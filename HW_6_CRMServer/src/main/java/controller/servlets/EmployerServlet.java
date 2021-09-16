package controller.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.List;

@WebServlet(name = "EmployerServlet", urlPatterns = "/employer")
public class EmployerServlet extends HttpServlet {
    private static String PARAM_NAME="name";
    private static String PARAM_SALARY="salary";
    private static String PARAM_DEP="department";
    private static String PARAM_POS="position";
    private static String PARAM_ID="id";
    private static String PARAM_LIMIT="limit";
    private static String PARAM_PAGE="page";

    private EmployerService employerService;

    public EmployerServlet(){
        this.employerService=EmployerService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       if(req.getParameter(PARAM_ID)!=null) {
           String id = req.getParameter(PARAM_ID);
           Employer employer = this.employerService.getEmployer(Long.valueOf(id));
           req.setAttribute("employer", employer);
           req.getRequestDispatcher("views/employer.jsp").forward(req, resp);
       }
       else if((req.getParameter(PARAM_PAGE))!=null){
           String limitParam = req.getParameter(PARAM_LIMIT);
           String pageParam = req.getParameter(PARAM_PAGE);
           long limit = Long.parseLong(limitParam);
           long page = Long.parseLong(pageParam);
           List<Employer> limitEmployers =
                   this.employerService.getLimitEmployers(limit,page);
           long countOfEmployers = this.employerService.getCountOfEmployers();
           long pageCount;
           if(countOfEmployers%limit==0){
               pageCount=countOfEmployers/limit;
           }else pageCount=(countOfEmployers/limit)+1;
           req.setAttribute("employers",limitEmployers);
           req.setAttribute("page",page);
           req.setAttribute("pageCount",pageCount);
           req.getRequestDispatcher("views/employerList.jsp").forward(req,resp);
       }
       else req.getRequestDispatcher("views/employerMain.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter(PARAM_NAME);
        String salary = req.getParameter(PARAM_SALARY);
        String departmentId = req.getParameter(PARAM_DEP);
        String positionId = req.getParameter(PARAM_POS);
        long addId = this.employerService.add(name, Double.valueOf(salary), Long.valueOf(departmentId), Long.valueOf(positionId));
        req.setAttribute("name",name);
        req.setAttribute("id",String.valueOf(addId));
        req.getRequestDispatcher("views/employerReg.jsp").forward(req,resp);




    }
}
