package JavaUC;

import java.util.concurrent.CountDownLatch;

public class Vola {
    //使用volatile修饰变量
    private static int n = 0;

    public static void add() {
        n++;
    }

    public static void main(String[] args) {
        int i = 2;
        CountDownLatch latch = new CountDownLatch(i);
        for (int j = 0; j < i; j++) {
            new Thread1(latch).start();
        }
        try {
            Thread.sleep(10000);

        } catch (Exception e) {

        }
        System.out.println("main" + n);

        System.out.println("stop");
    }

    static class Thread1 extends Thread {

        private CountDownLatch countDownLatch;

        public Thread1(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 100; i++) {
                add();
            }
            countDownLatch.countDown();
            //System.out.println("n" + n);
        }
    }
}
