package com.impaq.app.contoller;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.impaq.app.Container;
import com.impaq.app.CsvReader;
import com.impaq.app.service.CsvDataService;

public class Request extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Request() {
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

			CsvDataService cds = new CsvDataService();
			String jsonresult = cds.getdatafromrequest();
			
			response.setContentType("application/json; charset=UTF-8");
			//response.setCharacterEncoding("UTF-8");
			//request.setCharacterEncoding("UTF-8");
			
			PrintWriter pw = response.getWriter();
			pw.print( jsonresult );
			pw.close();
			
		
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/plain; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();		
		Enumeration<String> tmp = request.getParameterNames();
		CsvDataService csvwriter = new CsvDataService();
		List<Container> list = csvwriter.getlistfromrequest(tmp.nextElement());
		csvwriter.appendtocsv(this.path, list);

		//out.println("<html><body>");
		//out.println(list.get(0).getName() + " " + list.get(0).getAddress()	+ " " + list.get(0).getId());
		//out.println(list.get(1).getName() + " " + list.get(1).getAddress()	+ " " + list.get(1).getId());
		//out.println("</body></html>");
		//out.close();
		
		this.doGet(request, response);

	}

}
