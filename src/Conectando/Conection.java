package Conectando;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conection {
	
	private static Conection connect; 
	
	private Conection() {
		
	}
	
	public static Conection getInstance() {
		
		if (connect == null) {
			connect = new Conection();
			
		}
		return connect;
		
	}
	
	public static Connection conn;
	
	public static Connection getConnection() {
		
		if(conn == null) {
			try {
				Class.forName("oracle.jdbc.OracleDriver");
				String url = ("jdbc:oracle:thin:@172.17.0.2:1521:ORCL18");
				try {
					conn = DriverManager.getConnection(url,"SYSTEM","Welcome_1");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
				
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			}
			
		}
		return conn;
	}
	
	
}
