/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Achat;
import modele.Bdd;
import modele.Client;
import modele.Commande;
import modele.Produit;

/**
 *
 * @author Noelardau
 */
public class CommandeServlet extends HttpServlet {

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
            out.println("<title>Servlet CommandeServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CommandeServlet at " + request.getContextPath() + "</h1>");
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
        
        if(request.getParameter("idToDelete") != null){
            
            
            //Achat.getListAchat(Integer.valueOf(request.getParameter("idToDelete")),Bdd.getConnection());
            
            for(Achat a: Achat.getListAchat(Integer.valueOf(request.getParameter("idToDelete")),Bdd.getConnection())){
                int quantiteAchat = a.getQuantiteAchat();
                int idProduit = a.getIdProduit();
                
                try {
                    Produit.incrementProduitStock(idProduit, quantiteAchat, Bdd.getConnection());
                } catch (SQLException ex) {
                    Logger.getLogger(CommandeServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            Commande.deleteCommande(Integer.valueOf(request.getParameter("idToDelete")), Bdd.getConnection());
            
        }
        
        
        request.setAttribute("commandes", Commande.getListCommande(Bdd.getConnection()));
        
        this.getServletContext().getRequestDispatcher("/pages/commandePage.jsp").forward(request, response);
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
       
        
        
        if(!"".equals(request.getParameter("idClient")) && !"".equals(request.getParameter("dateCommande"))){
             int idClient = Integer.valueOf(request.getParameter("idClient"));
             String dateCommande = request.getParameter("dateCommande");
             
            try {
                if(Client.clientExist(idClient, Bdd.getConnection())!= 0){
                    new Commande(idClient,dateCommande).addCommande(Bdd.getConnection());
                    request.setAttribute("success", "Commande ajoutée");
                }else{
                    request.setAttribute("error", "L'id que vous avez entrez ne correspond à aucun client");
                }
            } catch (SQLException ex) {
                Logger.getLogger(CommandeServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }else{
            request.setAttribute("error", "Remplissez tout les champs!!");
            
        }
       
        
        request.setAttribute("commandes", Commande.getListCommande(Bdd.getConnection()));
         this.getServletContext().getRequestDispatcher("/pages/commandePage.jsp").forward(request, response);
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
