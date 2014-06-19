package boot.camp.day1.hello;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

public class Writer {
    public void write(List<Person> people, java.io.Writer fw)
	    throws IOException, WriterException {
	BufferedWriter bw = null;
	try {
	    if (people.isEmpty() == true)
		throw new WriterException("List is empty");

	    bw = new BufferedWriter(fw);

	    for (Person tmp : people) {
		bw.write(tmp.getImie() + "," + tmp.getNazwisko() + ","
			+ Integer.toString(tmp.getWiek()));
		bw.newLine();
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	} finally {
	    if (bw != null)
		bw.close();
	}
    }
}