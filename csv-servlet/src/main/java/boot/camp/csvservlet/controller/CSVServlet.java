package boot.camp.csvservlet.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import boot.camp.csv.CSVConverterException;
import boot.camp.csv.CSVParserException;
import boot.camp.csv.CSVWriterException;
import boot.camp.csv.model.Person;
import boot.camp.repository.H2Repository;
import boot.camp.repository.IRepository;
import boot.camp.repository.CSVRepository;
import boot.camp.repository.RepositoryException;

public class CSVServlet extends HttpServlet {
	
	private IRepository repository;
	private ObjectMapper mapper;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

	@Override
	public void init() throws ServletException {
		Connection connection = null;
		try {
			Class.forName("org.h2.Driver");
			connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
			System.out.println(connection == null);
			repository = new H2Repository(connection);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		mapper = new ObjectMapper();
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			List<Person> people = repository.getPeople();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			PrintWriter writer = resp.getWriter();
			mapper.writeValue(writer, people);
			writer.flush();
			writer.close();
			resp.setStatus(200);
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Person person = mapper.readValue(req.getInputStream(), Person.class);
		try {
			repository.savePerson(person);
			resp.setStatus(200);
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
