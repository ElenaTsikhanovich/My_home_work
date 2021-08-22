package controller;
import service.MessageService;
import model.Message;

import java.util.List;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ChatsServlet",urlPatterns = "/chats")
public class ChatsServlet extends HttpServlet {
    private MessageService messageService= MessageService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Message> list = messageService.getMessage(req);
        req.setAttribute("list",list);
        req.getRequestDispatcher("/views/chats.jsp").forward(req,resp);


    }
}