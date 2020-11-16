import java.util.Set;

public interface CriticalPath {
    Set<Task> findCriticalPath(Set<Task> tasks);
}