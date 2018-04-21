package api;

import java.time.LocalDate;
import java.util.List;

public interface IRepository {
    boolean exists(int id);
    Task create(int id, String title, String desc, LocalDate dueDate, TaskState taskState);
    Task retrieve(int id);
    Task update(int idToUpdate, String title, String desc, LocalDate dueDate, TaskState taskState);
    boolean remove(int id);
    List<Task> getAllTasks();
    void clearAllTasks();
}
