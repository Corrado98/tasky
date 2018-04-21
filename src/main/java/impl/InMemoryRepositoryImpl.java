package impl;

import api.IRepository;
import api.Task;
import api.TaskState;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class InMemoryRepositoryImpl implements IRepository {
	private final List<Task> tasks = new ArrayList<>();

    @Override
    public boolean exists(int id) {
        return tasks.stream().anyMatch(task -> task.getId() == id);
    }

    @Override
	public Task create(int id, String title, String desc, LocalDate dueDate, TaskState taskState) {
        Task task = new Task(id, title, desc, taskState, dueDate);
        tasks.add(task);
        return task;
	}

	@Override
	public Task retrieve(int id) {
        return tasks.stream().filter(task -> task.getId() == id).findFirst().orElse(null);
	}

	@Override
	public Task update(int idToUpdate, String title, String desc, LocalDate dueDate, TaskState taskState) {
        if(!exists(idToUpdate)) {
            return null;
        }

		return retrieve(idToUpdate)
                .setTitle(title)
                .setDescription(desc)
                .setDueDate(dueDate)
                .setTaskState(taskState);
	}

	@Override
	public boolean remove(int id) {
        if(!exists(id)) {
            return false;
        }

		return tasks.remove(retrieve(id));
	}

	@Override
	public List<Task> getAllTasks() {
		return Collections.unmodifiableList(tasks);
	}

	@Override
	public void clearAllTasks() {
        tasks.clear();
	}
}
