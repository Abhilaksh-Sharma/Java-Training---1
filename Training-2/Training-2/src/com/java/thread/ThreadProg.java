package com.java.thread;

public class ThreadProg extends Thread {

	public void run() {
		System.out.println("MyThread is runnning...");
	}

	public static void main(String[] args) {

		ThreadProg thread = new ThreadProg();

		System.out.println("Thread State : " + thread.getState());

		thread.setPriority(NORM_PRIORITY);//MAX_PRIORITY(10) ,NORM_PRIORITY(5) , MIN_PRIORITY(0)

		thread.setName("MyThread");

		// Set Daemon Thread -- True (Low priority threads that provide services to the User-Threads (background operations like GC etc which won't restrict JVM to exit even if its running )
		thread.setDaemon(true);

		// Invokes run() method implicitly
		thread.start();
		
		//Deprecated methods
//		thread.suspend();//Suspend the current thread to WAITING_STATE
//		
//		thread.resume();//Resume the current thread to RUNNING_STATE
//		
//		thread.stop();//Stops the thread completely
		
//		System.out.println("Thread State (After Resume): " + thread.getState());

		//Thread STATES --> NEW(new Thread()) , RUNNABLE(thread.start()) , RUNNING(Thread selected by ThreadShceduler for the execution --> run() method execution) , BLOCKED/WAITING(thread.wait()/Thread.sleep()) , TERMINATED
		System.out.println("Thread State : " + thread.getState());

		System.out.println("Thread Id : " + thread.getId());

		System.out.println("Thread Name : " + thread.getName());

		System.out.println("Thread Priority : " + thread.getPriority());

		System.out.println("Thread State : " + thread.getState());
		
		if (thread.isDaemon()) {
			System.out.println("*" + thread.getName() + " is Daemon Thread");
		}

		System.out.println("Thread Group : " + thread.getThreadGroup());

		//Creating a ThreadGroup
		ThreadGroup threadGroup = new ThreadGroup("Group-1A");

		//Adding Threads to ThreadGroup
		Thread thread1 = new Thread(threadGroup, "Thread-1");
		Thread thread2 = new Thread(threadGroup, "Thread-2");
		Thread thread3 = new Thread(threadGroup, "Thread-3");
		Thread thread4 = new Thread(threadGroup, "Thread-4");
		Thread thread5 = new Thread(threadGroup, "Thread-5");

		//Starting threads
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		thread5.start();

		System.out.println("Threads Active Count in threadGroup : " + threadGroup.activeCount());

		System.out.print("Thread Details in threadGroup : ");
		threadGroup.list();

		

	}
}
