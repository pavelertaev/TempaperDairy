import java.util.HashMap;
import java.util.Objects;

public class TaskUtils {
    private HashMap<Integer,Task> task = new HashMap<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskUtils taskUtils = (TaskUtils) o;
        return Objects.equals(task, taskUtils.task);
    }

    @Override
    public int hashCode() {
        return Objects.hash(task);
    }
}
