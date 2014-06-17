package com.contacts.web;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Controller extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
    
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Model model=new Model();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		model.printJson(out);
    }	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader readStream=request.getReader();
		Model model=new Model();
		model.getCsvPersonService().createPerson(model.validJson(readStream));
		model.getH2PersonService().createPerson(model.validJson(readStream));
	}	
}
