/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Bdd;
import modele.Commande;

/**
 *
 * @author Noelardau
 */
@WebServlet(name = "StatServlet", urlPatterns = {"/Stat"})
public class StatServlet extends HttpServlet {

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
            out.println("<title>Servlet StatServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StatServlet at " + request.getContextPath() + "</h1>");
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
        
        String[] mois = {"Janvier","Fevrier","Mars","Avril","Mai","Juin","Juillet","Août","Septembre","Octobre","Novembre","Décembre"}; 
        int moisActuel = new Date().getMonth();
        int anneeActuel =  Calendar.getInstance().get(Calendar.YEAR);
        
      
        request.setAttribute("mois", mois[moisActuel]);
        request.setAttribute("annee", anneeActuel);
        request.setAttribute("totalCommande", Commande.getNombreCommande(0, 0, Bdd.getConnection()));       
        request.setAttribute("totalPrixVente", Commande.getPrixTotal(0, 0, Bdd.getConnection()));

        request.getRequestDispatcher("./pages/statPage.jsp").forward(request, response);
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
        if(!"".equals(request.getParameter("moisAnnee"))){
        String[] moisL = {"Janvier","Fevrier","Mars","Avril","Mai","Juin","Juillet","Août","Septembre","Octobre","Novembre","Décembre"}; 

        int Annee = Integer.valueOf(request.getParameter("moisAnnee").split("-")[0]);
        int mois = Integer.valueOf(request.getParameter("moisAnnee").split("-")[1]);
        
         request.setAttribute("mois", moisL[mois-1]);
        request.setAttribute("annee", Annee);
        
        request.setAttribute("totalCommande", Commande.getNombreCommande(mois, Annee, Bdd.getConnection()));       
        request.setAttribute("totalPrixVente", Commande.getPrixTotal(mois, Annee, Bdd.getConnection()));

        request.getRequestDispatcher("./pages/statPage.jsp").forward(request, response);
            
        }else{
              String[] mois = {"Janvier","Fevrier","Mars","Avril","Mai","Juin","Juillet","Août","Septembre","Octobre","Novembre","Décembre"}; 
              int moisActuel = new Date().getMonth();
              int anneeActuel =  Calendar.getInstance().get(Calendar.YEAR);
        
        
        request.setAttribute("mois", mois[moisActuel]);
        request.setAttribute("annee", anneeActuel);
        request.setAttribute("error", "Veuillez préciser le mois et l'année");
            
            request.setAttribute("error", "Veuillez selectionner le mois et l'année");
            request.setAttribute("totalCommande", Commande.getNombreCommande(0, 0, Bdd.getConnection()));       
        request.setAttribute("totalPrixVente", Commande.getPrixTotal(0, 0, Bdd.getConnection()));

        request.getRequestDispatcher("./pages/statPage.jsp").forward(request, response);
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
