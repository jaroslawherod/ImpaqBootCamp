package boot.camp.springjsf.controller;

import java.io.Serializable;
import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.stereotype.Component;

import boot.camp.csv.model.Person;

@Component
@ManagedBean
@ViewScoped
public class PeopleBean implements Serializable {
	
	private static final long serialVersionUID = -2261750464846851227L;
	
	private Collection<Person> people;

	public Collection<Person> getPeople() {
		return people;
	}

	public void setPeople(Collection<Person> people) {
		this.people = people;
	}
}
