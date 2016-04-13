import java.util.Scanner;

public class StopwatchApp {

	public static void main(String[] args) {
		System.out.println("Click enter to start the Stopwatch");
		System.out.println("Click p to pause the Stopwatch");
		System.out.println("Click r to resume the Stopwatch");
		System.out.println("Click s to stop the Stopwatch");

		Stopwatch stopwatch = new Stopwatch();

		Scanner scanner = new Scanner(System.in);
		String line = scanner.nextLine();

		if (line.isEmpty()) {
			stopwatch.start();
			while ((line = scanner.nextLine()).charAt(0) != 's') {
				if (line.charAt(0) == 'p') {
					stopwatch.pause();
				} else if (line.charAt(0) == 'r') {
					stopwatch.resume();
				}
			}
			stopwatch.stop();

		}
	}
}
