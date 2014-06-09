package boot.camp.repository;

import java.io.IOException;
import java.util.List;

import boot.camp.csv.CSVConverterException;
import boot.camp.csv.CSVParserException;
import boot.camp.csv.CSVWriterException;
import boot.camp.csv.model.Person;

public interface IRepository {
	
	public List<Person> getPeople() throws RepositoryException;
	
	public void savePerson(Person person) throws RepositoryException;

}
