import java.sql.Struct;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Coupang {


    /**
     * 基于DAG的任务编排程序
     * 1. 支持任务之间的依赖, 例如任务A依赖B，则必须等待B执行完成后才能执行A，依赖关系包括一对多，多对一的依赖
     * 2. 自定义任务
     * 3. 支持多线程并发
     * <p>
     * //
     */


    public static void main(String[] args) throws InterruptedException {

        List<Task> children = new LinkedList<>();
        Object param = "t3";
        Task t3 = new Task();
        t3.setFunction((o) -> {
            System.out.println("执行" + param);
            return o;
        });
        t3.setObject(param);

        children.add(t3);


        List<Task> tasks = new LinkedList<>();
        for (int i = 1; i < 3; i++) {
            Object p = "t" + i;
            Task t1 = new Task();
            t1.setFunction((o) -> {
                System.out.println("执行" + p);
                return o;
            });
            t1.setObject(p);
            t1.setChildren(children);
            tasks.add(t1);

        }
        t3.setParent(tasks);

        new Coupang().solution(tasks.get(0));

        Thread.sleep(3000l);
        System.out.println("--");
        new Coupang().solution(tasks.get(1));

        // 串行

        // 1 和 2 完成了 才能完成 3

        //

    }

    public void solution(Task task) {

        List<Task> parentTask = task.getParent();

        if (parentTask == null || parentTask.size() == 0) {
            // 可以执行
            exeTask(task);
        } else {

            boolean isAllFinish = true;
            for (int i = 0; i < parentTask.size(); i++) {

                if (!parentTask.get(i).isFinish()) {
                    isAllFinish = false;
                    break;
                }
            }

            if (isAllFinish) {
                exeTask(task);
            }

        }

    }


    @Override
    public String toString() {
        return super.toString();
    }

    public void exeTask(Task task) {
        Object result = task.getFunction().apply(task.getObject());
        task.setResult(result);
        task.setFinish(true);
        if (task.getChildren() == null || task.getChildren().size() == 0) {
            return;
        }
        task.getChildren().stream().forEach(
                t -> {
                    solution(t);
                }
        );

    }

}
