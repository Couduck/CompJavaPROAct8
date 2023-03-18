<%-- 
    Document   : index
    Created on : 16 feb. 2023, 21:19:29
    Author     : patoe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="servlets.RecibirDatos"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Collections"%>

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
            
            //El tipo de error que se encontró
            Integer errores = (Integer) request.getAttribute("AVISO"); 
        %>  
        
        <h1>Computación Avanzada en Java Actividad 8: ¡Filtros purificadores!</h1>
   
        <div>
            <form action="RecibirDatos" method="post">
                
                <%
                
                //Ayudan para detectar si hay alguna sesión o no, si no la hay, se pregguntará por el nombre
                List<String> lista = Collections.list(enumeracion);
                
                if(lista.size() <= 1)    //En caso de que no haya habido una sesión, se le pedirá que ingrese su nombre
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
        
        <%

            //En caso de que haya errores, se imprime un mensaje especificando cual ocurrió
            if(errores != null)
            {
            
                if(errores == 1)
                {
                    out.print("<h4>ERROR: Las medidas del triangulo no pueden ser menores o iguales a 0</h4>");
                }

                if(errores == 2)
                {
                    out.print("<h4>ERROR: Faltan datos por ingresar");
                }
            }
        %>
                
    </body>
</html>
