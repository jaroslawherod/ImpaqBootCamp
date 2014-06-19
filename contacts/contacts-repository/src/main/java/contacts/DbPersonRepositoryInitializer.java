package contacts;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.h2.jdbcx.JdbcDataSource;

public class DbPersonRepositoryInitializer implements ServletContextListener {
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
	try {
	    System.setProperty(Context.INITIAL_CONTEXT_FACTORY,
		    "org.eclipse.jetty.jndi.InitialContextFactory");
	    Context ctx = (Context) (new InitialContext());
	    JdbcDataSource ds = new JdbcDataSource();
	    ds.setURL("jdbc:h2:file:~/people");
	    // ds.setUser("");
	    // ds.setPassword("");
	    ctx.createSubcontext("jdbc");
	    ctx.bind("jdbc/dsPeople", ds);

	    Connection conn = ds.getConnection();

	    DatabaseMetaData dbm = conn.getMetaData();
	    ResultSet tables = dbm.getTables(null, null, "PEOPLE", null);
	    if (!tables.next()) {
		Statement stat = conn.createStatement();
		stat.execute("create table people(imie varchar(32) primary key, nazwisko varchar(64), wiek int)");
		stat.close();
	    }

	    conn.close();
	} catch (SQLException | NamingException e) {
	    throw new RuntimeException(e);
	}
    }
}
