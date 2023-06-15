/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Noelardau
 */
public class Bdd {
    
    public static Connection connection;
    
   public static Connection getConnection() {
		try {
                       Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/chanfoui","root","");
		
                }catch(SQLException e) {
			
                        e.printStackTrace();
                        
		}catch(ClassNotFoundException e) {
                        
                    e.printStackTrace();
                    
		}
		
		return connection;
	}
    
    
}
