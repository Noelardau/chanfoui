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
public class Achat {
    private int idAchat;
    private int idCommande;
    private int idProduit;
    private int quantiteAchat;
    private int prixUnitaire;
    private int prixTotal;
    private String designation;
 
    
    
    public int getIdAchat(){
        return this.idAchat;
    }
    
    public void setIdAchat(int id){
        this.idAchat = id;
    }

    public int getIdCommande(){
        return this.idCommande;
    }
    
    public void setIdCommande(int id){
        
        this.idCommande = id;
        
    }
    
    public int getIdProduit(){
        return this.idProduit;
    }
    
    public void setIdProduit(int id){
        this.idProduit = id;
    }
    
    public int getQuantiteAchat(){
        return this.quantiteAchat;
    }
    
    
    public void setQuantiteAchat(int qt){
        this.quantiteAchat = qt;
    }
    
    public String getDesignation(){
        return this.designation;
    }

    public void setDesignation(String designation){
        this.designation = designation;
    }
    
    public int getPrixUnitaire(){
        return this.prixUnitaire;
    }
    
    public void setPrixUnitaire(int prix){
        this.prixUnitaire = prix;
    }
    
    public int getPrixTotal(){
        return this.prixTotal;
    }
    
    public void setPrixTotal(int prix){
        this.prixTotal = prix;
    }
    
    
    public Achat(){
        
    }    
    public Achat(int idAchat, int idProduit,String designation, int quantite, int prixUnitaire, int prixTotal){
         this.idAchat = idAchat;
        this.idProduit = idProduit;
        this.designation = designation;
        this.quantiteAchat = quantite;
        this.prixUnitaire = prixUnitaire;
        this.prixTotal = prixTotal;
        
    }
    
    public Achat(int idCommande, int idProduit, int quantite){
        
        this.idCommande = idCommande;
        this.idProduit = idProduit;
        this.quantiteAchat = quantite;
        
    }
    
    public static int getNetAPayer(int idCommande,Connection c){
        int netAPayer = 0;
        try{
            PreparedStatement p = c.prepareStatement("SELECT SUM(a.quantiteAchete * p.prixProduit) AS netAPayer FROM achat a NATURAL JOIN commande co NATURAL JOIN produit p WHERE co.idCommande = ?");
            
            p.setInt(1, idCommande);
            
            ResultSet res = p.executeQuery();
            
            while(res.next()){
                netAPayer = res.getInt("netAPayer");
            }
            
        }catch(SQLException e){
            System.out.println(e);
        }
        
        return netAPayer;
    }
    
    public void addAchat(Connection c){
        try{
           PreparedStatement p = c.prepareStatement("INSERT INTO achat SET idCommande = ?, idProduit= ?, quantiteAchete = ?");
           
           p.setInt(1, this.getIdCommande());
           p.setInt(2, this.getIdProduit());
           p.setInt(3, this.getQuantiteAchat());
           
           p.executeUpdate();
        }catch(SQLException e){
            System.out.print(e);
        }
    }
    
    public static ArrayList<Achat> getListAchat(int id,Connection c){
        ArrayList<Achat> listAchat = new ArrayList();
        
        try{
            PreparedStatement p = c.prepareStatement("SELECT a.idAchat, p.idProduit, p.designation, a.quantiteAchete, p.prixProduit, (a.quantiteAchete * p.prixProduit) AS prixTotal FROM achat a NATURAL JOIN commande co NATURAL JOIN produit p WHERE co.idCommande = ? ");
            p.setInt(1, id);
            
            ResultSet res = p.executeQuery();
            
            while(res.next()){
                int idAchat = res.getInt("idAchat");
                int idProduit = res.getInt("idProduit");
                String designation = res.getString("designation");
                int quantiteAchete = res.getInt("quantiteAchete");
                int prixUnitaire = res.getInt("prixProduit");
                int prixTotal = res.getInt("prixTotal");
                
                
                listAchat.add(new Achat(idAchat,idProduit,designation,quantiteAchete,prixUnitaire,prixTotal));             
                
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        
        return listAchat;
        
    }
    
    public static void deleteAchat(int idAchat,Connection c){
        
        try{
            
            PreparedStatement p = c.prepareStatement("DELETE FROM achat WHERE idAchat = ?");
            
            p.setInt(1, idAchat);
            
            p.executeUpdate();
            
        }catch(SQLException e){
            System.out.println(e);
        }
        
        
    }
    
}
