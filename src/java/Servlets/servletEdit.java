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
@WebServlet(name = "servletEdit", urlPatterns = {"/servletEdit"})
public class servletEdit extends HttpServlet {
        // database link, gebruikersnaam en wachtwoord
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
            out.println("<title>Bewerk gegevens</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Bewerk gegevens</h1>");        
                try{
                    // de naam van de tabel die je wil bewerken ingeven en hiervan de gegevens ophalen
                    table = request.getParameter("table");
                    sql = "SELECT * FROM " + table;
                    Class.forName("com.mysql.jdbc.Driver"); 
                    conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
                
                    stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_READ_ONLY);
                    rs = stmt.executeQuery(sql);
                    rsmd = rs.getMetaData();
                    out.println("<form action=\"servletWrite\">");
                    out.println("<table>");                    
                    out.println("<tr>");
                    out.println("<th>bewerk</th>");
                    for (int i=1; i<rsmd.getColumnCount()+1; i++ ){
                        out.println("<th>" + rsmd.getColumnName(i) + "</th>");
                    }
                    out.println("</tr>");
                    while(rs.next()){
                        out.println("<tr>");
                        out.println("<td>");
                        out.println("<input type=\"radio\" name=\"row\" value=\"" + rs.getInt(1) + "\">");
                        out.println("</td>");
                        for (int i=1; i<rsmd.getColumnCount()+1; i++){
                            out.println("<td>");
                            if (rsmd.getColumnTypeName(i).equals("VARCHAR")){
                                out.println("<input type=\"text\" name=\"" + rsmd.getColumnName(i) + "\" value=\"" + rs.getString(rsmd.getColumnName(i)) + "\">");
                            }
                            else if (rsmd.getColumnTypeName(i).equals("INT")){
                                if (rsmd.getColumnName(i).substring(rsmd.getColumnName(i).length()-2).equals("id")){
                                    out.println("<input type=\"text\" name=\"" + rsmd.getColumnName(i) + "\" value=\"" + rs.getInt(rsmd.getColumnName(i)) + "\" disabled>");
                                }
                                else{
                                    out.println("<input type=\"text\" name=\"" + rsmd.getColumnName(i) + "\" value=\"" + rs.getInt(rsmd.getColumnName(i)) + "\">");
                                }
                            }
                            else if (rsmd.getColumnTypeName(i).equals("DATE")){
                                out.println("<input type=\"text\" name=\"" + rsmd.getColumnName(i) + "\" value=\"" + rs.getDate(rsmd.getColumnName(i)) + "\">");
                            }
                            else if (rsmd.getColumnTypeName(i).equals("DECIMAL")){
                                out.println("<input type=\"text\" name=\"" + rsmd.getColumnName(i) + "\" value=\"" + rs.getDouble(rsmd.getColumnName(i)) + "\">");
                            }
                            else{
                                out.println(rsmd.getColumnTypeName(i));
                            }
                            out.println("</td>");
                        }
                        
                    }
                    out.println("</table>");
                    out.println("<input type=\"radio\" name=\"function\" value=\"update\">Update database</br>");
                    out.println("<input type=\"radio\" name=\"function\" value=\"delete\">Verwijder record</br>");
                    out.println("<input type=\"submit\" value=\"Voer uit\">");
                    out.println("<input type=\"text\" name=\"table\" value=\"" + table + "\" hidden>");
                    out.println("</form>");
                    conn.close();
                }
                catch (Exception ex){
                    out.println("De volgende fout is opgetreden: " + ex.getMessage());
                    ex.printStackTrace();
                }
                out.println("</body>");
                out.println("</html>");   
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
