import java.util.Set;

public interface CriticalPath {
    Task[] findCriticalPath(Set<Task> tasks);
}