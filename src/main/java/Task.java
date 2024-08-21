import java.util.List;
import java.util.function.Function;

public class Task {

    private Object result;
    private boolean isFinish;
    private Object object;
    private Function function;
    private List<Task> children;
    private List<Task> parent;

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    public List<Task> getChildren() {
        return children;
    }

    public void setChildren(List<Task> children) {
        this.children = children;
    }

    public List<Task> getParent() {
        return parent;
    }

    public void setParent(List<Task> parent) {
        this.parent = parent;
    }

    public boolean isFinish() {
        return isFinish;
    }

    public void setFinish(boolean finish) {
        isFinish = finish;
    }
}
