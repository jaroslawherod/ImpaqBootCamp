package boot.camp.day1.hello;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Scanner;

public class HelloWorld {

    public static void main(String[] args) throws Exception {
        String fp;
    	Scanner filePathReader=new Scanner(System.in);
    	LinkedList<Person> personalData=new LinkedList<Person>();
    	System.out.print("Podaj sciezke do pliku CVS: ");
    	fp=filePathReader.next();
    	InputStream fileStream=new FileInputStream(fp);
    	CSVParser csvFile=new CSVParser();
    	personalData=csvFile.preprocessCSVFile(fileStream);
    	for (Person s : personalData) { 
    		s.printPerson();
    		System.out.println("");
    	}
    	filePathReader.close();
    }

}
