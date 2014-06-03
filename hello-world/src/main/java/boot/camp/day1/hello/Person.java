package boot.camp.day1.hello;

public class Person {
	
	private String name;
	private String id;
	private String adres;
	
	public Person(String name,String id,String adres){
		this.name=name;
		this.id=id;
		this.adres=adres;
	}

	public void printPerson(){
		System.out.println("ID: " +id);
		System.out.println("Name: " +name);
		System.out.println("Address: " +adres);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}
	
	
}
