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
public class Produit {
    private int idProduit;
    private String designation;
    private int prixProduit;
    private int quantiteProduit;
    
    
    public Produit(String d,int p,int q){
        designation = d;
        prixProduit = p;
        quantiteProduit = q;
    } 
    
    public Produit(int id,String d,int p,int q){
        idProduit = id;
        designation = d;
        prixProduit = p;
        quantiteProduit = q;
    }
    
    public Produit(){
        
    }
    
    public String getDesignation(){
        return this.designation;
    }   
    public int getPrixProduit(){
        return this.prixProduit;
    }  
    
    public int getIdProduit(){
        return this.idProduit;
    }  
    
    public int getQuantiteProduit(){
        return this.quantiteProduit;
    }
    
    public void setIdProduit(int id){
        this.idProduit = id;
    }
    
    public void setDesignation(String d){
        this.designation = d;
        
    }
    
    public void setPrixProduit(int p){
        this.prixProduit = p;
            }
    
    public void setQuantiteProduit(int q){
        this.quantiteProduit = q;
    
    }
    
    public void saveProduit(Connection c){
        
        try {
            
            PreparedStatement p = c.prepareStatement("INSERT INTO produit SET designation = ?, prixProduit=?, quantiteProduit=?");
            
            p.setString(1, this.getDesignation());
            p.setInt(2, this.getPrixProduit());
            p.setInt(3, this.getQuantiteProduit());
            
            p.executeUpdate();
        }catch(SQLException e){
            
            
            
        }
    }
   public static ArrayList<Produit> getListProduit(Connection c){
        ArrayList listProduit = new ArrayList();
        try {
            
            PreparedStatement p = c.prepareStatement("SELECT * FROM produit ORDER BY idProduit DESC");
            
            ResultSet produits = p.executeQuery();
            
            while(produits.next()){
                int id = produits.getInt("idProduit");
                String designation = produits.getString("designation");
                int prixProduit = produits.getInt("prixProduit");
                int quantiteProduit = produits.getInt("quantiteProduit");
                
                listProduit.add(new Produit(id,designation,prixProduit,quantiteProduit));
                
                
            }
        }catch(SQLException e){
            
            
            
        }
        
        return listProduit;
    }
   
   public static Produit getProduitById(int id,Connection c){
       Produit prod = new Produit();
       
            try {
            
            PreparedStatement p = c.prepareStatement("SELECT * FROM produit WHERE idProduit = ?");
            p.setInt(1,id);
            ResultSet produit = p.executeQuery();
            
            while(produit.next()){
               prod.setIdProduit(id);
               prod.setDesignation(produit.getString("designation"));
               prod.setPrixProduit(produit.getInt("prixProduit"));
               prod.setQuantiteProduit(produit.getInt("quantiteProduit"));
                
                
            }
        }catch(SQLException e){
            
            
            
        }
            return prod;
       }
   
    public static void deleteProduit(int id,Connection c) throws SQLException{
     
       
            try {
            
            PreparedStatement p = c.prepareStatement("DELETE FROM produit WHERE idProduit = ?");
            p.setInt(1,id);
           p.executeUpdate();
            
          
         
        }catch(SQLException e){
            
            
            System.err.print(e);
        }
          
       }
    public static void UpdateProduit(Produit prod,Connection c) throws SQLException{
     
       
            try {
            
            PreparedStatement p = c.prepareStatement("UPDATE produit SET designation = ?, prixProduit= ?, quantiteProduit=? WHERE idProduit = ?");
            p.setInt(2,prod.getPrixProduit());
            p.setString(1,prod.getDesignation());
            p.setInt(3, prod.getQuantiteProduit());
            p.setInt(4, prod.getIdProduit());
           p.executeUpdate();
            
          
         
        }catch(SQLException e){
            
            
            System.err.print(e);
        }
          
       } 
    
    
    public static void incrementProduitStock(int id,int q,Connection c) throws SQLException{
     
       
            try {
            
            PreparedStatement p = c.prepareStatement("UPDATE produit SET quantiteProduit= quantiteProduit + ? WHERE idProduit = ?");
            p.setInt(2,id);
            p.setInt(1,q);
            p.executeUpdate();
            
          
         
        }catch(SQLException e){
            
            
            System.err.print(e);
        }
          
       }
     public static void decrementProduitStock(int id,int q,Connection c) throws SQLException{
     
       
            try {
            
            PreparedStatement p = c.prepareStatement("UPDATE produit SET quantiteProduit= quantiteProduit - ? WHERE idProduit = ?");
            p.setInt(2,id);
            p.setInt(1,q);
            p.executeUpdate();
            
          
         
        }catch(SQLException e){
            
            
            System.err.print(e);
        }
          
       }
    
    
    public static ArrayList<Produit> searchProduit(String key, Connection c){
          
          ArrayList<Produit> resultSearch = new ArrayList();
           try {
            
            PreparedStatement p = c.prepareStatement("SELECT * FROM produit WHERE designation LIKE '%"+ key +"%' ORDER BY idProduit DESC ");
            
            
            
            ResultSet produits = p.executeQuery();
            
            while(produits.next()){
                int idProduit = produits.getInt("idProduit");
                String designation = produits.getString("designation");
                int prixProduit = produits.getInt("prixProduit");
                int quantiteProduit = produits.getInt("quantiteProduit");
                
                resultSearch.add(new Produit(idProduit,designation,prixProduit,quantiteProduit));
                
                
            }
        }catch(SQLException e){
            
            
            
        }
        
        return resultSearch;     
          
                   
          
      }
      
   
    
}
