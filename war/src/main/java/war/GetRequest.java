package war;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.impaq.app.Container;
import com.impaq.app.FormatException;
import com.impaq.app.SaveCsv;

public class GetRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
    public GetRequest() {
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		/*
		String tmp1 = request.getParameter("name");
		String tmp2 = request.getParameter("id"); 
		String tmp3 = request.getParameter("address");	
		*/
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("<html><body>" + request.getParameterNames()+ "</body></html>");
		out.close();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		//String tmp = request.getParameterNames()("name") + request.getParameter("id") + request.getParameter("address");		
		//out.print("<html><body>"+tmp+"</body></html>");
		out.close();
		
		
		/*
		String path;
		Writer wr = null;
		SaveCsv writer = null;
		Boolean append = null;

		Container person = new Container(request.getParameter("name"), request.getParameter("id"), request.getParameter("address"));
		
		try {
			path = "C://Users//amac//Documents//sample.csv";
			File file = new File(path);
			append = file.exists() ? true : false;
			
			List<Container> list = new ArrayList<Container>();
			list.add(person);

			wr = new FileWriter(path, append);
			writer = new SaveCsv();
			writer.savetofile(wr, list);
			
		}  catch (FormatException exformat) {
			System.out.println(exformat.getMessage());
		} catch (FileNotFoundException fileex) {
			System.out.println
				("Nie znaleziono pliku. Sprawdź czy wprowadzona ścieżka jest poprawna: "+ fileex.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (wr != null) wr.close();
		}
*/
	}

}
