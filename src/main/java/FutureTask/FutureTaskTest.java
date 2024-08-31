package FutureTask;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // FutureTask实现了Runnable，可以看做是一个任务
        FutureTask<Long> futureTask = new FutureTask<>(new Callable() {
            @Override
            public Long call() throws Exception {
                System.out.println(Thread.currentThread().getName() + "========>正在执行");
                try {
                    Thread.sleep(3 * 1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return 10086L;
            }
        });

        System.out.println(Thread.currentThread().getName() + "========>启动任务");

        // 传入futureTask，启动线程执行任务
        new Thread(futureTask).start();

        // 但它同时又实现了Future，可以获取异步结果（会阻塞3秒）

        System.out.println("--------------");
        long time = System.currentTimeMillis();
        Long result = futureTask.get();

        System.out.println(System.currentTimeMillis() - time);
        System.out.println("任务执行结束，result====>" + result);
    }

}
