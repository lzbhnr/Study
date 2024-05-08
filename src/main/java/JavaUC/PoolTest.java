package JavaUC;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PoolTest {


    public static void main(String[] args) {

        cachedThreadPool();
    }


    public static void cachedThreadPool() {
        // 创建线程池
        ExecutorService threadPool = Executors.newCachedThreadPool();
        // 执行任务
        for (int i = 0; i < 10; i++) {
            threadPool.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println("任务被执行,线程:" + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                }
            });
        }
    }

}
