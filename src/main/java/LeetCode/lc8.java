package LeetCode;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class lc8 {

    // 使用原子操作类AtomicInteger的ctl变量，前3位记录线程池的状态，后29位记录线程数
    private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
    // Integer的范围为[-2^31,2^31 -1], Integer.SIZE-3 =32-3= 29，用来辅助左移位运算
    private static final int COUNT_BITS = Integer.SIZE - 3;
    // 高三位用来存储线程池运行状态，其余位数表示线程池的容量
    private static final int CAPACITY = (1 << COUNT_BITS) - 1;

    // 线程池状态以常量值被存储在高三位中
    private static final int RUNNING = -1 << COUNT_BITS; // 线程池接受新任务并会处理阻塞队列中的任务
    private static final int SHUTDOWN = 0 << COUNT_BITS; // 线程池不接受新任务，但会处理阻塞队列中的任务
    private static final int STOP = 1 << COUNT_BITS; // 线程池不接受新的任务且不会处理阻塞队列中的任务，并且会中断正在执行的任务
    private static final int TIDYING = 2 << COUNT_BITS; // 所有任务都执行完成，且工作线程数为0，将调用terminated方法
    private static final int TERMINATED = 3 << COUNT_BITS; // 最终状态，为执行terminated()方法后的状态

    // ctl变量的封箱拆箱相关的方法
    private static int runStateOf(int c) {
        return c & ~CAPACITY;
    } // 获取线程池运行状态

    private static int workerCountOf(int c) {
        return c & CAPACITY;
    } // 获取线程池运行线程数

    private static int ctlOf(int rs, int wc) {
        return rs | wc;
    } // 获取ctl对象


    public static int myAtoi(String str) {
        str = str.trim();
        if (str.length() == 0) return 0;
        if (!Character.isDigit(str.charAt(0))
                && str.charAt(0) != '-' && str.charAt(0) != '+')
            return 0;
        long ans = 0L;
        boolean neg = str.charAt(0) == '-';
        int i = !Character.isDigit(str.charAt(0)) ? 1 : 0;
        while (i < str.length() && Character.isDigit(str.charAt(i))) {
            ans = ans * 10 + (str.charAt(i++) - '0');
            if (!neg && ans > Integer.MAX_VALUE) {
                ans = Integer.MAX_VALUE;
                break;
            }
            System.out.println(Integer.MAX_VALUE + 1);
            System.out.println(Integer.MAX_VALUE + 1L);
            if (neg && ans > 1L + Integer.MAX_VALUE) {
                ans = 1L + Integer.MAX_VALUE;
                break;
            }
        }
        return neg ? (int) -ans : (int) ans;
    }

    public static boolean isNum(char c) {
        return c >= '0' && c <= '9';
    }

    public static void main(String[] args) {

        System.out.println(~CAPACITY);
        System.out.println(CAPACITY);

        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 4, 3l, TimeUnit.DAYS, new LinkedBlockingDeque<>());

        executor.execute(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
}
