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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Achat;
import modele.Bdd;
import modele.Commande;
import modele.Produit;

/**
 *
 * @author Noelardau
 */
@WebServlet(name = "SingleCommandeServlet", urlPatterns = {"/SingleCommandeServlet"})
public class SingleCommandeServlet extends HttpServlet {

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
            out.println("<title>Servlet SingleCommandeServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SingleCommandeServlet at " + request.getContextPath() + "</h1>");
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
        
        if(request.getParameter("idCommande") != null){
            
            if(request.getParameter("error")!= null){
                request.setAttribute("error", "Veuillez mettre la quantité du produit à ajouter");
            }
            
            if(request.getParameter("idAchatToDelete")!= null){
                Achat.deleteAchat(Integer.valueOf(request.getParameter("idAchatToDelete")), Bdd.getConnection());
                
                int idProduit = Integer.valueOf(request.getParameter("idProduit"));
                int qtAchat = Integer.valueOf(request.getParameter("quantiteAchete"));
                
                try {
                    Produit.incrementProduitStock(idProduit, qtAchat, Bdd.getConnection());
                } catch (SQLException ex) {
                    Logger.getLogger(SingleCommandeServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            
            int idCommande = Integer.valueOf(request.getParameter("idCommande"));
          
            if(request.getParameter("paye")!= null){
                
                Commande.changeToPaye(idCommande, Bdd.getConnection());
                
            }
            
            
            if(Commande.getCommandeById(idCommande, Bdd.getConnection()).getPaye() == 0){
                       request.setAttribute("produits", Produit.getListProduit(Bdd.getConnection()));
                   
          
            
            }
           
            request.setAttribute("commande", Commande.getCommandeById(idCommande, Bdd.getConnection()));
            request.setAttribute("achats", Achat.getListAchat(idCommande, Bdd.getConnection()));
            request.setAttribute("netAPayer", Achat.getNetAPayer(idCommande,Bdd.getConnection()));
          this.getServletContext().getRequestDispatcher("/pages/singleCommande.jsp").forward(request, response);
     
        }
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
        if(request.getParameter("idProduit")!= null ){
           

            if(request.getParameter("quantiteAchat") != ""){
             int idCommande = Integer.valueOf(request.getParameter("idCommande"));
            int idProduit = Integer.valueOf(request.getParameter("idProduit"));
            int quantiteAchat = Integer.valueOf(request.getParameter("quantiteAchat"));
                    
            new Achat(idCommande, idProduit, quantiteAchat).addAchat(Bdd.getConnection());
            try {
                Produit.decrementProduitStock(idProduit, quantiteAchat, Bdd.getConnection());
            } catch (SQLException ex) {
                Logger.getLogger(SingleCommandeServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            request.setAttribute("commande", Commande.getCommandeById(idCommande, Bdd.getConnection()));
            request.setAttribute("produits", Produit.getListProduit(Bdd.getConnection()));
             request.setAttribute("achats", Achat.getListAchat(idCommande, Bdd.getConnection()));
             request.setAttribute("netAPayer", Achat.getNetAPayer(idCommande,Bdd.getConnection()));
          this.getServletContext().getRequestDispatcher("/pages/singleCommande.jsp").forward(request, response);
     
                
            }else{
                 int idCommande = Integer.valueOf(request.getParameter("idCommande"));
            request.setAttribute("commande", Commande.getCommandeById(idCommande, Bdd.getConnection()));
            request.setAttribute("achats", Achat.getListAchat(idCommande, Bdd.getConnection()));
            request.setAttribute("netAPayer", Achat.getNetAPayer(idCommande,Bdd.getConnection()));
            
            response.sendRedirect("/Test/SingleCommandeServlet?idCommande="+idCommande+"&error=err");
         
     
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
