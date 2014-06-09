package boot.camp.repository;

import java.sql.Connection;
import java.sql.SQLException;

import org.h2.jdbcx.JdbcConnectionPool;

public class ConnectionPool {
	
	private JdbcConnectionPool pool;
	
	public ConnectionPool(String url, String user, String password) {
		super();
		pool = JdbcConnectionPool.create(url, user, password);
	}
	
	public Connection getConnection() throws SQLException {
		return pool.getConnection();
	}
	
	public void  dispose() {
		pool.dispose();
	}
}
