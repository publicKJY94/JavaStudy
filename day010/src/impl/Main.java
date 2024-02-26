package impl;

class LgTV implements TVimpl{
	boolean power;
	int sound;
	@Override
	public void turnOn() {
		power = true;
		System.out.println("lg티비 켜짐");
	}

	@Override
	public void turnOff() {
		power = false;
	}

	public void soundUp() {
		this.sound++;
		System.out.println("현재 볼륨 : "+ this.sound);
	}

	public void soundDown() {
		this.sound--;
		System.out.println("현재 볼륨 : "+ this.sound);
		
	}
}

class SamsungTV implements TVimpl{
	boolean energe;
	int volume;
	
	@Override
	public void turnOn() {
		energe = true;
		System.out.println("삼성티비");
	}
	
	@Override
	public void turnOff() {
		energe = true;
		System.out.println("굿바이!");
		
	}
	
	public void volumeUp() {
		this.volume++;
		System.out.println(this.volume);
	}
	
	public void volumeDown() {
		this.volume--;
		System.out.println(this.volume);
	}
}

public class Main {

	public static void main(String[] args) {
		SamsungTV samsungTV = new SamsungTV();
		LgTV lgTV = new LgTV();
		samsungTV.turnOff();
		samsungTV.volumeUp();
		lgTV.turnOn();
		lgTV.soundDown();
	}

}
