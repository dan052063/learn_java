# learn_java
作业三：
1、测试前提对串行GC\并行GC\CMS-GC\G1-GC分别设置Xms、Xmx=512m、1G、4G
2、当xms、xmx内存设置越来越大时，gc次数减少，gc处理时间增加，full gc减少
3、同样使用512m的时候，串行GC产生两次full gc，并行也出现两次full gc，串行GC的时间，是并行GC的两倍，cmsgc没有产生full gc,G1GC也没有产生full gc，G1GC GC处理时间最短
4、内存越大G1的效果越好


作业六：
package cn.com.paycn.util;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtils {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

	/**
	 * httpClientPost请求
	 */
	public static String httpClientPost() {
		try {
			String url = "http://localhost:8801";
			HttpClient client = new HttpClient();
			PostMethod method = new PostMethod(url);
			int code = client.executeMethod(method);
			if (code == 200) {
				String res = method.getResponseBodyAsString();
				return res;
			}
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * okHttpPost请求
	 * 
	 * @param json
	 * @return
	 */
	public static String okHttpPost(String json) {
		try {
			String url = "http://localhost:8801";
			OkHttpClient client = new OkHttpClient();
			RequestBody requestBody = RequestBody.create("", JSON);
			Request request = new Request.Builder().url(url).post(requestBody).build();
			Response response = client.newCall(request).execute();
			return response.body().string();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";

	}
}
