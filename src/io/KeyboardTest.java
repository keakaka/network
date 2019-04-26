package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class KeyboardTest {
	public static void main(String[] args) {
		
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in, "utf-8"))) {
			// 기반 스트림(표준 입력, 키보드, System.in)
			
			// 보조 스트림 1 
			// byte | byte | byte -> char
			
			// 보조스트림 2
			// char|char|char|\n -> "charcharchar"
			
			// read
			String line = null;
			while((line = br.readLine()) != null) {
				if("exit".contentEquals(line)) {
					break;
				}
				System.out.println(">> " + line);
			}
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
