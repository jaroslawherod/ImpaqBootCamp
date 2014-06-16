package test.h2.repository;

import static org.hamcrest.CoreMatchers.equalTo;

import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import com.contacts.repository.H2PersonRepository;
import com.contacts.web.Person;

public class H2RepositoryTest {
	private H2PersonRepository h2=new H2PersonRepository();
	private Person person1=new Person("Test Name0","10000000","Test Address0");
	private Person person2=new Person("Test Name1","10000001","Test Address1");
	private Person person3=new Person("Test Name2","10000002","Test Address2");
	private Person person4=new Person("Test Name3","10000003","Test Address3");
	private Person person5=new Person("Test Name4","10000004","Test Address4");
	

	@Test
	public void shouldProperlyAddAndFindOnePersonToDatabase() {
		h2.createPerson(person1);
		Assert.assertThat(h2.findAllPersons().get(0).getId(), equalTo("10000000"));
		Assert.assertThat(h2.findAllPersons().get(0).getName(), CoreMatchers.equalTo("Test Name0"));
		Assert.assertThat(h2.findAllPersons().get(0).getAdres(), CoreMatchers.equalTo("Test Address0"));
		h2.deletePersonsTable();
	}

	@Test
	public void shouldProperlyAddAndFindManyPersonsInDatabase(){
		h2.createPerson(person1);
		h2.createPerson(person2);
		h2.createPerson(person3);
		h2.createPerson(person4);
		h2.createPerson(person5);
		
		List<Person> personsList=h2.findAllPersons();
		for(int i=0;i<personsList.size();i++){
		Assert.assertThat(personsList.get(i).getId(), equalTo("1000000"+i));
		Assert.assertThat(personsList.get(i).getName(), CoreMatchers.equalTo("Test Name"+i));
		Assert.assertThat(personsList.get(i).getAdres(), CoreMatchers.equalTo("Test Address"+i));
		h2.deletePersonsTable();
		}
	
	}

}
