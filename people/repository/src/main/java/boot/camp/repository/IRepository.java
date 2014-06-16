package boot.camp.repository;

import java.util.List;

import boot.camp.csv.model.Person;

public interface IRepository {
	
	public List<Person> getPeople() throws RepositoryException;
	
	public void savePerson(Person person) throws RepositoryException;
	
	public void dispose();

}
