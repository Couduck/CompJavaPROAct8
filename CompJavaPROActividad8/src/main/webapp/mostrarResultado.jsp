<%-- 
    Document   : mostrarResultado
    Created on : 16 feb. 2023, 22:55:40
    Author     : patoe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="calculos.Triangulo"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Resultados del calculo</title>
    </head>
    <body>
             
        <!-- Se manda a llamar el resultado del servlet y se despliegan los resultados en pantalla -->
        <%
                Triangulo result = (Triangulo) request.getAttribute("resultado");
        %>
        
                <h1>Resultados del calculo con servlet</h1>
                <h2>Area</h2>
                <%=result.getArea()%>
                <br><br>
                <h2>Perimetro</h2>
                <%=result.getPerimetro()%>
    </body>
</html>
