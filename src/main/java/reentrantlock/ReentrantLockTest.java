package reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

	public static Integer i=0;
	public static final ReentrantLock lock = new ReentrantLock();
	static class addthread implements Runnable{
		@Override
		public void run() {
			lock.lock();
			i++;
			System.out.println(String.format("%s : %d",Thread.currentThread().getName(),i));
			lock.lock();
			i++;
			System.out.println(String.format("%s : %d",Thread.currentThread().getName(),i));
			lock.unlock();
			try {
				Thread.currentThread().sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			lock.unlock();
		}

	}
	static class subthread implements Runnable{

		@Override
		public void run() {
			lock.lock();
			i--;
			System.out.println(String.format("%s : %d",Thread.currentThread().getName(),i));
			lock.unlock();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Thread addThread = new Thread(new addthread());
		Thread subThread = new Thread(new subthread());
		addThread.start();
		Thread.currentThread().sleep(1);
		subThread.start();
	}
}
