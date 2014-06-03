package boot.camp.day1.hello;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVParser {
	
	List<ArrayList<String>> lines;
	
	public void loadFromStream(InputStream stream, String separator) {
		InputStreamReader streamReader = new InputStreamReader(stream);
		BufferedReader reader = new BufferedReader(streamReader);
		lines = new ArrayList<ArrayList<String>>();
		String line = null;
		
		try {
			while((line = reader.readLine()) != null) {
				String[] values = line.split(separator);
				ArrayList<String> newLine = new ArrayList<String>(
						Arrays.asList(values));
				lines.add(newLine);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		for(List<String> line : lines) {
			for(String value : line) {
				buffer.append(value);
				buffer.append(" ");
			}
			buffer.append("\n");
		}
		return buffer.toString();
	}

	public static void main(String[] args) {
		File file = new File("test.csv");
		InputStream stream = null;
		CSVParser parser = new CSVParser();
		
		try {
			stream = new FileInputStream(file);
			parser.loadFromStream(stream, ",");
			System.out.println("Oddzielone przecinkiem:");
			System.out.println(parser.toString());
			
			stream = new FileInputStream(file);
			parser.loadFromStream(stream, ";");
			System.out.println("Oddzielone średnikiem:");
			System.out.println(parser.toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

}
