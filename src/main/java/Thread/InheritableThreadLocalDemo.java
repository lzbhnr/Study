package Thread;

public class InheritableThreadLocalDemo {

    static InheritableThreadLocal<String> local = new InheritableThreadLocal<>();

    public static void testParent() {
        local.set("I'm parent");
        Thread child = new Thread(InheritableThreadLocalDemo::testChild, "子线程");
        child.start();
        try {
            child.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 查看父线程的值是否对子线程有影响
        print();
    }

    public static void testChild() {
        print();
        // 在子线程中修改值，后续查看父线程是否受影响
        local.set("I'm child");
        // 查看子线程是否修改成功
        print();
    }

    public static void print() {
        System.out.println("当前线程为：" + Thread.currentThread().getName() + "; " +
                "当前ThreadLocal中的值为：" + local.get());
    }

    public static void main(String[] args) {
        // 命名为父线程，方便后面打印结果
        Thread parent = new Thread(InheritableThreadLocalDemo::testParent, "父线程");
        parent.start();
    }
}
