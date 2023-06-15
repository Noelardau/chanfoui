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
import modele.Client;
import modele.Produit;

/**
 *
 * @author Noelardau
 */
public class ClientServlet extends HttpServlet {

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
            out.println("<title>Servlet ClientServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ClientServlet at " + request.getContextPath() + "</h1>");
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
        
                   

        if(request.getParameter("idToDelete")!= null){
            try {
                Client.deleteClient(Integer.valueOf(request.getParameter("idToDelete")), Bdd.getConnection());
            } catch (SQLException ex) {
                Logger.getLogger(ProduitServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(request.getParameter("idClientToUpdate")== null ){
        ArrayList<Client> listClient = Client.getListClient(Bdd.getConnection());
        
        request.setAttribute("clients",listClient);
        
         this.getServletContext().getRequestDispatcher("/pages/clientPage.jsp").forward(request, response);
         
        }else{
            
            int idClient = Integer.valueOf(request.getParameter("idClientToUpdate"));
            
            request.setAttribute("client",Client.getClientById(idClient, Bdd.getConnection()));
        
         this.getServletContext().getRequestDispatcher("/pages/updateClientPage.jsp").forward(request, response);
            
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
        
        
        if(request.getParameter("key")!= null){
            
            ArrayList<Client> results = Client.searchClient(request.getParameter("key"),Bdd.getConnection());
            
            request.setAttribute("clients", results);
            request.setAttribute("resultPage", true);
            
  this.getServletContext().getRequestDispatcher("/pages/clientPage.jsp").forward(request, response);
                
            
        }
        
        
        if(!"".equals(request.getParameter("nomClient")) && !"".equals(request.getParameter("prenomClient")) && !"".equals(request.getParameter("telClient"))){
            
             String nom = request.getParameter("nomClient");
            String prenom = request.getParameter("prenomClient");
            String tel = request.getParameter("telClient");
            
        if(request.getParameter("idClientToUpdate") == null){          
            
            
                
            if(request.getParameter("telClient").length() == 10){
              
                try{
                    
                    int telTest = Integer.valueOf(request.getParameter("telClient"));
                     new Client(nom,prenom,tel).saveClient(Bdd.getConnection());  
                    request.setAttribute("success", "Client ajouté avec succès!!");
            
                }catch(NumberFormatException e){
                    request.setAttribute("error", "Le numero teléphone doit contenir que des chiffres");
                    System.err.print(e);
                    
                }
                       
           
             
            }else{
              request.setAttribute("error", "Le numero telephone doit être 10 chiffre");
   
                
            }    
           doGet(request,response);
           
           
        }else{
            
                 try {
                     Client.UpdateClient(new Client(Integer.valueOf(request.getParameter("idClientToUpdate")),nom,prenom,tel), Bdd.getConnection());
                     
                 } catch (SQLException ex) {
                     Logger.getLogger(ClientServlet.class.getName()).log(Level.SEVERE, null, ex);
                 }       
             
                 request.setAttribute("clients", Client.getListClient(Bdd.getConnection()));
                 
            this.getServletContext().getRequestDispatcher("/pages/clientPage.jsp").forward(request, response);
            
        }
        
        
        }else{
            request.setAttribute("error", "Veuillez remplir tout les champs");
            doGet(request,response);
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
