package boot.camp.csv;

import java.util.ArrayList;
import java.util.List;

public class PersonConverter extends CSVConverter<Person>{

	public List<String> convert(Person person) {
		List<String> properties = new ArrayList<String>();
		properties.add(person.name);
		properties.add(person.pesel);
		properties.add(person.adress);
		return properties;
	}
	
	public Person convertBack(List<String> properties) throws CSVConverterException {
		if(properties.size() != 3) {
			throw new CSVConverterException("Incorrect number of arguments.");
		}
		return new Person(properties.get(0), properties.get(1), properties.get(2));
	}
}
