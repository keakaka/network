package echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {
	private static final String SERVER_IP = "192.168.1.38";
	private static final int SERVER_PORT = 7000;
		public static void main(String[] args) {
			Socket socket = null;
			Scanner scanner = null;
			try {		
				// Scanner 생성(표준입력)
				scanner = new Scanner(System.in);
				
				// 1. 소켓 생성
				socket = new Socket();
				
				// 2. 서버 연결
				socket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));
				log("connected");
				
				// 3. I/O Stream 받아오기
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
				PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
				
				while(true) {
					// 5. 키보드 입력 받기
					System.out.print(">> ");
					String line = scanner.nextLine();
					if("quit".contentEquals(line)) {
						break;
					}
					
					// 6. 데이터 쓰기
					pw.println(line);
					
					// 7. 데이터 읽기
					String data = br.readLine();
					if(data == null) {
						log("closed by server");
						break;
					}
					
					// 8. 콘솔 출력
					System.out.println("<< " + data);
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if(scanner != null) {
						scanner.close();
					}
					if(socket != null && !socket.isClosed()) {
						socket.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	}

		public static void log(String log) {
			System.out.println("[client] " + log);
		}
}
