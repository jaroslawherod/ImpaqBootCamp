package contacts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import boot.camp.day1.hello.Person;

public class DbPersonRepository implements PersonRepository {
    private DataSource ds = null;

    public DbPersonRepository(DataSource ds) {
	this.ds = ds;
    }

    @Override
    public Person save(Person p) throws SQLException, ClassNotFoundException {
	Connection conn = ds.getConnection();
	PreparedStatement stat = conn.prepareStatement("INSERT INTO people (imie, nazwisko, wiek) VALUES (?,?,?);");
	stat.setString(1, p.getImie());
	stat.setString(2, p.getNazwisko());
	stat.setString(3, String.valueOf(p.getWiek()));
	
	stat.execute();

	stat.close();
	conn.close();

	return p;
    }

    @Override
    public List<Person> findAll() throws SQLException, ClassNotFoundException {
	List<Person> people = new ArrayList<Person>();
	Connection conn = ds.getConnection();
	Statement stat = conn.createStatement();

	ResultSet rs = stat.executeQuery("select * from people");
	while (rs.next()) {
	    people.add(new Person(rs.getString("imie"), rs
		    .getString("nazwisko"), rs.getInt("wiek")));
	}
	stat.close();
	conn.close();

	return people;
    }

}