<%-- 
    Document   : info
    Created on : 18-apr-2013, 15:42:43
    Author     : Eusebius
--%>

<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="./css/css3.css" media="screen">
        <link rel="stylesheet" type="text/css" href="./css/forms.css" media="screen">
        <link rel="stylesheet" type="text/css" href="./css/general.css" media="screen">
        <link rel="stylesheet" type="text/css" href="./css/grid.css" media="screen">
        <title>Info</title>
    </head>
    <body>
        <h1>Meer informatie</h1>
        <div id="Info">
            <table>
                <%
                try{
                    
                    String DATABASE_URL = "jdbc:mysql://localhost/groep16_festivals";
                    String USERNAME = "root";
                    String PASSWORD = "";
                    // de pagina achter de knop meer info vullen
                    String sql = "select * from festivals f, tickettypesperfestival tpf, tickettypes t where f.fest_id = tpf.fest_id and t.typ_id = tpf.typ_id order by f.fest_id";
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);

                    Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
                        ResultSet.CONCUR_READ_ONLY);
                    ResultSet rs = stmt.executeQuery(sql);
                    out.println("<tr>");
                    out.println("<th>Naam</th>");
                    out.println("<th>Locatie</th>");
                    out.println("<th>Datum</th>");
                    out.println("<th>Duur</th>");
                    out.println("<th>ticketsoorten</th>");
                    out.println("<th>Prijs</th>");
                    out.println("</tr>");
                    while(rs.next()){
                        out.println("<td>" + rs.getString("fest_naam") + "</td>");
                        out.println("<td>" + rs.getString("fest_locatie") + "</td>");
                        out.println("<td>" + rs.getString("fest_datum") + "</td>");
                        out.println("<td>" + rs.getInt("fest_duur") + "</td>");
                        out.println("<td>" + rs.getString("typ_omschr") +"</td>");
                        out.println("<td>" + rs.getDouble("typ_prijs") + "</td>");
                        out.println("</tr>");
                    }
                    conn.close();
                }
                catch (Exception ex){
                   out.println("Er is een fout opgetreden");

                }

                %>
    </body>
</html>
