package contactscsvservice;

public class Contact {
    private String name, id, address;

    public Contact() {};

    public Contact(String name, String id, String address) {
	this.name = name;
	this.id = id;
	this.address = address;
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

    public String getAddress() {
	return address;
    }

    public void setAddress(String address) {
	this.address = address;
    }

}
