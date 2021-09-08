package controller.servlets;

import model.Department;
import model.Employer;
import model.Position;
import service.DataService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ListServlet", urlPatterns = "/list")
public class ListServlet extends HttpServlet {
    private static String PARAM_LIST="list";
    private DataService dataService;
    public ListServlet(){
        this.dataService=DataService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parameter = req.getParameter(PARAM_LIST);
        switch (parameter){
            case "employers":
                List<Employer> employers = this.dataService.getEmployers();
                req.setAttribute("employers", employers);
                break;
            case "departments":
                List<Department> departments = this.dataService.getDepartments();
                req.setAttribute("departments",departments);
                break;
            case "positions":
                List<Position> positions = this.dataService.getPositions();
                req.setAttribute("positions",positions);
                break;
        }

        req.getRequestDispatcher("views/list.jsp").forward(req,resp);
    }
}
