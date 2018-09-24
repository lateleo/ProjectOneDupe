package util;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class JDBCconnection {
	private static Connection conn;

	public static Connection getConnection() {
		if (conn == null) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				Properties props = new Properties();
				System.out.println(new File(".").getAbsolutePath());
				FileInputStream in = new FileInputStream("C:\\Users\\Art Burtch\\Documents\\workspace-sts-3.9.5.RELEASE\\ProjectOne\\src\\main\\resources\\properties\\connection.properties");
				props.load(in);
				String url = props.getProperty("url");
				String username = props.getProperty("username");
				String password = props.getProperty("password");
				
				conn = DriverManager.getConnection(url, username, password);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
}
