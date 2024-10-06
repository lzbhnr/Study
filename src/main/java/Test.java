import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class Test {

    public static void main(String[] args) {
        Test t = new Test();
        Thread tc = new Thread(t::printC);
        Thread tb = new Thread(() -> t.printB(tc));
        Thread ta = new Thread(() -> t.printA(tb));
        ta.start();
        tb.start();
        tc.start();

        Lock lock = new ReentrantLock();

        lock.lock();

        Thread.currentThread().interrupt();
    }

    private void printC() {
        try {
            //Thread.sleep(5L);
            LockSupport.park();
            System.out.println("C");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static void printA(Thread thread) {
        try {
            //Thread.sleep(20L);
            System.out.println("A");
            LockSupport.unpark(thread);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public static void printB(Thread thread) {
        try {
            //.sleep(10L);
            LockSupport.park();
            System.out.println("B");
            LockSupport.unpark(thread);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

}
