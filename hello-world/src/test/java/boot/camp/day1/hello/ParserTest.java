package boot.camp.day1.hello;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;
import java.util.List;

import org.junit.Test;

public class ParserTest {

    @Test(expected = FormatException.class)
    public void WrongReaderException() throws Exception {
	Parser tester = new Parser();
	StringReader sr = new StringReader("a");
	tester.parse(sr);
    }

    @Test(expected = FormatException.class)
    public void WrongFileFormat() throws Exception {
	URL resource = this.getClass().getClassLoader()
		.getResource("wrongsample.csv");
	InputStream isr = resource.openStream();
	Reader reader = new InputStreamReader(isr);
	Parser person = new Parser();
	person.parse(reader);
	
    }

    @SuppressWarnings("unchecked")
    @Test
    public void CheckDataFormFile() throws Exception {
	URL resource = this.getClass().getClassLoader()
		.getResource("sample.csv");
	InputStream isr = resource.openStream();
	Reader reader = new InputStreamReader(isr, "UTF-8");
	Parser person = new Parser();
	List<Container> l = person.parse(reader);

	assertThat( l.get(0).getName(), anyOf(is("Jarosław Heród")) );
	assertThat( l.get(1).getName(), anyOf(is("Jan Kowalski")) );
	
	assertThat( l.get(0).getId(), anyOf(is("800805123456")) );
	assertThat( l.get(1).getId(), anyOf(is("851205123456")) );
	
	assertThat( l.get(0).getAddress(), anyOf(is("ul. Bura 15 60-222 Poznań")) );
	assertThat( l.get(1).getAddress(), anyOf(is("ul. Nijaka 15 01-222 Warszawa")) );
	
    }

    
    
    
    
}
