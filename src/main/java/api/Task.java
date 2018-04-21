package api;

import java.time.LocalDate;
import java.util.UUID;

public class Task {
	private final int id;
	private String title;
	private String description;
	private TaskState taskState;
	private LocalDate dueDate;

	public Task(int id, String title, String description, TaskState taskState, LocalDate dueDate) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.taskState = taskState;
		this.dueDate = dueDate;
	}
	
	public Task(int id, String title, String description, LocalDate dueDate) {
		this(id, title, description, TaskState.Todo, dueDate);
	}
	
	public Task(int id, String title, String description) {
		this(id, title, description, null);
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Task setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public Task setDescription(String description) {
		this.description = description;
		return this;
	}

	public TaskState getTaskState() {
		return taskState;
	}

	public Task setTaskState(TaskState taskState) {
		this.taskState = taskState;
		return this;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public Task setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
		return this;
	}
	
	
	
	
	
	
}
