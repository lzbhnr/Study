package PoolTest;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTestMethod {

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, Integer.MAX_VALUE, 3l, TimeUnit.DAYS, new LinkedBlockingDeque<>());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                long i = 1;
                while (i > 0) {
                    i--;
                    System.out.println("-----" + i);
                }

            }
        });

        executor.shutdownNow();
        executor.shutdown();

        Thread a = Thread.currentThread();
        a.interrupted();
        System.out.println(a.isInterrupted());

        System.out.println("----end-----");

        Thread thread=new Thread(()->{
            while (true){
                //让thread线程不断运行
            }
        });
        thread.start();
        Thread.sleep(1000);//让主线程睡一会
        thread.interrupt();//打断正在正常运行的thread
        System.out.println(thread.isInterrupted());

    }
}
