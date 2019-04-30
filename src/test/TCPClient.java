package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class TCPClient {
	private static final String SERVER_IP = "192.168.1.19";
	private static final int SERVER_PORT = 6000;
	public static void main(String[] args) {
		Socket socket = null;
		try {		
			// 1. 소켓 생성
			socket = new Socket();
			
			// 1-1. 소켓 버퍼 사이즈 확인
			int receiveBufferSize = socket.getReceiveBufferSize();
			int sendBufferSize = socket.getSendBufferSize();
			
			System.out.println("기존 리시브 버퍼 사이즈 : " + receiveBufferSize);
			System.out.println("기존 센드 버퍼 사이즈 : " + sendBufferSize);
			
			// 1-2. 소켓 버퍼 사이즈 변경
			socket.setReceiveBufferSize(1024*10);
			socket.setSendBufferSize(1024*10);
			System.out.println("변경 후 리시브 버퍼 사이즈 : " + socket.getReceiveBufferSize());
			System.out.println("변경 후 센드 버퍼 사이즈 : " + socket.getSendBufferSize());
			
			// 1-3. SO_NODELAY(Nagle Algorithm Off) - ACK 수신 없이 DATA 보내기.
			socket.setTcpNoDelay(true);
			
			// 1-4. SO_TIMEOUT - read 에 타이머 걸기.
			socket.setSoTimeout(1000);
			
			// 2. 서버 연결
			socket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));
			System.out.println("[client] connected");
			// 3. I/O Stream 받아오기
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			
			// 4. 쓰기
			String data = "Hello World\n";
			os.write(data.getBytes("utf-8"));
			
			// 5. 읽기
			byte[] buffer = new byte[256];
			int readByteCount = is.read(buffer); // blocking
			if(readByteCount == -1) {
				System.out.println("[client] closed by server");
			}
			
			data = new String(buffer, 0, readByteCount, "utf-8");
			System.out.println("[client] received : " + data);
			
		} catch (SocketTimeoutException e) {
			System.out.println("[client] time out");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(socket != null && !socket.isClosed()) {
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
