package contactscsvservice;

import java.io.BufferedWriter;
import java.io.Writer;
import java.util.List;

public class CsvWriter {

	public void save(Writer wr, List<Contact> people) throws Exception {
		String line = "";
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(wr);
			StringBuilder stringBuilder = new StringBuilder(line);

			for (Contact i : people) {
				stringBuilder.append(i.getName().concat(","));
				stringBuilder.append(i.getId().concat(","));
				stringBuilder.append(i.getAddress().concat("\n"));
				bufferedWriter.write(stringBuilder.toString());
				stringBuilder.setLength(0);
			}
			bufferedWriter.close();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}
