import java.time.LocalDate;
import java.util.UUID;

public class Task {
	private final UUID id;
	private String title;
	private String desc;
	private Taskstate taskstate;
	private LocalDate dueDate;

	public Task(String title, String desc, Taskstate taskstate, LocalDate dueDate) {
		this.id = UUID.randomUUID();
		this.title = title;
		this.desc = desc;
		this.taskstate = taskstate;
		this.dueDate = dueDate;
	}
	
	public Task(String title, String desc, LocalDate dueDate) {
		this(title, desc, Taskstate.Todo, dueDate);
	}
	
	public Task(String title, String desc) {
		this(title, desc, null);
	}

	public UUID getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Taskstate getTaskstate() {
		return taskstate;
	}

	public void setTaskstate(Taskstate taskstate) {
		this.taskstate = taskstate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	
	
	
	
	
	
}
