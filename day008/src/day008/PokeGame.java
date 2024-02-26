package day008;

import java.util.Random;

class Pokemon {
	String name;
	int level;
	int exp;
	int point;
	static final int EXPMAX = 100;

	public Pokemon(String name) {
		this(name, 5);
	}

	public Pokemon(String name, int level) {
		this.name = name;
		this.level = level;
		this.exp = 0;
	}

	void game() {
		Random random = new Random();
		boolean success = random.nextBoolean();
		if (success) {
			this.point = random.nextInt(451) + 50;
			System.out.printf("%d 경험치를 획득!\n", this.point);
			this.exp += this.point;
			this.levelUp();
		} else {
			System.out.println("아무일도 없습니다");
		}
	}

	void levelUp() {
		if (this.exp >= Pokemon.EXPMAX) {
			this.level += this.exp / Pokemon.EXPMAX;
			for (int i = 0; i < this.exp / Pokemon.EXPMAX; i++) {
				System.out.println(this.name + " 레벨업!");
			}
			this.exp %= Pokemon.EXPMAX;
		}
	}

	void printInfo() {
		System.out.printf("%s Lv.%d [%d/%d]입니다\n", this.name, this.level, this.exp, Pokemon.EXPMAX);
	}
}

public class PokeGame {

	public static void main(String[] args) {
		Pokemon pokemon = new Pokemon("피카츄");
		Pokemon pokemon2 = new Pokemon("피카츄", 10);
		pokemon2.printInfo();
		pokemon.printInfo();
		pokemon.game();
		pokemon.printInfo();
		pokemon.game();
		pokemon.printInfo();
		pokemon.game();
		pokemon.printInfo();
		Pokemon[] ar = new Pokemon[10];
		ar[0] = new Pokemon("고라파덕");
		ar[1] = new Pokemon("이상해씨");
		ar[2] = new Pokemon("파이리");
	}

}
