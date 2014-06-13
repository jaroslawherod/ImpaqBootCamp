package personwebapp.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import personwebapp.service.PersonWebAppService;
import personwebapp.service.ServiceException;
import repository.factory.Repository;
import repository.factory.RepositoryFactory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import contactscsvservice.Contact;

public class PersonWebAppController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final Logger logger = Logger.getLogger(PersonWebAppController.class);
	
	private String path = "C://Users//amac//sample.csv";
	// private String path = "C://Users//Andrzej//sample.csv";

	private Repository repo;
	
	@Override
	public void init(ServletConfig config) throws ServletException {

		RepositoryFactory factory = new RepositoryFactory();
		try {
			
			this.repo = factory.getRepository( config.getInitParameter("RepositoryParam"), path );
			
		} catch (Exception e) {
			throw new ServletException("Problem z inicjalizacja servletu.", e);
		}
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/json; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		PersonWebAppService personService;
		List<Contact> people;
		Gson jsonService;
		try {

			jsonService = new Gson();
			personService = new PersonWebAppService(this.repo);
			people = personService.findAll();

			PrintWriter writer = response.getWriter();
			writer.print(jsonService.toJson(people));
			writer.close();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.sendError(500);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/json; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		BufferedReader jsonStream = null;
		List<Contact> people;
		Gson jsonService;
		Type list;
		PersonWebAppService csvWriter;

		try {

			jsonStream = request.getReader();
			jsonService = new Gson();
			list = new TypeToken<List<Contact>>() {}.getType();
			people = jsonService.fromJson(jsonStream, list);
			csvWriter = new PersonWebAppService(this.repo);
			csvWriter.create(people);
			
			PrintWriter writer = response.getWriter();
			writer.print("Success!");
			writer.close();
			
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			response.sendError(422);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.sendError(500);
		}
		finally {
			if(jsonStream != null)
				jsonStream.close();
		}

	}
	
}
