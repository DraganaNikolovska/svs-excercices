import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CountDownLatchApplication {

	private static final int NUMBER_OF_OPERATIONS = 10;

	public static void main(String[] args) throws Exception {

		final CountDownLatch latch = new CountDownLatch(NUMBER_OF_OPERATIONS);
		final ExecutorService executorService = Executors.newCachedThreadPool();
		Future<Integer> future = null;
		ArrayList<Future<Integer>> allFuturs = new ArrayList<>();
		for (int i = 1; i <= NUMBER_OF_OPERATIONS; i++) {
			future = executorService.submit(new ComplexCalculation(i, latch));
			allFuturs.add(future);
		}

		System.out.println("Waithing for all complex operations to finish...");
		latch.await();
		System.out.println("All complex operations finished.");

		int max = 0;
		for (Future<Integer> future2 : allFuturs) {
			if (future2.get() > max)
				max = future2.get();
		}
		System.out.println("Max is " + max);
		executorService.shutdown();
	}
}
