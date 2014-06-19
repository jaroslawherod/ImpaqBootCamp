package contacts;

import java.util.List;

import boot.camp.day1.hello.Person;

public class PersonServiceImpl implements PersonService {
    private PersonRepository repository = null;

    public PersonServiceImpl() {
    }

    public PersonServiceImpl(PersonRepository repository) {
	this.repository = repository;
    }

    public Person create(Person person) throws Exception {
	return repository.save(person);
    }

    public List<Person> findAll() throws Exception {
	return repository.findAll();
    }
}