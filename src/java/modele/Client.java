/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Noelardau
 */
public class Client {

   
    private int idClient;
    private String nomClient;
    private String prenomClient;
    private String telClient;
    
    public Client(int id, String nom, String prenom, String tel){
        this.idClient = id;
        this.nomClient = nom;
        this.prenomClient = prenom;
        this.telClient = tel;
    }
     public Client(String nom, String prenom, String tel){
       
        this.nomClient = nom;
        this.prenomClient = prenom;
        this.telClient = tel;
    }
     
     public Client(){
         
     }
    
    public int getIdClient(){
        return this.idClient;
    }
    
    public void setIdClient(int id){
        this.idClient = id;
    }
    
    
    public String getNomClient(){
        return this.nomClient;
    }
    
    public void setNomClient(String nom){
        this.nomClient = nom;
    }
    
    public String getPrenomClient(){
        return this.prenomClient;
    }
    
    public void setPrenomClient(String prenom){
        this.prenomClient = prenom;
    }
    
    
    
    public String getTelClient(){
        return this.telClient;
    }
    
    public void setTelClient(String tel){
        this.telClient = tel;
    }
    
    
    
     public void saveClient(Connection c){
        
        try {
            
            PreparedStatement p = c.prepareStatement("INSERT INTO client SET nomClient= ?, prenomClient=?, telClient=?");
            
            p.setString(1, this.getNomClient());
            p.setString(2, this.getPrenomClient());
            p.setString(3, this.getTelClient());
            
            p.executeUpdate();
        }catch(SQLException e){
            
            
            
        }
    }
     
      public static ArrayList<Client> getListClient(Connection c){
        ArrayList<Client> listClient = new ArrayList();
        try {
            
            PreparedStatement p = c.prepareStatement("SELECT * FROM client ORDER BY idClient DESC");
            
            ResultSet clients = p.executeQuery();
            
            while(clients.next()){
                int idClient = clients.getInt("idClient");
                String nomClient = clients.getString("nomClient");
                String prenomClient = clients.getString("prenomClient");
                String telClient = clients.getString("telClient");
                
                listClient.add(new Client(idClient,nomClient,prenomClient,telClient));
                
                
            }
        }catch(SQLException e){
            
            
            
        }
        
        return listClient;
    }
    
       public static void deleteClient(Integer id, Connection connection) throws SQLException {
       PreparedStatement p = connection.prepareStatement("DELETE FROM client WHERE idClient = ?");
       p.setInt(1,id);
       p.executeUpdate();
    } 
       
       public static int clientExist(Integer id, Connection connection) throws SQLException {
         int row = 0;
       PreparedStatement p = connection.prepareStatement("SELECT COUNT(*) AS effectif FROM client WHERE idClient = ?");
       p.setInt(1,id);
       ResultSet res =  p.executeQuery();
       
       while(res.next()){
           row = res.getInt("effectif");
       }
       return row;
    }
       
      public static ArrayList<Client> searchClient(String key, Connection c){
          
          ArrayList<Client> resultSearch = new ArrayList();
           try {
            
            PreparedStatement p = c.prepareStatement("SELECT * FROM client WHERE nomClient LIKE '%"+ key +"%' OR prenomClient LIKE '%"+ key +"%' ORDER BY idClient DESC ");
            
            
            
            ResultSet clients = p.executeQuery();
            
            while(clients.next()){
                int idClient = clients.getInt("idClient");
                String nomClient = clients.getString("nomClient");
                String prenomClient = clients.getString("prenomClient");
                String telClient = clients.getString("telClient");
                
                resultSearch.add(new Client(idClient,nomClient,prenomClient,telClient));
                
                
            }
        }catch(SQLException e){
            
            
            
        }
        
        return resultSearch;     
          
                   
          
      }
      
      
      public static Client getClientById(int idClient, Connection c){
          
          Client client = new Client();
          
          try{
              PreparedStatement p = c.prepareStatement("SELECT * FROM client WHERE idClient = ?");
              
              p.setInt(1, idClient);
              
              ResultSet res = p.executeQuery();
              
              while(res.next()){
                  client.setIdClient(res.getInt("idClient"));
                  client.setNomClient(res.getString("nomclient"));
                  client.setPrenomClient(res.getString("prenomclient"));
                  client.setTelClient(res.getString("telClient"));
              }
              
              
          }catch(SQLException e){
              System.out.print(e);
          }
          return client;
      }
      
       public static void UpdateClient(Client cli,Connection c) throws SQLException{
     
       
            try {
            
            PreparedStatement p = c.prepareStatement("UPDATE client SET nomClient = ?, prenomClient= ?, telClient=? WHERE idClient = ?");
            
            p.setInt(4,cli.getIdClient());
            p.setString(1,cli.getNomClient());            
            p.setString(2, cli.getPrenomClient());
            p.setString(3, cli.getTelClient());
           p.executeUpdate();
            
          
         
        }catch(SQLException e){
            
            
            System.err.print(e);
        }
          
       } 
    
    
}
