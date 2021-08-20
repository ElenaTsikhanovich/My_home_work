package controller;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name ="SignInServlet",urlPatterns = "/signIn")
public class SignInServlet extends HttpServlet {
    private static UserService userService=UserService.getInstance();
    private String SESSION_NAME="user";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/signIn.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = userService.getUser(req);
        if(user==null){
            req.setAttribute("info","Неверно введен логин или пароль! Попробуйте еще раз или зарегистрируйтесь!");
            req.getRequestDispatcher("/views/signIn.jsp").forward(req,resp);
        } else {
            HttpSession session = req.getSession();
            session.setAttribute(SESSION_NAME, user);
            req.setAttribute("userName",user.getFio());
            req.getRequestDispatcher("/views/chose.jsp").forward(req, resp);
        }

    }
}
