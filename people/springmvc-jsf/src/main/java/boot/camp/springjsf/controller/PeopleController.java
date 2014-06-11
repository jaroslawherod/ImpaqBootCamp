package boot.camp.springjsf.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import boot.camp.csv.model.Person;
import boot.camp.springjsf.service.PeopleService;

@Controller
@RequestMapping("/people")
public class PeopleController {
	
	private PeopleService peopleService;
	
	@Autowired
	public PeopleController(PeopleService service) {
		this.peopleService = service;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String getPeople(Model model) {
		Collection<Person> people = peopleService.getPeople();
		model.addAttribute("people", people);
		return "people";		
	}
	
	@RequestMapping(value="new", method = RequestMethod.POST)
	public String prepareSavePerson(@ModelAttribute("person") Person person, BindingResult result) {
		//peopleService.savePerson(person);
		return "new";
	}
	
	@RequestMapping(value="new", method = RequestMethod.POST)
	public String savePerson(@ModelAttribute("person") Person person, BindingResult result) {
		peopleService.savePerson(person);
		return "redirect:/people";
	}
}
