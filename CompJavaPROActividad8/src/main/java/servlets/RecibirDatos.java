/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import calculos.Triangulo;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author patoe
 */
@WebServlet(name = "RecibirDatos", urlPatterns = {"/RecibirDatos"})
public class RecibirDatos extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) 
        {
            //Se crea el objeto de la sesión
            HttpSession sesion = request.getSession(false);
            
            //Se piden los valores del formulario del traingulo y se utilizan para el cálculo de los valores para guardarse en variables de tipo int
            String n1 = request.getParameter("base");
            String n2 = request.getParameter("altura");
            
            //Se piden los valores del nombre del formulario
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");

            //En caso de que no se llame al formulario (por que ya se habían registrado los valores con anterioridad) se llaman los valores de la sesión y se guardan en nombre y apellido
            if(sesion != null)
            {
                if(sesion.getAttribute("name") != null && sesion.getAttribute("lastName") != null)
                {
                    nombre = (String) sesion.getAttribute("name");
                    apellido = (String) sesion.getAttribute("lastName");
                }  
            } 
            
            //Se manda a llamar al filtro para que investigue el tipo de error que se presemtó
            Integer errorFiltros = (Integer) request.getAttribute("AVISO");
            
            if(errorFiltros != 0)   //Si hubo algún error, se llama al index.jsp
            {
                request.getRequestDispatcher("/index.jsp").forward(request, response);  
            }
            
            else
            {
                //Se generan los valores de la sesión actual
                sesion.setAttribute("name", nombre);
                sesion.setAttribute("lastName", apellido);
                
                request.setAttribute("nombreGuardado",nombre);
                request.setAttribute("apellidoGuardado", apellido);

                //Se crea el triangulo con los valores ingresados
                Triangulo t = new Triangulo(n1, n2);

                //Se crean las cookies para 
                Cookie ck = new Cookie("ckBase", t.getBase()+"");
                response.addCookie(ck);
                ck = new Cookie("ckAltura", t.getAltura()+"");
                response.addCookie(ck);
                ck = new Cookie("ckPerimetro", t.getPerimetro()+"");
                response.addCookie(ck);
                ck = new Cookie("ckArea", t.getArea()+"");
                response.addCookie(ck);

                request.setAttribute("resultado", t); 
                request.getRequestDispatcher("/mostrarResultado.jsp").forward(request, response);
            }
        }
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
