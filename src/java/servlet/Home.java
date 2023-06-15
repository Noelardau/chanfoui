/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.ModuleLayer.empty;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modele.Bdd;
import modele.User;

/**
 *
 * @author Noelardau
 */
public class Home extends HttpServlet {

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
       // this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);

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
     
        HttpSession s = request.getSession();
            if("ok".equals(request.getParameter("deconnected"))){
                s.removeAttribute("connected");
            } 
            
           
            
       
          this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        processRequest(request, response);
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
        System.out.print(request.getAttribute("password"));
                   // response.getWriter().write(request.getParameter("password"));
 if(!"".equals(request.getParameter("password")) && !"".equals(request.getParameter("pseudo"))){
     
       
            try {
                
                if(new User(request.getParameter("pseudo"),request.getParameter("password")).connectUser(Bdd.getConnection())!= 0){
                    HttpSession s = request.getSession();
                    
                    s.setAttribute("connected", "oui");
                    this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
                }else{
                    request.setAttribute("error", "identifiant incorrect");
                    this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
                }
                //s.removeAttribute("connected");
            } catch (SQLException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }
           
       

        }else{
            
 request.setAttribute("error", "Veuillez remplir tout les champs lors de la connexion");
               this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);


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
