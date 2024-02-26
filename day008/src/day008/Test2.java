package day008;

import java.util.Scanner;

class Animal {
	String type;
	String name;

	public Animal(String type) {
		this.type = type;
		this.name = "이름 없는 " + type;
	}

	public Animal(String type, String name) {
		this.type = type;
		this.name = name;
	}

	void printInfo() {
		System.out.println(this.type + " : " + this.name);
	}
}

public class Test2 {

	public static void main(String[] args) {
		
	}
}
