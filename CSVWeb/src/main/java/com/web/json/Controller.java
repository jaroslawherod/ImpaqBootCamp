package com.web.json;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import com.google.gson.Gson;

public class Controller extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		Gson gson=new Gson();
		List <Person> personsList=new LinkedList<Person>();
		InputStream fp=new FileInputStream("C:/inputdata.csv");
		BufferedReader br = new BufferedReader(new InputStreamReader(fp));
		personsList.add(gson.fromJson(br, Person.class));
		for(int i=0;i<personsList.size();i++)
		out.print("Fullname: "+personsList.get(i).getFullname()+"\nId: "+personsList.get(i).getId()+"\nAddress: "+personsList.get(i).getAddress());
		br.close();
    }	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson=new Gson();
        Person newPerson=new Person();
        OutputStream fp=new FileOutputStream("C:/inputdata.csv");
        newPerson.setId("123124123");
        newPerson.setFullname("Imie Nazwisko");
        newPerson.setAddress("ul. ulica 12 61-123 Poznan");
        String toJSon=gson.toJson(newPerson);
        response.setStatus(200);
        fp.write(toJSon.getBytes());
        fp.close();
    }	
}
