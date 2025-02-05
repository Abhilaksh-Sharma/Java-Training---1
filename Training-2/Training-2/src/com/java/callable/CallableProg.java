package com.java.callable;

//Java program to illustrate Callable and Future for random number generation 
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

class CallableExample implements Callable {

	public Integer call() throws InterruptedException {
		Random generator = new Random();
		Integer randomNumber = generator.nextInt(5);

		Thread.sleep(1000);

		return randomNumber;
	}

}

public class CallableProg {
	public static void main(String[] args) throws Exception {
		
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		
		Future<Integer> future = executorService.submit(new CallableExample());
		System.out.println("Future Object Value : " + future.get(1000, TimeUnit.MILLISECONDS));
		
		executorService.shutdown();

//		// FutureTask is a concrete class that
//		// implements both Runnable and Future
//		FutureTask[] randomNumberTasks = new FutureTask[5];
//
//		for (int i = 0; i < 5; i++) {
//			Callable callable = new CallableExample();
//
//			// Create the FutureTask with Callable
//			randomNumberTasks[i] = new FutureTask(callable);
//
//			// As it implements Runnable, create Thread
//			// with FutureTask
//			Thread t = new Thread(randomNumberTasks[i]);
//			t.start();
//		}
//
//		for (int i = 0; i < 5; i++) {
//			// As it implements Future, we can call get()
//			System.out.println(randomNumberTasks[i].get());
//
//			// This method blocks till the result is obtained
//			// The get method can throw checked exceptions
//			// like when it is interrupted. This is the reason
//			// for adding the throws clause to main
//		}
	}
}