import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class ComplexCalculation implements Callable<Integer> {

	private Integer orderNumber;
	private CountDownLatch latch;
	

	public ComplexCalculation(Integer orderNumber, CountDownLatch latch) {
		this.orderNumber = orderNumber;
		this.latch = latch;
	}

	@Override
	public Integer call() throws Exception {
		try {
			System.out.println("Complex calculation " + orderNumber + " started");
			TimeUnit.SECONDS.sleep(1);
			System.out.println("Complex calculation " + orderNumber + " finished");
			latch.countDown();
		} catch (Exception e) {

		}
		Integer r = new Random().nextInt(10 + 1);
		return r;
	}

}
