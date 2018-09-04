package sample.servletcomponents.sampleservletcomponents.Components.Servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns={"/servlet"})
public class ServletTest extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("----------Components.Servlets.ServletTest[doGet]----------");
        System.out.println("Components.Servlets.ServletTest[doGet] sendRedirect to home");
        resp.sendRedirect("/home");
    }
}
