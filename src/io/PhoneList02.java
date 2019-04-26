package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PhoneList02 {

	public static void main(String[] args) {
		try(Scanner sc = new Scanner(new File("phone.txt"))) {
			while(sc.hasNextLine()) {
				String name = sc.next();
				String phone01 = sc.next();
				String phone02 = sc.next();
				String phone03 = sc.next();
				
				System.out.println(name + " :\t" + phone01 + "-" + phone02 + "-" + phone03);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
