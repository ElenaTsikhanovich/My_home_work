package controller.servlets;

import model.Department;
import model.Employer;
import service.DataService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "DepartmentServlet", urlPatterns = "/department")
public class DepartmentServlet extends HttpServlet {
    private static String PARAM_ID="id";
    private DataService dataService;
    public DepartmentServlet(){
        this.dataService=DataService.getInstance();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter(PARAM_ID);
        Department department = this.dataService.getDepartment(Long.valueOf(id));
        List<Employer> employerInDep = this.dataService.getEmployerInDep(Long.valueOf(id));
        req.setAttribute("department",department);
        req.setAttribute("employerInDep",employerInDep);
        req.getRequestDispatcher("views/department.jsp").forward(req,resp);
    }
}
