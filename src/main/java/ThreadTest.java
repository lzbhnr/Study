import java.util.stream.IntStream;

public class ThreadTest {

    public static void main(String[] args) throws Exception {

        /*
        ThreadLocal threadLocal = new ThreadLocal();

        IntStream.range(1, 5).forEach(i -> new Thread(() -> {
            // 设置线程中本地变量的值
            threadLocal.set("thread-" + i);
            // 打印当前线程中本地内存中本地变量的值
            System.out.println(threadLocal.get() + "i---------------------" + i);
            // 清除本地内存中的本地变量
            threadLocal.remove();
            // 打印本地变量

            System.out.println("removem m m--------------" + i);
            System.out.println("thread-" + i + " after remove: " + threadLocal.get());
        }).start());
        */

        new ThreadTest().threadLocalTest();

    }

    public static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();


    public void threadLocalTest() throws Exception {
        // 主线程设置值
        THREAD_LOCAL.set("wupx");
        String v = THREAD_LOCAL.get();
        System.out.println("[1]Thread-0线程执行之前，" + Thread.currentThread().getName() + "线程取到的值：" + v);

        new Thread(new Runnable() {
            @Override
            public void run() {
                String v = THREAD_LOCAL.get();
                System.out.println("[1.5]" + Thread.currentThread().getName() + "线程取到的值：" + v);
                // 设置 threadLocal
                THREAD_LOCAL.set("huxy");
                v = THREAD_LOCAL.get();
                System.out.println("[2]重新设置之后，" + Thread.currentThread().getName() + "线程取到的值为：" + v);
                System.out.println("[3]" + Thread.currentThread().getName() + "线程执行结束");
            }
        }).start();
        // 等待所有线程执行结束
        Thread.sleep(3000L);
        v = THREAD_LOCAL.get();
        System.out.println("[4]" + "Thread-0线程执行之后，" + Thread.currentThread().getName() + "线程取到的值：" + v);
    }
}
