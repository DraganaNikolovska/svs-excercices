import java.util.Scanner;
import java.util.Timer;

import javax.swing.plaf.basic.BasicTabbedPaneUI.TabSelectionHandler;

public class Stopwatch {

	static boolean paused = false;

	public static void main(String[] args) {
		System.out.println("Click enter to start the Stopwatch");
		System.out.println("Click p to pause the Stopwatch");
		System.out.println("Click r to resume the Stopwatch");
		System.out.println("Click s to stop the Stopwatch");

		Task task = new Task();
		Thread taskThread = new Thread(task);

		Scanner scanner = new Scanner(System.in);
		String line = scanner.nextLine();
		// empty line == ENTER
		if (line.isEmpty()) {
			System.out.println("Stopwatch is started");
			taskThread.start();

			while ((line = scanner.nextLine()).charAt(0) != 's') {
				if (line.charAt(0) == 'p') {
					System.out.println("-- PAUSE --");
					paused = true;

				} else if (line.charAt(0) == 'r') {
					System.out.println("-- RESUME --");
					paused = false;
					synchronized (task) {
						task.notifyAll();
					}
				}
			}
			System.out.println(" -- STOP -- ");
			taskThread.interrupt();
		}

	}

	public static class Task implements Runnable {
		int seconds = 1;

		@Override
		public void run() {
			while (true) {
				try {
					if (Thread.interrupted()) {
						return;
					}
					if (paused) {
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
