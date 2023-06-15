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
public class Commande {
    private int idCommande;
    private int idClient;
    private String nomClient;
    private String prenomClient;
    private String dateCommande;
    private int paye;
    
    public Commande(){
        
    }
    
    public Commande(int id, String date){
        this.idClient = id;
        this.dateCommande = date;
    }
    public Commande(int id, String nom, String prenom, String date,int paye){
        
        
        
        this.idCommande = id;
        this.nomClient = nom;
        this.prenomClient = prenom;
        this.dateCommande = date;
        this.paye = paye;
    }
    
    
    public int getIdCommande(){
        return this.idCommande;
    } 
    
    public void setIdCommande(int id){
        this.idCommande = id;
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
    
    public String getDateCommande(){
        
        return this.dateCommande;
    }
    
    public void setDateCommande(String date){
        this.dateCommande = date;
    }
    
    public int getPaye(){
        return this.paye;
    }
    
    public void setPaye(int paye){
        this.paye = paye;
    }
    
    
    public void addCommande(Connection c){
        try{
            
            PreparedStatement p = c.prepareStatement("INSERT INTO commande SET idClient = ?, dateCommande = ?");
            p.setInt(1, this.getIdClient());
            p.setString(2, this.getDateCommande());
            
            p.executeUpdate();
            
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    
    
    public static ArrayList<Commande> getListCommande(Connection c){
        
        ArrayList<Commande> commandeList = new ArrayList();
        
        
        try{
            PreparedStatement p =  c.prepareStatement("SELECT * FROM commande NATURAL JOIN client ORDER BY dateCommande DESC");
            
            ResultSet r = p.executeQuery();
            
            while(r.next()){
                
                int idCommande = r.getInt("idCommande");
                String nomClient = r.getString("nomClient");
                String prenomClient = r.getString("prenomClient");
                String dateCommande = r.getString("dateCommande");
                int paye = r.getInt("paye");
                
                commandeList.add(new Commande(idCommande,nomClient,prenomClient,dateCommande,paye));              
                
                
                
            }
        
        }catch(SQLException e){
            System.out.println(e);
        }
        
        return commandeList;
    }
    
    public static void deleteCommande(int idCommande,Connection c){
         try{
            PreparedStatement p =  c.prepareStatement("DELETE FROM commande WHERE idCommande = ?");
            p.setInt(1, idCommande);
             p.executeUpdate();
            
            
        }catch(SQLException e){
            System.out.println(e);
        }
    }

    public static Commande getCommandeById(int idCommande,Connection c){
         Commande commande = new Commande();
        try{
            PreparedStatement p =  c.prepareStatement("SELECT * FROM commande NATURAL JOIN client WHERE idCommande = ? ORDER BY dateCommande ");
            p.setInt(1, idCommande);
          ResultSet res =  p.executeQuery();
            while(res.next()){
                
                commande.setIdCommande(res.getInt("idCommande"));
                commande.setNomClient(res.getString("nomClient"));
                commande.setNomClient(res.getString("prenomClient"));
                commande.setDateCommande(res.getString("dateCommande"));
                commande.setPaye(res.getInt("paye"));
                
                
              }
            
        }catch(SQLException e){
            System.out.println(e);
        }
        
        return commande;
    }
    
    public static void changeToPaye(int idCommande,Connection c){
        try{
            
            PreparedStatement p = c.prepareStatement("UPDATE commande SET paye = 1 WHERE idCommande = ?");
            p.setInt(1,idCommande);
            p.executeUpdate();
            
            
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    
    public static int getNombreCommande(int mois,int annee,Connection c){
        int nb = 0;
        try{
            if(mois == 0 && annee == 0 ){
            PreparedStatement p = c.prepareStatement("SELECT COUNT(*) as total FROM commande WHERE MONTH(dateCommande) = MONTH(NOW()) AND YEAR(dateCommande) = YEAR(NOW())");
             ResultSet rs = p.executeQuery();
             while(rs.next()){
                 nb = rs.getInt("total");
             }
            }else{
                      PreparedStatement p = c.prepareStatement("SELECT COUNT(*) as total FROM commande WHERE MONTH(dateCommande) = ? AND YEAR(dateCommande) = ?");
                      p.setInt(1, mois);
                      p.setInt(2, annee);
                      ResultSet rs = p.executeQuery();
                      while(rs.next()){
                         nb = rs.getInt("total");
                        }
            }
            
           
            
            
        }catch(SQLException e){
            System.out.println(e);
        }
        return nb;
    }
 public static int getPrixTotal(int mois,int annee,Connection c){
        int nb = 0;
        try{
            if(mois == 0 && annee == 0 ){
            PreparedStatement p = c.prepareStatement("SELECT SUM(a.quantiteAchete * p.prixProduit) as prixTotal FROM commande co NATURAL JOIN achat a NATURAL JOIN produit p WHERE MONTH(co.dateCommande) =MONTH(NOW()) AND YEAR(co.dateCommande) = YEAR(NOW())   AND co.paye = 1");
             ResultSet rs = p.executeQuery();
             while(rs.next()){
                 nb = rs.getInt("prixTotal");
             }
            }else{
                      PreparedStatement p = c.prepareStatement("SELECT SUM(a.quantiteAchete * p.prixProduit) as prixTotal FROM commande co NATURAL JOIN achat a NATURAL JOIN produit p WHERE MONTH(co.dateCommande) =? AND YEAR(co.dateCommande) = ?   AND co.paye = 1");
                      p.setInt(1, mois);
                      p.setInt(2, annee);
                      ResultSet rs = p.executeQuery();
                      while(rs.next()){
                         nb = rs.getInt("prixTotal");
                        }
            }
            
           
            
            
        }catch(SQLException e){
            System.out.println(e);
        }
        return nb;
    }
 
  public static ArrayList<Commande> searchCommande(String key, Connection c){
          
          ArrayList<Commande> resultSearch = new ArrayList();
           try {
            
            PreparedStatement p = c.prepareStatement("SELECT * FROM commande NATURAL JOIN client WHERE nomClient LIKE '%"+key+"%' OR prenomClient LIKE '%"+key+"%' ORDER BY dateCommande");
            
            
            
            ResultSet res = p.executeQuery();
            
            while(res.next()){
               
                resultSearch.add(new Commande(res.getInt("idCommande"),res.getString("nomClient"), res.getString("prenomClient"), res.getString("dateCommande"),res.getInt("paye")));
                
                
            }
        }catch(SQLException e){
            
            
            
        }
        
        return resultSearch;     
          
                   
          
      }
      
   




}
