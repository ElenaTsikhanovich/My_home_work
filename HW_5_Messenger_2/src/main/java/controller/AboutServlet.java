package controller;

import storage.MessageStorageFactory;
import storage.UserStorageFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "AboutServlet",urlPatterns = "/about")
public class AboutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userStorage = String.valueOf(UserStorageFactory.getType());
        String messageStorage = String.valueOf(MessageStorageFactory.getType());
        req.setAttribute("userStorage",userStorage);
        req.setAttribute("messageStorage",messageStorage);

        String date = (String) req.getServletContext().getAttribute("date");
        req.setAttribute("startDate",date);

        req.getRequestDispatcher("/views/about.jsp").forward(req,resp);
    }
}
