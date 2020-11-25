import java.util.Map;
import java.util.Set;

public interface CriticalPath {
    Task[] findCriticalPath(Set<Task> tasks);
    Map<Task, CriticalCalculations> forwardBackwardPass(Set<Task> tasks);
}