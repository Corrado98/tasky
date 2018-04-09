
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Repository implements IRepository {
	private final List<Task> tasks = new ArrayList();

	public boolean add(String title, String desc, LocalDate dueDate, Taskstate taskstate) {
		return tasks.add(new Task(title, desc, taskstate, dueDate));
	}
	
	public boolean remove(Task t) {
		return tasks.remove(t);
	}
	
	public void clear() {
		tasks.clear();
	}
	
	public List<Task> allTasks() {
		return Collections.unmodifiableList(tasks);
	}


}
