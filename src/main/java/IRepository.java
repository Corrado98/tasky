import java.time.LocalDate;
import java.util.List;

public interface IRepository {
    boolean add(String title, String desc, LocalDate dueDate, Taskstate taskstate);
    boolean remove(Task t);
    void clear();
    List<Task> allTasks();
}
