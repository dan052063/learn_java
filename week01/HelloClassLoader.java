package cn.com.paycn.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

public class HelloClassLoader extends ClassLoader {
	public static void main(String[] args) {

		try {
			Class<?> clazz = new HelloClassLoader().findClass("Hello");
			Object obj = clazz.newInstance();
			Method method = clazz.getMethod("hello");
			method.invoke(obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] bytes = decode();
		return defineClass(name, bytes, 0, bytes.length);
	}

	public byte[] decode() {
		FileInputStream fileInputStream = null;
		BufferedInputStream bis = null ;
		ByteArrayOutputStream bos = null;
		try {
			File file = new File("/Users/lyndan/git/learn_java/week01/Hello.xlass");
			 fileInputStream = new FileInputStream(file);
			 bis = new BufferedInputStream(fileInputStream);
			 bos = new ByteArrayOutputStream();
			int length = 0;
			while ((length = bis.read()) != -1) {
				bos.write(255 - length);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(fileInputStream!=null){
				try {
					fileInputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(bis!=null){
			try {
				bis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			if(bos!=null){
				try {
					bos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return bos.toByteArray();
	}
}
