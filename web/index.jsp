<%-- 
    Document   : index
    Created on : 28-mrt-2013, 14:31:18
    Author     : Eusebius
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="D:\Dropbox\Geïntegreerd Project\52framework_2.0.4\css\forms.css" />
        <link rel="stylesheet" type="text/css" href="D:\Dropbox\Geïntegreerd Project\52framework_2.0.4\css\general.css" />
        <link rel="stylesheet" type="text/css" href="D:\Dropbox\Geïntegreerd Project\52framework_2.0.4\css\grid.css" />
        <link rel="stylesheet" type="text/css" href="D:\Dropbox\Geïntegreerd Project\52framework_2.0.4\css\reset.css" />
        <title>Home</title>
    </head>
    <body>
        <h1>Muziekfestivals</h1>
        <div id="Data">
                <%
                try{
                    
                    String DATABASE_URL = "jdbc:mysql://localhost/groep16_festivals";
                    String USERNAME = "root";
                    String PASSWORD = "";

                    String sql = "SELECT * FROM festivals";
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);

                    Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
                        ResultSet.CONCUR_READ_ONLY);
                    ResultSet rs = stmt.executeQuery(sql);
                    out.println("<table>");
                    out.println("<tr>");
                    out.println("<th>Naam</th>");
                    out.println("<th>Locatie</th>");
                    out.println("<th>Datum</th>");
                    out.println("</tr>");
                    while(rs.next()){
                        
                        out.println("<td>" + rs.getString("fest_naam") + "</td>");
                        out.println("<td>" + rs.getString("fest_locatie") + "</td>");
                        out.println("<td>" + rs.getString("fest_datum") + "</td>");
                        out.println("<td><a href=\"info.jsp\"><input type=\"button\" name=\"" + rs.getString("fest_id") +"\" value=\"Meer info\"/></a></td>");
                        out.println("</tr>");
                    }
                    out.println("</table>");
                    
                    sql = "SELECT * FROM bands";
                    rs = stmt.executeQuery(sql);
                    out.println("<form action=\"servletBands\">");
                    out.println("<select name=\"bands\" required=\"required\">");
                    while (rs.next()){
                      out.println("<option>" + rs.getString("band_naam") + "</option>"); 
                    }
                    out.println("</select>");                  
                    out.println("<input type=\"submit\" value=\"Meer info van deze band\" />");
                    out.println("</form>");
                    conn.close();
                }
                catch (Exception ex){
                   out.println("<label>Er is een fout opgetreden</label>");

                }

                %>
        </div>
        <form action="login.jsp">
            <input type="submit" value="Login">
        </form>
    </body>
</html>
