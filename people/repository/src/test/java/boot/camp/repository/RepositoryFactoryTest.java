package boot.camp.repository;

import static org.junit.Assert.*;

import java.lang.reflect.Type;

import org.junit.Test;

public class RepositoryFactoryTest {
	
	RepositoryFactory factory = new RepositoryFactory();
	
	@Test
	public void testH2Repository() throws RepositoryException {
		Type expected = H2Repository.class;
		IRepository repository = factory.createRepository(expected, new String[] {"jdbc:h2:~/testtest", "sa", ""});
		assertEquals(expected, repository.getClass());
	}
	
	@Test
	public void testCSVRepository() throws RepositoryException {
		Type expected = CSVRepository.class;
		String filename = System.getProperty("user.home") + "\\repository.csv";
		IRepository repository = factory.createRepository(expected, new String[] {filename});;
		assertEquals(expected, repository.getClass());
	}
	
	@Test(expected=RepositoryException.class)
	public void testNotEnoughParameters() throws RepositoryException {
		Type expected = H2Repository.class;
		String filename = System.getProperty("user.home") + "\\repository.csv";
		IRepository repository = factory.createRepository(expected, new String[] {filename});
		assertEquals(expected, repository.getClass());
	}

}
