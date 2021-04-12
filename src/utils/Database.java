package utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {

	private static String url = "jdbc:mysql://localhost:3306/ecare";

	private Connection connection;

	private Database() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Properties props = new Properties();
			props.setProperty("user", "root");
			props.setProperty("password", "");
			connection = DriverManager.getConnection(url,props);			
		} catch (SQLException | ClassNotFoundException ex) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	private static final Database DATABASE = new Database();

	public static Database getInstance() {
		return DATABASE;
	}
	
	public Connection getConnection() {
		return connection;
	}
}
