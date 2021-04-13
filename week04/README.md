# learn_java

#示例一
package java0.Thread;

import java.util.concurrent.CountDownLatch;

public class Demo1 {
    public static void main(String[] args) throws InterruptedException {


        Thread thread=new Thread(new Runnable(){

            @Override
            public void run() {
                System.out.println("新线程1开始-----");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("新线程1结束-----");
            }
        });
        thread.start();

        Thread thread1=new Thread(new Runnable(){

            @Override
            public void run() {
                System.out.println("新线程2开始-----");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("新线程2结束-----");
            }
        });
        thread1.start();
        thread.join();
        thread1.join();
        System.out.println("主线程结束-----");
    }
}


#示例二
package java0.Thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Demo2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //supplyAsync有返回值，而runAsync没有返回值
        CompletableFuture<Integer> completableFuture=CompletableFuture.supplyAsync(()->{
            System.out.println("新线程1开始-----");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("新线程1结束-----");
            return 1;
        });
        CompletableFuture<Integer> completableFuture1=CompletableFuture.supplyAsync(()->{
            System.out.println("新线程2开始-----");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("新线程2结束-----");
            return 2;
        });
        //使用thenCombine合并任务
        CompletableFuture<Integer> result=completableFuture.thenCombine(completableFuture1,(result1,result2)->result1*result2);
        System.out.println("计算结果:" + result.get());
    }
}

#示例三

package java0.Thread;

import java.util.concurrent.CountDownLatch;

public class Demo3 {
    public static void main(String[] args) throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(2);

        Thread thread=new Thread(new Runnable(){
            @Override
            public void run() {
                System.out.println("新线程1开始-----");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    latch.countDown();
                }
                System.out.println("新线程1结束-----");
            }
        });
        thread.start();

        Thread thread1=new Thread(new Runnable(){
            @Override
            public void run() {
                System.out.println("新线程2开始-----");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    latch.countDown();
                }
                System.out.println("新线程2结束-----");
            }
        });

        thread1.start();

        latch.await();
        System.out.println("主线程结束-----");
    }
}


#示例四
package java0.Thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Demo4 {
    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

        Thread thread=new Thread(new Runnable(){
            @Override
            public void run() {
                System.out.println("新线程1开始-----");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("新线程1结束-----");
            }
        });
        thread.start();

        Thread thread1=new Thread(new Runnable(){
            @Override
            public void run() {
                System.out.println("新线程2开始-----");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("新线程2结束-----");
            }
        });
        thread1.start();
        cyclicBarrier.await();
        System.out.println("主线程结束-----");
    }
}

#示例五
package java0.Thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo5 {
    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("新线程1开始-----");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("新线程1结束-----");
            }
        });
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("新线程2开始-----");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("新线程2结束-----");
            }
        });
        executorService.shutdown();
        while (true){
            if(executorService.isTerminated()){
                break;
            }
        }
        System.out.println("主线程结束-----");
    }
}

