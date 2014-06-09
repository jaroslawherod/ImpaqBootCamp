package com.impaq.app;

import java.io.BufferedWriter;
import java.io.Writer;
import java.util.List;

public class SaveCsv {
	public SaveCsv() {
	}

	public void savetofile(Writer wr, List<Container> people) throws Exception {
		String line = "";
		try {
			BufferedWriter bw = new BufferedWriter(wr);
			StringBuilder sb = new StringBuilder(line);

			for (Container i : people) {
				sb.append(i.getName().concat(","));
				sb.append(i.getId().concat(","));
				sb.append(i.getAddress().concat("\n"));
				bw.write(sb.toString());
				sb.setLength(0);
			}
			bw.close();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

}
