package boot.camp.csv;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

public class CSVWriter {

	public void write(OutputStream stream, List<Person> persons,
			String separator) throws CSVWriterException {
		OutputStreamWriter streamWriter = new OutputStreamWriter(stream);
		BufferedWriter writer = new BufferedWriter(streamWriter);
		StringBuilder builder = new StringBuilder();
		for (Person p : persons) {
			if(builder.length() > 0) {
				builder.append("\n");
			}
			builder.append(p.name);
			builder.append(separator);
			builder.append(p.pesel);
			builder.append(separator);
			builder.append(p.adress);
		}
		try {
			writer.write(builder.toString());
			writer.flush();
		} catch (IOException e) {
			throw new CSVWriterException(e);
		}

	}

}
