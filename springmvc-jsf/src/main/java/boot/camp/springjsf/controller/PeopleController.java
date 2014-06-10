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
import boot.camp.springjsf.service.PeopleServiceException;

@Controller
@RequestMapping("/persons")
public class PeopleController {
	
	@Autowired
	private PeopleService peopleService;
	
	@RequestMapping(method = RequestMethod.GET)
	public void getPeople(Model model) {
		try {
			Collection<Person> people = peopleService.getPeople();
			model.addAttribute("people", people);
		} catch (PeopleServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void savePerson(@ModelAttribute("person") Person person, BindingResult result) {
		try {
			peopleService.savePerson(person);
		} catch (PeopleServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
