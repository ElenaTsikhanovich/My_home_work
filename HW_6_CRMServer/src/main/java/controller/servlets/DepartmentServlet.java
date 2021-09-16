package controller.servlets;

import model.Department;
import model.Employer;
import service.DepartmentService;
import service.EmployerService;

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
    private static String PARAM_LIST="list";

    private DepartmentService departmentService;
    public DepartmentServlet(){
        this.departmentService=DepartmentService.getInstance();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter(PARAM_ID)!=null) {
            String id = req.getParameter(PARAM_ID);
            Department department = this.departmentService.getDepartment(Long.valueOf(id));
            req.setAttribute("department", department);
            req.getRequestDispatcher("views/department.jsp").forward(req, resp);
        }
        else if (req.getParameter(PARAM_LIST)!=null){
            List<Department> departments = this.departmentService.getDepartments();
            req.setAttribute("departments",departments);
            req.getRequestDispatcher("views/departmentList.jsp").forward(req,resp);
        }
        else req.getRequestDispatcher("views/departmentMain.jsp").forward(req,resp);
    }
}
