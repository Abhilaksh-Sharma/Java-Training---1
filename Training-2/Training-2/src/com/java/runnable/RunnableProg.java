package com.java.runnable;

/* new Thread(new Runnable()) --> implementing Runnable interface to create a THREAD
 * Multiple inheritance through interfaces can be achieved
 * We can extend one class as well but in case of --> class MyThread extends Thread {} ==> NOT POSSIBLE (Multiple-Inheritance)
 * Runnable interface is a functional interface which has only abstract method --> run().
 * Hence can be implemented by using Java-8 Lambda expression.
 * */

public class RunnableProg implements Runnable{

	@Override
	public void run() {
		System.out.println("My Runnable Instance Thread ...");
	}
	
	public static void main(String[] args) throws InterruptedException {
		//Create an instance of thread using Runnable
		Thread thread = new Thread(new RunnableProg());
		
		/*JOIN-EXAMPLE*/
		//	When we invoke the join() method on a thread, the calling thread goes into a waiting state. 
		//	It remains in a waiting state until the referenced thread terminates.
		
		//Java-8 --> Implementing Lambda Expression
		Thread thread1 = new Thread(()->{
			for(int i=0;i<100;i++) {
				System.out.println(i);};
		},"Thread-1");
		
		Thread thread2 = new Thread(()->{
			for(int i=100;i<200;i++) {
				System.out.println(i);};
		},"Thread-2");
		
		Thread thread3 = new Thread(()->{
			for(int i=200;i<300;i++) {
				System.out.println(i);};
		},"Thread-3");
		
		//Start & Join the Threads
		thread1.start();
		thread1.join();//Here the main thread will go into waiting state until thread1 completes its execution
		
		
		thread2.start();
		thread2.join();//If I comment the thread1 then main thread & thread1 will go into waiting state until thread2 completes its execution
		
		
		thread3.start();//After thread1 & thread2 completes their execution then only thread3 will start its execution if we invoke thread1 & thread2 join() method
		

	}

}
