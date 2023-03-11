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
            //Se piden los valores del formulario y se utilizan para el cálculo de los valores para guardarse en variables de tipo int
            String n1 = request.getParameter("base");
            String n2 = request.getParameter("altura");
        
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            
            HttpSession sesion = request.getSession(false);
            sesion.setAttribute("name", nombre);
            sesion.setAttribute("lastName", apellido);

            Triangulo t = new Triangulo(n1, n2);
            
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
