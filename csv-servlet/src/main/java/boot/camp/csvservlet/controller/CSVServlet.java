package boot.camp.csvservlet.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import boot.camp.csv.model.Person;
import boot.camp.repository.IRepository;
import boot.camp.repository.RepositoryException;
import boot.camp.repository.RepositoryFactory;
import boot.camp.service.RepositoryService;
import boot.camp.service.RepositoryServiceException;

public class CSVServlet extends HttpServlet {
	
	private ObjectMapper mapper;
	private RepositoryService service;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		mapper = new ObjectMapper();
		RepositoryFactory factory = new RepositoryFactory();
		System.out.print("Parameter names: ");
		for (Enumeration<String> e = config.getInitParameterNames(); e.hasMoreElements();)
		       System.out.println(e.nextElement());
		try {
			Class<?> serviceClass = Class.forName(config.getInitParameter("serviceClass"));
			String[] serviceParameters = config.getInitParameter("serviceParameters").split(",");
			IRepository repository = factory.createRepository(serviceClass, serviceParameters);
			service = new RepositoryService(repository);
		} catch (ClassNotFoundException | RepositoryException e) {
			throw new ServletException(e);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			List<Person> people = service.getPeople();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			PrintWriter writer = resp.getWriter();
			mapper.writeValue(writer, people);
			writer.flush();
			writer.close();
			resp.setStatus(200);
		} catch (RepositoryServiceException e) {
			throw new ServletException(e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Person person = mapper.readValue(req.getInputStream(), Person.class);
		try {
			service.savePerson(person);
			resp.setStatus(200);
		} catch (RepositoryServiceException e) {
			throw new ServletException(e);
		}
	}
}
