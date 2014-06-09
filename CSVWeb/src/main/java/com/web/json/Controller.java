package com.web.json;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
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
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class Controller extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
    
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
		String fullPath = context.getRealPath("/WEB-INF/inputdata.csv");
		PrintWriter out=response.getWriter();
		Gson gson=new Gson();
		List <Person> personsList=new LinkedList<Person>();
		InputStream fp=new FileInputStream(fullPath);
		BufferedReader br = new BufferedReader(new InputStreamReader(fp));
		JsonReader jr=new JsonReader(br);
		
		jr.setLenient(true);
		jr.beginArray();
		while(jr.hasNext()){
		personsList.add((Person) gson.fromJson(jr, Person.class));
		}
		jr.endArray();
		for(int i=0;i<personsList.size();i++)
		out.print(i+1+".\nFullname: "+personsList.get(i).getFullname()+"\nId:"+personsList.get(i).getId()+"\nAddress: "+personsList.get(i).getAddress()+"\n");
		doPost(request,response);
		br.close();
		
    }	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson=new Gson();
		//BufferedReader br=request.getReader();
		ServletContext context = getServletContext();
		String fullPath = context.getRealPath("/WEB-INF/inputdata.csv");
        Person newPerson=new Person();
        InputStream fp=new FileInputStream(fullPath);
		BufferedReader br = new BufferedReader(new InputStreamReader(fp));
		String jsonFile="";
		while(br.ready()){
			jsonFile=br.readLine();
		}
        FileWriter fw=new FileWriter(fullPath);
        newPerson.setId("512412341");
        newPerson.setFullname("asdqweasd Daze");
        newPerson.setAddress("ul. jakas 12 61-123 Poznan");
        String toJSon=gson.toJson(newPerson);
        fw.write(jsonFile.replaceAll("]", ","+toJSon+"]"));
        fw.flush();
        fw.close();
        br.close();
        
    }	
}
