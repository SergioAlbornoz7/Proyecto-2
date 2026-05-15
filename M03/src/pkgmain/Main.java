package pkgmain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) {
		String urlDatos = "jdbc:mysql://127.0.0.1:3307/ProyectoMixII?serverTimezone=UTC";
		String usuari = "super";
		String pass = "1234";
		
		try {
			//Cargar Driver//
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver cargado correctamente");
			//Cargar Driver//
			
			//Crear conección//
			Connection conn = DriverManager.getConnection(urlDatos, usuari, pass);
			System.out.println("Connection carregat correctament");
			//Crear conección//
			
			//Crear consulta//
			String querySql = "select num_battle from Battle_stats";
			Statement stmnt=conn.createStatement();
				//creamos un contenedor para ponder nuestros datos
			//4)ejecutar querry
			ResultSet rs=stmnt.executeQuery(querySql);
			while (rs.next()) {
				System.out.println("num_battle = "+rs.getInt(1));
			}
			
		} catch (ClassNotFoundException e) {
			//Cargar Driver//
			System.out.println("Driver no ha cargado correctamente");
			//Cargar Driver//
		
		//Crear conección//	
		} catch (SQLException e) {
			System.out.println("Connection no ha carregat correctament");
		}
		//Crear conección//
	}

}
