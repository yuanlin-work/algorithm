package reentrantlock;

public class AddThread implements Runnable {

	private Task task;

	public AddThread(Task task) {
		this.task = task;
	}

	@Override
	public void run() {
		task.add();
	}

}
