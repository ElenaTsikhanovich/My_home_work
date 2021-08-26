package controller;

import model.User;
import service.UserService;
import service.api.IUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UsersServlet", urlPatterns = "/user")
public class UsersServlet extends HttpServlet {
    private final IUserService userService;

    public UsersServlet() {
        this.userService=UserService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userService.allUsers();
        req.setAttribute("allUsers",users);
        req.getRequestDispatcher("/views/user.jsp").forward(req,resp);
    }
}
