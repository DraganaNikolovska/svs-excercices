
public class NumberPrinter implements Runnable {

	private int number;
	private int interval;

	public static void main(String[] args) throws InterruptedException {
		NumberPrinter printer = new NumberPrinter();

		if (args.length != 0) {
			printer.number = Integer.parseInt(args[0]);
			printer.interval = Integer.parseInt(args[1]);

		} else {
			System.out.println("Argument list is epmpy!");
		}

		Thread thread = new Thread(printer);
		thread.start();
		Thread.sleep(printer.interval);
		thread.interrupt();
	}

	@Override
	public void run() {
		for (int i = 1; i < this.number; i++) {
			System.out.println(i);
			if (Thread.interrupted()) {
				System.out.println("interrupted");
				return;
			}

		}

	}
}
