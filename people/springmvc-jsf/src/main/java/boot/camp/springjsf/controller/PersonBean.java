package boot.camp.springjsf.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.stereotype.Component;

import boot.camp.csv.model.Person;

@Component
@ManagedBean
@ViewScoped
public class PersonBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5631842480338277874L;
	
	private Person person;

	public PersonBean() {
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
}
