/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Noelardau
 */
public class User {
    
    
    private int idUser;
    private String pseudo;
    private String password;
    
    
    public User(){
                  
    }
    
    public User(int id, String pseudo,String password){
        this.idUser = id;
        this.pseudo = pseudo;
        this.password = password;      
    } 
    
    
    public User(String pseudo,String password){
       
        this.pseudo = pseudo;
        this.password = password;      
    }
    
   public int getIdUser(){
       return this.idUser;
   }
   
   public String getPseudo(){
       return this.pseudo;
   }
   
   public void setId(int id){
       this.idUser = id;
   }
    public void setPseudo(String pseudo){
       this.pseudo = pseudo;
   }
   
   public String getPassword(){
       return this.password;
   }
   
   public void setPassword(String pass){
       this.password = pass;
   }
   
   
   public static User getUser(Connection c){
       
       User user = new User();
       
       try{
           PreparedStatement p = c.prepareStatement("SELECT * FROM user");
           
           ResultSet res = p.executeQuery();
           
           while(res.next()){
               user.setId(res.getInt("idUser"));
               user.setPseudo(res.getString("pseudo"));
               user.setPassword(res.getString("password"));
           }
       }catch(SQLException e){
           System.out.println(e);
       }
       
       return user;
       
       
   }
   
   public int connectUser(Connection c) throws SQLException{
       int row = 0;  
         
       try{
          PreparedStatement p = c.prepareStatement("SELECT COUNT(*) AS ligne FROM user WHERE pseudo = ? AND password = ?");
       
       p.setString(1, this.getPseudo());
       p.setString(2, this.getPassword());
       
       
       ResultSet res = p.executeQuery();
       
       while(res.next()){
           row = res.getInt("ligne");
       }
       
       }catch(SQLException e){
           System.out.println(e);
       }
       
     return row;
       
   } 
   
   public void updateUser(Connection c) throws SQLException{
       
         
       try{
          PreparedStatement p = c.prepareStatement("UPDATE user SET pseudo = ?, password = ?");
       
       p.setString(1, this.getPseudo());
       p.setString(2, this.getPassword());
       
       
      p.executeUpdate();
       
     
       
       }catch(SQLException e){
           System.out.println(e);
       }
       
 
       
   }
    
    
    
    
    
    
    
    
    
}
