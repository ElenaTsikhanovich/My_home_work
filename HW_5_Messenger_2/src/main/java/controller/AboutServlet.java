package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "AboutServlet",urlPatterns = "/about")
public class AboutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");

        String storage = req.getServletContext().getInitParameter("storage");
        req.setAttribute("storage",storage);

        String date = (String) req.getServletContext().getAttribute("date");
        req.setAttribute("startDate",date);

        req.getRequestDispatcher("/views/about.jsp").forward(req,resp);
    }
}
