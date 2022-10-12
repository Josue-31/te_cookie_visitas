package com.emergentes;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ContadorServlet", urlPatterns = {"/ContadorServlet"})
public class ContadorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int contador = 0;
        
        //obtener el arreglo de cookies del cliente
        Cookie cukis[] = request.getCookies();
        
        if (cukis !=null) {
            for (Cookie c : cukis) {
                if (c.getName().equals("visitas")) {
                    //antes de la asignacion se conbierte un valor en cadena
                    contador = Integer.parseInt(c.getValue());             
                }
            }
        }
        contador++;
        //antes de la asignacion se convierte cadena en valor o entero
        Cookie c = new Cookie("visitas",Integer.toString(contador));
        
        c.setMaxAge(300);
        response.addCookie(c);
        
        //generar contenido  a partir del servlet
        response.setContentType("text/html");
        
        
        PrintWriter out = response.getWriter();
        out.println("visitante Nro:" + contador);        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

}
