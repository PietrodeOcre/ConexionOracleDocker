package Conectando;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class TestConnect {
	
	Conection conexion = Conection.getInstance();

	static Connection conn;

	public static void main(String[] args) {

		/*
		 * CREATE TABLE departamentos(
   depto_id NUMBER(9),
   nombre VARCHAR2(100),
   localidad VARCHAR2(300),
   fecha_creacion DATE DEFAULT SYSDATE
   CONSTRAINT departamentos_pk PRIMARY KEY(depto_id)
);
		 */
		
		String sql = "CREATE TABLE customers" + 
				"( customer_id number(10) NOT NULL," + 
				"  customer_name varchar2(70) NOT NULL," + 
				"  address varchar2(50)," + 
				"  city varchar2(50)," +  
				"  zip_code varchar2(10)," + 
				"  CONSTRAINT customers_pk PRIMARY KEY (customer_id))";
		
		
		String query = "INSERT INTO customers (customer_id,customer_name,address,city,zip_code) "
				+ "VALUES (10,'JOSE LUIS RUIZ','Calle Bernabeu','BARCELONA',28024)";
		
		//System.out.println(crearTabla(sql));
		
		//System.out.println(registrar(query));
		
		consultaDatosSubasta();
		
	}
	
	public static String obtenerFechaFormateada() {
		LocalDate fecha = LocalDate.now();
	    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	    return fecha.format(dtf);
	}
	
	public static boolean crearTabla(String sql) {
		boolean registrar = false;
		
		Statement stm= null;
		Connection conn=null;

		try {			
			conn=Conection.getConnection();
			stm= conn.createStatement();
			stm.execute(sql);
			registrar=true;
			stm.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("Error: Tabla no creada");
			e.printStackTrace();
		}
		return registrar;
	}
	
	public static boolean registrar(String sql) {
		boolean registrar = false;
		
		Statement stm= null;
		Connection con=null;

		try {			
			conn=Conection.getConnection();
			stm= conn.createStatement();
			stm.execute(sql);
			registrar=true;
			stm.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("Error: No insert");
			e.printStackTrace();
		}
		return registrar;
	}
	
	public static void consultaDatosSubasta() {
		String query = "select * from customers";

		try {
			conn = Conection.getConnection();
			
			if(conn != null) {
				Statement serverStatement = conn.createStatement();
				ResultSet resultSet = serverStatement.executeQuery(query);
				while(resultSet.next()) {
						
						System.out.println("ID: " + resultSet.getInt(1));
						System.out.println("NOMBRE: " + resultSet.getString(2));
						System.out.println("DIRECCION: " + resultSet.getString(3));
						System.out.println("CIUDAD: " + resultSet.getString(4));
						System.out.println("CODIGO POSTAL: " + resultSet.getString(5));
						
				
				}
			}
			System.out.println("Conectado.");
		} catch (SQLException ex) {
			System.out.println("Datos No mostrados. Error.");
			ex.printStackTrace();
		} finally {

			try {
				System.out.println("Datos mostrados");
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
