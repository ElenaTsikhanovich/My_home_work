package controller.servlets.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Position;
import service.PositionService;
import service.api.IPositionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "PositionJacksonServlet", urlPatterns = "/position_api")
public class PositionJacksonServlet extends HttpServlet {
    private ObjectMapper objectMapper=new ObjectMapper();
    private IPositionService iPositionService;

    public PositionJacksonServlet(){
        this.iPositionService= PositionService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("registration",req.getParameter("registration"));
        req.getRequestDispatcher("views/positionMain.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Position position = objectMapper.readValue(req.getInputStream(), Position.class);
        final long id = this.iPositionService.add(position);
        String registration="Должность " + position.getName() + " внесена в базу под номером " + id;
        resp.sendRedirect(req.getContextPath()+"/position_api?registration="+ URLEncoder.encode(registration,"UTF-8"));
    }
}
