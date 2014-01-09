package server.test.TestLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class LockerThread implements Runnable{
	
	Lock lock = new ReentrantLock();
	Condition first, second;
	Thread t;
	int waiting = 0;
	public LockerThread(){
//		t = new Thread(this);
//		t.start();
		first = lock.newCondition();
		second = lock.newCondition();
	}
	public void first(){
		lock.lock();
		System.out.println("First");
			waiting=1;
//			first.await();
			waiting=0;
		System.out.println("First");
		second.signal();
		lock.unlock();
	}
	
	public void run(){
		while(true){
		lock.lock();
//		second.await();
		System.out.println("run");
		try {
//			t.sleep(2000);
			System.out.println("Sleep over");
//			if(waiting == 1){
//				first.signal();
			
				second.await();
//			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		lock.unlock();
		}
	}
}
