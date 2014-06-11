package personwebapp.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import personwebapp.service.PersonWebAppService;
import personwebapp.service.ServiceException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import contactscsvservice.Contact;

public class PersonWebAppController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String path = "C://Users//amac//Documents//sample.csv";
	// private String path = "C://Users//Andrzej//Documents//sample.csv";

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
			personService = new PersonWebAppService(this.path);
			people = personService.findAll();
			
			PrintWriter writer = response.getWriter();
			writer.print( jsonService.toJson(people) );
			writer.close();
			
		} catch (ServiceException e) {
			e.printStackTrace(); // tutaj rzucic jakis http
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
			list = new TypeToken<List<Contact>>(){}.getType();
			people = jsonService.fromJson(jsonStream, list);
			csvWriter = new PersonWebAppService(this.path);
			csvWriter.update(people);

		} catch (Exception e) {
			e.printStackTrace(); //tutaj rzucic jakis kod http
		}
		
	}
}
