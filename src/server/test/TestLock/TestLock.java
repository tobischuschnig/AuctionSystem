package server.test.TestLock;


public class TestLock {
	public static void main(String[] args) {
		LockerThread l = new LockerThread();
		Thread t = new Thread(l);
		t.start();
		int i = 0;
		while(true){
			l.first();
			System.out.println(++i);
		}
	}
}
