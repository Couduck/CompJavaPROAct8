<%-- 
    Document   : index
    Created on : 16 feb. 2023, 21:19:29
    Author     : patoe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="servlets.RecibirDatos"%>
<%@page import="java.util.Enumeration"%>

<!DOCTYPE html>
<html>
    <!-- Documento principal del proyecto -->
    <head>
        <title>Principal</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        
        <%
            //Se inicializa la sesión, las cookies y las variables donde mostrar los atributos del traingulo
            HttpSession sesion = request.getSession(false);
            Enumeration enumeracion = sesion.getAttributeNames();
            
            Cookie[] cke = request.getCookies();
            String base="0", altura="0", perimetro="0", area="0";
        %>  
        
        <h1>Computación Avanzada en Java Actividad 7: ¡Cookies 'n Sessions!</h1>
   
        <div>
            <form action="RecibirDatos" method="post">
                
                <%
                if(!enumeracion.hasMoreElements())    //En caso de que no haya habido una sesión, se le pedirá que ingrese su nombre
                {
                %>
                
                    <h3>Ingresa tu nombre para recordarlo mas tarde!</h3>
                        Nombre:<br>
                        <input type="text" name="nombre" value=""><br><br>
                        Apellido:<br>
                        <input type="text" name="apellido" value=""><br><br>

                <%
                } 
                
                else    //Si ya hubo una sesión, se mostrará el nombre ingresado anteriormente
                {
                    out.print("<h2>Bienvenido de vuelta " + sesion.getAttribute("name") + " " + sesion.getAttribute("lastName") + "!</h2>");
                }
                
                if(cke != null) //Aqui es donde se cargan los valores de las cookies para mostrar los cálculos del último triangulo calculado
                {

                    for(int i = 0; i<cke.length; i++)
                    {
                        if(cke[i].getName().equals("ckBase"))
                        {
                            base = cke[i].getValue();
                        }

                        if(cke[i].getName().equals("ckAltura"))
                        {
                            altura = cke[i].getValue();
                        }

                        if(cke[i].getName().equals("ckPerimetro"))
                        {
                            perimetro = cke[i].getValue();
                        }

                        if(cke[i].getName().equals("ckArea"))
                        {
                            area = cke[i].getValue();
                        }
                    }

                    out.print("<h2>Datos del último triangulo calculado</h2>");
                    out.print("Base = " + base + "<br>");
                    out.print("Altura = " + altura + "<br>");
                    out.print("Perimetro = " + perimetro + "<br>");
                    out.print("Area = " + area + "<br>");

                }

                %>

            <!-- Formulario para el cálculo del traingulo-->
            <h3>Ingrese los datos del triangulo</h3>
                Base:<br>
                    <input type="text" name="base" value=""><br><br>
                Altura:<br>
                    <input type="text" name="altura" value=""><br><br>
                <input type="submit" value="Calcular">
            </form>
        </div>
        
    </body>
</html>
