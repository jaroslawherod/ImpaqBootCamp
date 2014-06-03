package boot.camp.csv;

public class Person {
	
	String name;
	String pesel;
	String adress;
	
	
	public Person(String name, String peselString,
			String adresString) {
		super();;
		this.name = name;
		this.pesel = peselString;
		this.adress = adresString;
	}
	
	public String getName() {
		return name;
	}
	public String getPesel() {
		return pesel;
	}
	public String getAdress() {
		return adress;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", pesel=" + pesel
				+ ", adress=" + adress + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adress == null) ? 0 : adress.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((pesel == null) ? 0 : pesel.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (adress == null) {
			if (other.adress != null)
				return false;
		} else if (!adress.equals(other.adress))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pesel == null) {
			if (other.pesel != null)
				return false;
		} else if (!pesel.equals(other.pesel))
			return false;
		return true;
	}
}
