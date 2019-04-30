package thread;

public class ThreadEx01 {
	public static void main(String[] args) {
//		for(int i = 0; i <= 10; i++) {
//			System.out.println(i);
//		}
		Thread digitThread = new DigitThread();
		digitThread.start();
		
	}
}
