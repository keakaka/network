package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class NSLookup {
	public static void main(String[] args) {
		String hostname = "";
		while(true) {
			System.out.print("> ");
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in, "utf-8"));
				hostname = br.readLine();
				if("exit".equals(hostname)) {
					br.close();
					System.exit(0);
				}
				InetAddress[] inetAddresses = InetAddress.getAllByName(hostname);
				for(InetAddress addr : inetAddresses) {
					System.out.println(hostname + " : " + addr.getHostAddress());
				}
			} catch (UnknownHostException e) {
				System.out.println(hostname + "을 찾을 수 없습니다. Non-existent domain");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

