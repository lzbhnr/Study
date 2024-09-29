import java.util.concurrent.locks.LockSupport;

public class Test {

    public static void main(String[] args) {

    }

    private void printA(Thread thread) {

        try {
            Thread.sleep(10L);
            LockSupport.park();
            System.out.println("A");
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }

    private void printB(Thread thread) {
        try {
            Thread.sleep(10L);
            LockSupport.park();
            System.out.println("B");
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }

    private void printC(Thread thread) {

        try {
            Thread.sleep(10L);
            LockSupport.park();
            System.out.println("C");
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }
}
