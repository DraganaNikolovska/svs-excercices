
public class Stopwatch {
	public enum STATE {
		NOT_STARTED, PAUSED, RUNNING,
	}

	private int seconds;
	private STATE currentState;
	private Task task;
	private Thread taskThred;

	public Stopwatch() {
		this.seconds = 0;
		this.currentState = STATE.NOT_STARTED;
		task = new Task();
		taskThred = new Thread(task);
	}

	public void start() {
		this.currentState = STATE.RUNNING;
		this.taskThred.start();

	}

	public void pause() {
		this.currentState = STATE.PAUSED;

	}

	public void resume() {
		this.currentState = STATE.RUNNING;
		synchronized (task) {
			task.notifyAll();
		}

	}

	public void stop() {
		taskThred.interrupt();

	}

	public class Task implements Runnable {

		@Override
		public void run() {

			while (true) {

				try {
					if (Thread.interrupted()) {
						return;
					}
					if (currentState == STATE.PAUSED) {
						synchronized (this) {
							wait();
						}
					} else {
						System.out.println(seconds++);
						Thread.sleep(1000);
					}
				} catch (InterruptedException e) {
					return;
				}

			}

		}

	}
}
