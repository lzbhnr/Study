package Thread;

public class PrintABC {

    private static int state = 0;

    private static final Object lock = new Object();

    public static void main(String[] args) {

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 100; i++) {

                        synchronized (lock){

                            while (state%3 != 0 ){
                                lock.wait();
                            }

                            System.out.println("A");
                            state++;
                            lock.notifyAll();
                        }
                    }

                } catch (Exception exception) {

                }
            }
        });
    }
}
