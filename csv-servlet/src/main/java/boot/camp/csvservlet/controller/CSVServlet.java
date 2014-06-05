package boot.camp.csvservlet.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import boot.camp.csv.CSVConverterException;
import boot.camp.csv.CSVParserException;
import boot.camp.csv.CSVWriterException;
import boot.camp.csv.Person;
import boot.camp.csvservlet.model.CSVRepository;

public class CSVServlet extends HttpServlet {
	
	CSVRepository repository = new CSVRepository();
	ObjectMapper mapper = new ObjectMapper();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
		} catch (CSVParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CSVConverterException e) {
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
		} catch (CSVWriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
