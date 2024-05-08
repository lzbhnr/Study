package JavaUC;

public class SyncTest {

    //使用volatile修饰变量
    private static int n = 0;

    public static synchronized void add() {
        n++;
        System.out.println("add method" + n);
    }

    public static void main(String[] args) {
        int j = 0;
        new Thread1().start();
        while (n < 100) {

        }
        System.out.println("stop");
    }

    static class Thread1 extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 100; i++) {
                System.out.println(i);
                add();

            }
        }
    }

}
