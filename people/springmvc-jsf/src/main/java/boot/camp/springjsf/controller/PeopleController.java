package boot.camp.springjsf.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import boot.camp.csv.model.Person;
import boot.camp.springjsf.service.PeopleService;

@Controller
@RequestMapping("/people")
public class PeopleController {

	@Autowired
	private PeopleService peopleService;

	@Autowired
	PeopleBean peopleBean;

	@Autowired
	PersonBean personBean;

	@Autowired
	public PeopleController(PeopleService service) {
		this.peopleService = service;
	}

	Person testPerson;

	@RequestMapping(method = RequestMethod.GET)
	public String getPeople() {
		Collection<Person> people = peopleService.getPeople();
		peopleBean.setPeople(people);
		return "people";
	}

	@RequestMapping(value = "new", method = RequestMethod.GET)
	public String prepareSavePerson() {
		// Person person = new Person();
		// testPerson = person;
		// personBean.setPerson(person);
		return "new";
	}

	@RequestMapping(value = "new", method = RequestMethod.POST)
	public String savePerson(@RequestParam(value = "name") String name,
			@RequestParam(value = "pesel") String pesel,
			@RequestParam(value = "address") String address) {
		Person person = new Person(name, pesel, address);
		peopleService.savePerson(person);
		return "redirect:/people";
	}
}
