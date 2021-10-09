package controller.servlets;

import controller.utils.Params;
import model.Position;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.api.IPositionService;
import utils.AppContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "PositionServlet", urlPatterns = "/position")
public class PositionServlet extends HttpServlet {
    private ApplicationContext context=AppContext.getContext();
    private IPositionService iPositionService;

    public PositionServlet(){
        this.iPositionService= context.getBean(IPositionService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id;
        if ((id=req.getParameter(Params.ID.getTitle())) != null) {
            Position position = this.iPositionService.get(Long.valueOf(id));
            if(position!=null) {
                req.setAttribute("position", position);
                req.getRequestDispatcher("views/position.jsp").forward(req, resp);
            } else {
                req.setAttribute("exception","Должности с таким id нет в базе данных");
                req.getRequestDispatcher("views/positionMain.jsp").forward(req,resp);
            }
        }
        else if (req.getParameter(Params.LIST.getTitle())!=null) {
            List<Position> positions = this.iPositionService.getAll();
            req.setAttribute("positions", positions);
            req.getRequestDispatcher("views/positionList.jsp").forward(req, resp);
        }
        else req.getRequestDispatcher("views/positionMain.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter(Params.NAME.getTitle());
        if(name!="") {
            long id = this.iPositionService.add(name);
            req.setAttribute("registration", "Должность " + name + " внесена в базу под номером " + id);
        } else {
            req.setAttribute("registration", "Заполните все поля для регистрации");
        }
        req.getRequestDispatcher("views/positionMain.jsp").forward(req,resp);
    }
}
