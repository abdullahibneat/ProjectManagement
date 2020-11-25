public class Node {
    private Task task;
    private boolean isCritical;

    public Node(Task t, boolean b) {
        task = t;
        isCritical = b;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public boolean isCritical() {
        return isCritical;
    }

    public void setCritical(boolean critical) {
        isCritical = critical;
    }

    @Override
    public String toString() {
        return task.getName();
    }
}
