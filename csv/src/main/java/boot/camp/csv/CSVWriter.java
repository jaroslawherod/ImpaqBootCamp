package boot.camp.csv;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

public class CSVWriter<T> {
	
	CSVConverter<T> converter;

	public CSVWriter(CSVConverter<T> converter) {
		super();
		this.converter = converter;
	}

	public void write(OutputStream stream, List<T> elements,
			String separator, boolean append) throws CSVWriterException {
		OutputStreamWriter streamWriter = new OutputStreamWriter(stream);
		BufferedWriter writer = new BufferedWriter(streamWriter);
		StringBuilder builder = new StringBuilder();
		for (T element : elements) {
			if(builder.length() > 0 || append) {
				builder.append("\n");
			}
			List<String> values = converter.convert(element);
			for(int i = 0; i < values.size(); i++) {
				if(i != 0) {
					builder.append(separator);
				}
				builder.append(values.get(i));				
			}
		}
		try {
			writer.write(builder.toString());
			writer.flush();
		} catch (IOException e) {
			throw new CSVWriterException(e);
		}

	}
	
	public void write(OutputStream stream, T element,
			String separator, boolean append) throws CSVWriterException {
		OutputStreamWriter streamWriter = new OutputStreamWriter(stream);
		BufferedWriter writer = new BufferedWriter(streamWriter);
		StringBuilder builder = new StringBuilder();
		
		List<String> values = converter.convert(element);
		if(append) {
			builder.append("\n");
		}
		
		for(int i = 0; i < values.size(); i++) {
			if(i != 0) {
				builder.append(separator);
			}
			builder.append(values.get(i));				
		}
		
		try {
			writer.write(builder.toString());
			writer.flush();
		} catch (IOException e) {
			throw new CSVWriterException(e);
		}
		
	}

}
