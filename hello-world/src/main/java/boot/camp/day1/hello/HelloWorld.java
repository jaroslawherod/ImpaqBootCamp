package boot.camp.day1.hello;

import java.util.Scanner;

public class HelloWorld {

    public static void main(String[] args) throws Exception {
        String fp;
    	Scanner filePathReader=new Scanner(System.in);
    	System.out.print("Podaj sciezke do pliku CVS: ");
    	fp=filePathReader.next();
    	csvParser parseFile=new csvParser(fp);
    	
    	parseFile.preprocessCSVFile();
    	filePathReader.close();
    }

}
