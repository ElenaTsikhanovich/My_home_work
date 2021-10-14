package controller.servlets;

import controller.utils.Params;
import model.Department;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.api.IDepartmentService;
import utils.AppContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DepartmentServlet", urlPatterns = "/department")
public class DepartmentServlet extends HttpServlet {
    private ApplicationContext context= AppContext.getContext();
    private IDepartmentService iDepartmentService;

    public DepartmentServlet(){
        this.iDepartmentService=context.getBean(IDepartmentService.class);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       if(req.getParameter(Params.ID.getTitle())!=null){
           byId(req, resp);
        } else {
            List<Department> departments = this.iDepartmentService.getAll();
            req.setAttribute("departments",departments);
            if(req.getParameter(Params.LIST.getTitle())!=null){
                req.getRequestDispatcher("views/departmentList.jsp").forward(req,resp);
            } else {
                req.getRequestDispatcher("views/departmentMain.jsp").forward(req,resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter(Params.NAME.getTitle());
        String parentId = req.getParameter(Params.PARENT.getTitle());
        if (name!="" && parentId!=null) {
            long id = this.iDepartmentService.add(name, Long.valueOf(parentId));
            req.setAttribute("registration", name + " успешно зарегистрирован под номером " + id);
        }else {
            req.setAttribute("registration", "Заполните все поля для регистрации");
        }
        req.getRequestDispatcher("views/departmentMain.jsp").forward(req,resp);
    }

    private void byId(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter(Params.ID.getTitle());
        Department department = this.iDepartmentService.get(Long.valueOf(id));
        if(department!=null) {
            request.setAttribute("department", department);
            request.getRequestDispatcher("views/department.jsp").forward(request, response);
        } else {
            request.setAttribute("exception","Отдела с таким id нет в базе данных");
            request.getRequestDispatcher("views/departmentMain.jsp").forward(request,response);
        }
    }
}
