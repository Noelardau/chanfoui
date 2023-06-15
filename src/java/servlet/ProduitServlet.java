/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Bdd;
import modele.Produit;

/**
 *
 * @author Noelardau
 */
public class ProduitServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProduitServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProduitServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        
       
           //delete produit
        if(request.getParameter("idToDelete") != null){
            
       
            try {
                Produit.deleteProduit(Integer.valueOf(request.getParameter("idToDelete")), Bdd.getConnection());
            } catch (SQLException ex) {
                Logger.getLogger(ProduitServlet.class.getName()).log(Level.SEVERE, null, ex);
        
        }}
        
        if(request.getParameter("idProduitToUpdate") == null){
            
        ArrayList<Produit> listProd = Produit.getListProduit(Bdd.getConnection());
        
        request.setAttribute("produits", listProd);
       
        this.getServletContext().getRequestDispatcher("/pages/produitPage.jsp").forward(request, response);
        }else{
            
            Produit produit = Produit.getProduitById(Integer.valueOf(request.getParameter("idProduitToUpdate")), Bdd.getConnection());
            
            request.setAttribute("produit", produit);
            
            this.getServletContext().getRequestDispatcher("/pages/updateProduitPage.jsp").forward(request, response);
            
        }

     //   processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        
              if(request.getParameter("key")!= null){
            
            ArrayList<Produit> results = Produit.searchProduit(request.getParameter("key"),Bdd.getConnection());
            
            request.setAttribute("produits", results);
            request.setAttribute("resultPage", true);
            
  this.getServletContext().getRequestDispatcher("/pages/produitPage.jsp").forward(request, response);
                
            
        }
         
          
        
        // ajout de produit
     if(request.getParameter("idProdToUpdate")== null || "".equals(request.getParameter("idProdToUpdate"))){
         
     
        if(!"".equals(request.getParameter("designation")) && !"".equals(request.getParameter("prixProduit")) && !"".equals(request.getParameter("quantiteProduit"))){
            int prix = Integer.valueOf(request.getParameter("prixProduit"));
            int quantite = Integer.valueOf(request.getParameter("quantiteProduit"));
            new Produit(request.getParameter("designation"),prix,quantite).saveProduit(Bdd.getConnection());
            request.setAttribute("success", "Produit ajouté avec succès");
                       doGet(request,response);

            
        }else{
            request.setAttribute("error", "Veuillez remplir tout les champs!!");
            doGet(request,response);
        }
    }else{
         
         if(!"".equals(request.getParameter("designation")) && !"".equals(request.getParameter("prixProduit")) && !"".equals(request.getParameter("quantiteProduit"))){
             try {
                 int prix = Integer.valueOf(request.getParameter("prixProduit"));
                 int idProd = Integer.valueOf(request.getParameter("idProdToUpdate"));
                 int quantite = Integer.valueOf(request.getParameter("quantiteProduit"));
                 Produit.UpdateProduit(new Produit(idProd,request.getParameter("designation"),prix,quantite), Bdd.getConnection());
                 
                 doGet(request,response);
             } catch (SQLException ex) {
                 Logger.getLogger(ProduitServlet.class.getName()).log(Level.SEVERE, null, ex);
             }

            
        }else{
             request.setAttribute("error", "Veuillez remplir tout les champs!!");
             
              doGet(request,response);
        }
         
     }
        
              

    

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
