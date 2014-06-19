package contacts;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PersonRepositoryFactory {
    public PersonRepository create(String type) throws NamingException {
	PersonRepository repo = null;

	if (type.equals("CsvPersonRepository")) {
	    repo = new CsvPersonRepository();
	} else if (type.equals("DbPersonRepository")) {
	    Context ic = new InitialContext();
	    repo = new DbPersonRepository(
		    (DataSource) ic.lookup("jdbc/dsPeople"));
	}
	
	return repo;
    }
}