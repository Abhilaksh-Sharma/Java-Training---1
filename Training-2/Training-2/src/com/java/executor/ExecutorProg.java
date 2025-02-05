package com.java.executor;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

class CallableTask implements Callable<String> {

	/*
	 * Callable interface --> Create a thread which can return some value (object)
	 * Here we can throw exceptions as well unlike in Runnable interface
	 */
	@Override
	public String call() throws Exception {
		return "Callable interface Thread Running ...";
	}

}

class RunnableTask implements Runnable {

	@Override
	public void run() {
		System.out.println("Runnable interface Thread Running ...");
	}

}

public class ExecutorProg {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// User-Input
		Scanner in = new Scanner(System.in);

		System.out.print("Enter number of threads for FixedThreadPool : ");
		int nThreads = in.nextInt();
		int corePoolSize = Runtime.getRuntime().availableProcessors();
		System.out.println("Numbre of cores available in your system : " + corePoolSize);

		/* Creating ExecutorService :::Types of Thread Pools::: */

		// Fixed-size thread pool
		// BlockingQueue : LinkedBlockingQueue (Unbounded Queue)
		ExecutorService executorService1 = Executors.newFixedThreadPool(nThreads);

		// Creates threads as needed reuses already constructed threads as per
		// availability
		// keepAliveTime : 60 seconds
		// BlockingQueue : SynchronousQueue
		ExecutorService executorService2 = Executors.newCachedThreadPool();

		// Schedule task execution after a fixed delay or periodically
		// BlockingQueue : LinkedBlockingQueue (Unbounded Queue)
		ExecutorService executorService3 = Executors.newScheduledThreadPool(corePoolSize);

		// Sequential execution of tasks with Single Worker Thread
		// BlockingQueue : LinkedBlockingQueue (Unbounded Queue)
		ExecutorService executorService4 = Executors.newSingleThreadExecutor();

		//
		ExecutorService executorService5 = Executors.newWorkStealingPool();

		/* ::: ExecutorService Executing Tasks --> Callable & Runnable ::: */

		executorService1.execute(new RunnableTask());
		// submit() method in ExecutorService is overloaded for Runnable & Callable
		// interface tasks
		executorService1.submit(new RunnableTask());
		// Upon invocation of a callable task you will always get in return a Future
		// object
		Future<String> futureObject1 = executorService1.submit(new CallableTask());
		System.out.println("Value of Future<> object : " + futureObject1.get());

		System.out.println("Future Object Cancel Status : " + futureObject1.isCancelled());

		System.out.println("Future Object Done Status : " + futureObject1.isDone());

		// Attempts to cancel the execution of task depending upon state of the task
		// Task --> Completed,Already Cancelled,Could not be cancelled for some reason
		// ==> FAIL (Attempt to cancel the execution fails)
		// Task --> Not Started ==> SUCCESS (Task will never start)
		// Task --> Already Started ==> depends upon mayInterruptIfRunning boolean flag
		// to raise an interrupt or not
		futureObject1.cancel(false);

		// Immediate shutdown required --> Don't wait for actively executing tasks to terminate
		List<Runnable> tasks = executorService1.shutdownNow();

		// Initiate Shutdown process for the ThreadPool
		//executorService1.shutdown();

		if (executorService1.isShutdown()) {
			System.out.println("Shutdown Initiated ...");
		}

		// Awaits for actively executing tasks to get terminated
		executorService1.awaitTermination(10, TimeUnit.SECONDS);

		//Checks whether all the tasks have completed the shutdown process
		if (executorService1.isTerminated()) {
			System.out.println("ExecutorService is Terminated");
		}

	}

}
