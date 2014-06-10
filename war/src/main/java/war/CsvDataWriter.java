package war;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.impaq.app.Container;
import com.impaq.app.CsvWriter;
import com.impaq.app.FormatException;

public class CsvDataWriter {

	public List<Container> getlistfromrequest(String data) {
		JsonElement jsonel = new JsonParser().parse(data);
		JsonArray array = jsonel.getAsJsonArray();
		Iterator<JsonElement> it = array.iterator();
		List<Container> list = new ArrayList<Container>();

		while (it.hasNext()) {
			JsonElement json2 = (JsonElement) it.next();
			Gson gson = new Gson();
			Container person = gson.fromJson(json2, Container.class);
			list.add(person);
		}
		return list;
	}

	public void appendtocsv(String path, List<Container> list)
			throws IOException {
		Writer wr = null;
		CsvWriter writer = null;
		Boolean append = null;

		try {
			File file = new File(path);
			append = file.exists() ? true : false;

			wr = new FileWriter(path, append);
			writer = new CsvWriter();
			writer.savetofile(wr, list);

		} catch (FormatException exformat) {
			System.out.println(exformat.getMessage());
		} catch (FileNotFoundException fileex) {
			System.out
					.println("Nie znaleziono pliku. Sprawdź czy wprowadzona ścieżka jest poprawna: "
							+ fileex.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (wr != null)
				wr.close();
		}
	}

}
