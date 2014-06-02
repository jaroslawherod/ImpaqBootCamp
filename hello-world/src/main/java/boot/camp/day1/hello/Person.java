package boot.camp.day1.hello;

public class Person {
	
	public Person(String name,String id,String adres){
		this.name=name;
		this.id=id;
		this.adres=adres;
	}
	public String name;
	private String id;
	private String adres;
	
	public void printPerson(){
		System.out.println("ID: " +id);
		System.out.println("Name: " +name);
		System.out.println("Address: " +adres);
	}
}
