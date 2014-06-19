package contacts;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class CsvPersonRepositoryInitializer implements ServletContextListener {
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
	String path = System.getProperty("user.home");
	File file = new File(path + "/database.csv");
	if (file.exists() == false) {
	    try {
		file.createNewFile();
	    } catch (IOException e) {
		throw new RuntimeException(e);
	    }
	}
    }
}