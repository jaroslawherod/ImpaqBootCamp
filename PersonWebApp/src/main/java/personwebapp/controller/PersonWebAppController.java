package personwebapp.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.h2.jdbcx.JdbcConnectionPool;

import personwebapp.service.PersonWebAppService;
import personwebapp.service.ServiceException;
import repository.csvservice.PersonWebAppRepository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import contactscsvservice.Contact;

public class PersonWebAppController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String path = "C://Users//amac//Documents//sample.csv";

	// private String path = "C://Users//Andrzej//Documents//sample.csv";

	@Override
	public void init(ServletConfig config) throws ServletException {
		// super.init(config);

		JdbcConnectionPool cp = JdbcConnectionPool.create("jdbc:h2:~/test", "sa", "sa");
		Connection conn;
		
		try {
			
			conn = cp.getConnection();
			conn.getMetaData();
	        conn.createStatement().execute("SHOW TABLES");
	        cp.dispose();
	        
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/json; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		PersonWebAppService personService;
		List<Contact> people;
		Gson jsonService;
		try {

			jsonService = new Gson();
			personService = new PersonWebAppService(this.path,
					new PersonWebAppRepository(this.path));
			people = personService.findAll();

			PrintWriter writer = response.getWriter();
			writer.print(jsonService.toJson(people));
			writer.close();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/json; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		BufferedReader jsonStream;
		List<Contact> people;
		Gson jsonService;
		Type list;
		PersonWebAppService csvWriter;

		try {

			jsonStream = request.getReader();
			jsonService = new Gson();
			list = new TypeToken<List<Contact>>() {
			}.getType();
			people = jsonService.fromJson(jsonStream, list);
			csvWriter = new PersonWebAppService(this.path,
					new PersonWebAppRepository(this.path));
			csvWriter.update(people);

		} catch (ServiceException e) {
			response.sendError(422);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
}
