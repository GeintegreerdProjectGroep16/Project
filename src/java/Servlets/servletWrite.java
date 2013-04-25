/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Eusebius
 */
@WebServlet(name = "servletWrite", urlPatterns = {"/servletWrite"})
public class servletWrite extends HttpServlet {
    // database link, gebruikersnaam en wachwoord
    static final String DATABASE_URL = "jdbc:mysql://localhost/groep16_festivals";
    static final String USERNAME = "root";
    static final String PASSWORD = "";
    String sql;
        
    String table;

    ResultSet rs = null;
    ResultSetMetaData rsmd = null;
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
            
            out.println("<html>");
            out.println("<head>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"../css/css3.css\" media=\"screen\">");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"../css/forms.css\" media=\"screen\">");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"../css/general.css\" media=\"screen\">");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"../css/grid.css\" media=\"screen\">");
            out.println("<title>Schrijven naar databank</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Databank bijwerken</h1>"); 
            
            
            try{
                // opvragen van de tabelnaam
                 table = request.getParameter("table");
                 sql = "SELECT * FROM " + table;
                 Class.forName("com.mysql.jdbc.Driver"); 
                 conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
                
                 stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
                 ResultSet.CONCUR_READ_ONLY);
                 rs = stmt.executeQuery(sql);
                 rsmd = rs.getMetaData();
                 int number = Integer.parseInt(request.getParameter("row"));
                 // als de radiobutton update is aangeduid wordt dit uitgevoerd
                 if (request.getParameter("function").equals("update")){
                     sql = "UPDATE " + table + " SET ";
                     for (int i=2; i<rsmd.getColumnCount()+1; i++){
                       sql += rsmd.getColumnName(i) + "=\""+ request.getParameter(rsmd.getColumnName(i) + "_" + number) +"\", "; 
                     }
                     sql = sql.substring(0, sql.length()-2);
                     sql += " WHERE " + rsmd.getColumnName(1) + "=" + Integer.parseInt(request.getParameter("row"));
                     try{
                        stmt.executeUpdate(sql);
                        out.println("De database is aangepast");
                     }
                     catch (Exception ex){
                        out.println("De volgende fout is opgetreden: " + ex.getMessage());
                        ex.printStackTrace();
                     }
                     
                 }
                 // anders wordt dit uitgevoerd
                 else{
                     sql = "DELETE FROM " + table + " WHERE " + rsmd.getColumnName(1) + "=" + Integer.parseInt(request.getParameter("row"));
                     try{
                        stmt.executeUpdate(sql);
                        out.println("Het record is verwijderd");
                     }
                     catch (Exception ex){
                        out.println("De volgende fout is opgetreden: " + ex.getMessage());
                        ex.printStackTrace();
                     }
                     
                 }
                 conn.close();
            }
            catch (Exception ex){
                out.println("De volgende fout is opgetreden: " + ex.getMessage());
                ex.printStackTrace();
            }
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
