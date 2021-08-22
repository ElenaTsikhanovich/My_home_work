package controller;

import model.User;
import service.MessageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "MessageServlet", urlPatterns = "/message")
public class MessageServlet extends HttpServlet {
    private MessageService messageService = MessageService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        req.setAttribute("userName", user.getFio());
        req.getRequestDispatcher("/views/message.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        User user = (User) req.getSession().getAttribute("user");
        req.setAttribute("userName", user.getFio());
        boolean answer = messageService.addMessage(req);
        if (answer) {
            req.setAttribute("addMessage", "Ваше сообщение успешно отправлено!");
        } else {
            req.setAttribute("addMessage", "Вы не можете отправлять сообщения незарегистрированным пользователям!");
     }
        req.getRequestDispatcher("/views/message.jsp").forward(req, resp);
    }
}
