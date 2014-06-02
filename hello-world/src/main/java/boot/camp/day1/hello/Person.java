package boot.camp.day1.hello;

public class Person {
	private String imie;
	private String nazwisko;
	private int wiek;
	
	Person(){
	}
	
	Person(String imie, String nazwisko, int wiek) {
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.wiek = wiek;
	}
	
	public void setImie(String imie){
		this.imie = imie;
	}
	
	public void setNazwisko(String nazwisko){
		this.nazwisko = nazwisko;
	}
	
	public void setWiek(int wiek){
		this.wiek = wiek;
	}
	
	public String getImie(){
		return imie;
	}
	
	public String getNazwisko(){
		return nazwisko;
	}
	
	public int getWiek(){
		return wiek;
	}
}