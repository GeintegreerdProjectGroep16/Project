/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Eusebius
 */
@WebServlet(name = "Servlet", urlPatterns = {"/Servlet"})
public class Servlet extends HttpServlet {
    
    static final String DATABASE_URL = "jdbc:mysql://localhost/groep16_festivals";
    static final String USERNAME = "root";
    static final String PASSWORD = "";
    
    String uName;
    String uPass;
    String sql;
                       
    ResultSet rs = null;
    Statement stmt = null;
    Connection conn = null;

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /*
             * TODO output your page here. You may use following sample code.
             */
            
               // database link, gebruikersnaam en wachtwoord
           
            try{
                uName = request.getParameter("uName"); 
                uPass = request.getParameter("Password");
                
                sql = "SELECT count(*) FROM geregistreerdegebruikers WHERE gebr_naam LIKE '" + uName + "' AND gebr_pass LIKE '"+ uPass + "'";
                Class.forName("com.mysql.jdbc.Driver"); 
                conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
                
                stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
                       ResultSet.CONCUR_READ_ONLY);
                rs = stmt.executeQuery(sql);
                rs.next();
                if (rs.getInt(1) != 0){
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<link rel="+"stylesheet"+" type="+"text/css"+" href="+"D:/Dropbox/Geïntegreerd Project/52framework_2.0.4/css/css3.css"+" />");
                    out.println("<link rel="+"stylesheet"+" type="+"text/css"+" href="+"D:/Dropbox/Geïntegreerd Project/52framework_2.0.4/css/forms.css"+" />");
                    out.println("<link rel="+"stylesheet"+" type="+"text/css"+" href="+"D:/Dropbox/Geïntegreerd Project/52framework_2.0.4/css/general.css"+" />");
                    out.println("<link rel="+"stylesheet"+" type="+"text/css"+" href="+"D:/Dropbox/Geïntegreerd Project/52framework_2.0.4/css/grid.css"+" />");
                    out.println("<title>Bewerk gegevens</title>");            
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Bewerk gegevens</h1>");
                    out.println("</body>");
                    out.println("</html>");
                }
                else{
                    // foutmelding
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Foute login</title>");            
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>De opgegeven gebruiker bestaat niet of de ingevoerde gegevens zijn fout</h1>");
                    out.println("</body>");
                    out.println("</html>");
                }
                conn.close();
                
                
            }
            catch (Exception ex){
                out.println("De volgende fout is opgetreden:\n\n" + ex.getMessage());
                ex.printStackTrace();
            }
            
            /*out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Servlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Servlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");*/
        } finally {            
            out.close();
        }
    }
    


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
