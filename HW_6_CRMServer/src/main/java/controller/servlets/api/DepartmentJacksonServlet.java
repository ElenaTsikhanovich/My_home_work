package controller.servlets.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Department;
import service.DepartmentService;
import service.api.IDepartmentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "DepartmentJacksonServlet ",urlPatterns = "/department_api")
public class DepartmentJacksonServlet extends HttpServlet {
    private ObjectMapper objectMapper=new ObjectMapper();
    private IDepartmentService iDepartmentService;

    public DepartmentJacksonServlet(){
        this.iDepartmentService= DepartmentService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("registration",req.getParameter("registration"));
        req.getRequestDispatcher("views/departmentMain.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Department department = objectMapper.readValue(req.getInputStream(), Department.class);
        final long id= this.iDepartmentService.add(department);
        String registration=department.getName() + " внесен в базу под номером " + id;
        resp.sendRedirect(req.getContextPath()+"/department_api?registration="+ URLEncoder.encode(registration,"UTF-8"));
    }
}
