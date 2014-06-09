package war;


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

import com.google.gson.JsonObject;
import com.impaq.app.Container;
import com.impaq.app.CsvReader;

public class Request extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//private String path = "C://Users//amac//Documents//sample.csv";
	private String path = "C://Users//Andrzej//Documents//sample.csv";
	
	//czy mozna do klasy reader w poprzednim projekcie dac strema zamiast readera ?
	//co zrobic z kodowaniem znakow przy odczytywaniu z linii ?
	
    public Request() {
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CsvReader csvread;
		Reader reader;
		List<Container> people;
		CsvDataGetter getdata;
		List<JsonObject> jsonlist;
		try
		{
			csvread = new CsvReader();
			reader = new FileReader(new File(this.path) );
			people = csvread.read(reader);
			getdata = new CsvDataGetter();
			jsonlist = getdata.getjson(people);
			
			//response.setHeader("Content-Disposition", "attachment; filename=\"people.json\"");
			PrintWriter pw = response.getWriter();
			pw.print(jsonlist.toString());
			pw.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		Enumeration<String> tmp = request.getParameterNames();
		
		CsvDataWriter csvwriter = new CsvDataWriter();
		List<Container> list = csvwriter.getlistfromrequest(tmp.nextElement());
		//csvwriter.appendtocsv(this.path, tmp.nextElement());
		
		out.println("<html><body>");
		out.println(list.get(0).getName() + " " + list.get(0).getAddress() + " " + list.get(0).getId());
		out.println(list.get(1).getName() + " " + list.get(1).getAddress() + " " + list.get(1).getId());
		out.println("</body></html>");
		out.close();
		
		
		
		
		
	}

}
