package controller.servlets;

import model.Department;
import model.Employer;
import model.Position;

import service.DepartmentService;
import service.EmployerService;
import service.PositionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

@WebServlet(name = "PositionServlet", urlPatterns = "/position")
public class PositionServlet extends HttpServlet {
    private static String PARAM_ID="id";
    private static String PARAM_LIST="list";
    private PositionService positionService;
    public PositionServlet(){
        this.positionService=PositionService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter(PARAM_ID)!= null) {
            String id = req.getParameter(PARAM_ID);
            Position position = this.positionService.getPosition(Long.valueOf(id));
            if(position.getName()!=null) {
                req.setAttribute("position", position);
                req.getRequestDispatcher("views/position.jsp").forward(req, resp);
            } else {
                req.setAttribute("exception","Должности с таким id нет в базе данных");
                req.getRequestDispatcher("views/positionMain.jsp").forward(req,resp);
            }
        }
        else if (req.getParameter(PARAM_LIST)!=null) {
            List<Position> positions = this.positionService.getPositions();
            req.setAttribute("positions", positions);
            req.getRequestDispatcher("views/positionList.jsp").forward(req, resp);
        }
        else req.getRequestDispatcher("views/positionMain.jsp").forward(req,resp);
    }
}
