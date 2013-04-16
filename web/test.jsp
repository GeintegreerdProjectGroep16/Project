<%-- 
    Document   : test
    Created on : 16-apr-2013, 17:58:35
    Author     : Eusebius
--%>

<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="javax.swing.JOptionPane"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
         <%
            int count = 0;
            Connection conn = null;
            String DATABASE_URL = "jdbc:mysql://localhost/groep16_festivals";
            String USERNAME = "root";
            String PASSWORD = "";            
            conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        try{
            
            
            out.println("it's something");
           /*        
            String uName = request.getParameter("uName");
            out.println(uName);
            String uPass = request.getParameter("Password");
            out.println(uPass);
            
            String sql = "SELECT count(*) FROM geregistreerdegebruikers WHERE gebr_naam LIKE" + uName
                         + "AND gebr_pass = " + uPass;
            Statement stmt = null;
            ResultSet rs = null;
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
                   ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(sql);
            out.println(rs.getInt(0));
            try{
               count = rs.getInt(1);
            }
            catch (Exception ex){
                out.println("error1");
                out.println(count);
            }
            if (count == 0){
            out.println("mislukt");
            }
            else{
            out.println("gelukt");
            }*/
            conn.close();
        }

        catch (Exception ex){
            out.println("error2");
            out.println(count);
 
        }
        %>
    </body>
</html>
