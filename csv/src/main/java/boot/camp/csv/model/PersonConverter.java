package boot.camp.csv.model;

import java.util.ArrayList;
import java.util.List;

import boot.camp.csv.CSVConverter;
import boot.camp.csv.CSVConverterException;
import boot.camp.csv.model.Person;

public class PersonConverter extends CSVConverter<Person>{

	public List<String> convert(Person person) {
		List<String> properties = new ArrayList<String>();
		properties.add(person.getName());
		properties.add(person.getPesel());
		properties.add(person.getAdress());
		return properties;
	}
	
	public Person convertBack(List<String> properties) throws CSVConverterException {
		if(properties.size() != 3) {
			throw new CSVConverterException("Incorrect number of arguments.");
		}
		return new Person(properties.get(0), properties.get(1), properties.get(2));
	}
}
