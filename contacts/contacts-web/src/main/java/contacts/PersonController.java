package contacts;

import java.io.IOException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boot.camp.day1.hello.Person;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PersonController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private PersonServiceImpl ps = null;

    public void init(ServletConfig config) throws ServletException {
	super.init(config);
	PersonRepositoryFactory rf = new PersonRepositoryFactory();
	try {
	    ps = new PersonServiceImpl(rf.create(config
		    .getInitParameter("my.repo")));
	} catch (NamingException e) {
	    throw new RuntimeException(e);
	}
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	ObjectMapper m = new ObjectMapper();

	try {
	    ps.create(m.readValue(request.getInputStream(), Person.class));
	} catch (Exception e) {
	    throw new RuntimeException(e);
	}
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	List<Person> people = null;
	try {
	    people = ps.findAll();
	} catch (Exception e) {
	    throw new RuntimeException(e);
	}

	ObjectMapper m = new ObjectMapper();

	m.writeValue(response.getWriter(), people);
    }
}