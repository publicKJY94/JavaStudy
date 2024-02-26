package day010;

abstract class Pokemon {
	String name,type;
	int level;

	public Pokemon(String name, int level,String type) {
		this.name = name;
		this.level = level;
		this.type = type;
	}

	void printInfo() {
		System.out.println(this.type + "타입 " + this.name + " Lv." + this.level);
	}

	abstract void attack();

	abstract void hello();
}

abstract class Elec extends Pokemon {
	
	public Elec(String name, int level) {
		super(name, level, "전기");
	}
	
	@Override
	void attack() {
		System.out.println("백만볼트!!!");
	}
}

class Pikachu extends Elec {
	public Pikachu() {
		this(5);
	}

	public Pikachu(int level) {
		super("피카츄", level);
	}

	@Override
	void hello() {
		System.out.println("피카피카");
	}

}

class Raichu extends Elec {
	public Raichu() {
		this(5);
	}

	public Raichu(int level) {
		super("라이츄", level);
	}

	@Override
	void hello() {
		System.out.println("라이라이");
	}

}

public class Main {

	public static void main(String[] args) {
		Pikachu p1 = new Pikachu();
		Pikachu p2 = new Pikachu(11);
		Raichu p3 = new Raichu();
		p1.printInfo();
		p2.printInfo();
		p3.printInfo();
		p1.attack();
		p3.attack();
		p1.hello();
		p3.hello();
	}

}
