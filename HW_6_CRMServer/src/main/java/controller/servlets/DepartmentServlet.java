package controller.servlets;

import model.Department;
import model.Employer;
import service.DepartmentService;
import service.EmployerService;
import service.api.IDepartmentService;

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
    private static String PARAM_NAME="name";
    private static String PARAM_PARENT="parent";

    private IDepartmentService iDepartmentService;

    public DepartmentServlet(){
        this.iDepartmentService=DepartmentService.getInstance();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter(PARAM_ID)!=null) {
            String id = req.getParameter(PARAM_ID);
            Department department = this.iDepartmentService.get(Long.valueOf(id));
            if(department.getName()!=null) {
                req.setAttribute("department", department);
                req.getRequestDispatcher("views/department.jsp").forward(req, resp);
            } else {
                req.setAttribute("exception","Отдела с таким id нет в базе данных");
                req.getRequestDispatcher("views/departmentMain.jsp").forward(req,resp);
            }
        }
        else if (req.getParameter(PARAM_LIST)!=null){
            List<Department> departments = this.iDepartmentService.getAll();
            req.setAttribute("departments",departments);
            req.getRequestDispatcher("views/departmentList.jsp").forward(req,resp);
        }
        else {
            List<Department> departments = this.iDepartmentService.getAll();
            req.setAttribute("departments",departments);
            req.getRequestDispatcher("views/departmentMain.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter(PARAM_NAME);
        String parentId = req.getParameter(PARAM_PARENT);
        long id = this.iDepartmentService.add(name, Long.valueOf(parentId));
        req.setAttribute("registration",name+" успешно зарегистрирован под номером " +id);
        req.getRequestDispatcher("views/departmentMain.jsp").forward(req,resp);

    }
}
