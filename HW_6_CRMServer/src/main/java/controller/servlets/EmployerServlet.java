package controller.servlets;

import controller.utils.Params;
import model.Employer;
import model.dto.EmployerParamsDTO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.api.IDepartmentService;
import service.api.IEmployerService;
import service.api.IPositionService;
import utils.AppContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "EmployerServlet", urlPatterns = "/employer")
public class EmployerServlet extends HttpServlet {
    private ApplicationContext context= AppContext.getContext();
    private IEmployerService iEmployerService;
    private IDepartmentService iDepartmentService;
    private IPositionService iPositionService;

    public EmployerServlet() {
        this.iEmployerService=context.getBean(IEmployerService.class);
        this.iDepartmentService=context.getBean(IDepartmentService.class);
        this.iPositionService=context.getBean(IPositionService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id;
        if ((id=req.getParameter(Params.ID.getTitle())) != null) {
            Employer employer = this.iEmployerService.get(Long.valueOf(id));
            if (employer!= null) {
                req.setAttribute("employer", employer);
                req.getRequestDispatcher("views/employer.jsp").forward(req, resp);
            } else {
                req.setAttribute("exception", "Сотрудника с таким id нет в базе данных");
                req.setAttribute("positions", this.iPositionService.getAll());
                req.setAttribute("departments", this.iDepartmentService.getAll());
                req.getRequestDispatcher("views/employerMain.jsp").forward(req, resp);
            }
        } else if ((req.getParameter(Params.PAGE.getTitle())) != null) {
            String limitParam = req.getParameter(Params.LIMIT.getTitle());
            String pageParam = req.getParameter(Params.PAGE.getTitle());
            int limit = Integer.parseInt(limitParam);
            int page = Integer.parseInt(pageParam);
            List<Employer> employers;
            long countOfEmployers;
            if (req.getParameter(Params.SALARY_FROM.getTitle())!=null) {
                String name = req.getParameter(Params.NAME.getTitle());
                String from = req.getParameter(Params.SALARY_FROM.getTitle());
                String to = req.getParameter(Params.SALARY_TO.getTitle());
                EmployerParamsDTO employerParamsDTO = new EmployerParamsDTO();
                employerParamsDTO.setName(name);
                employerParamsDTO.setSalaryFrom(from.equalsIgnoreCase("") ? 0 : Double.parseDouble(from));
                employerParamsDTO.setSalaryTo(to.equalsIgnoreCase("") ? 99999999.99 : Double.parseDouble(to));
                employerParamsDTO.setPage(page);
                employerParamsDTO.setLimit(limit);
                employers = this.iEmployerService.find(employerParamsDTO);
                countOfEmployers=this.iEmployerService.getCountFromFind(employerParamsDTO);
                String url="&name="+name+"&salaryFrom="+from+"&salaryTo="+to;
                req.setAttribute("url",url);
            }else {
                employers = this.iEmployerService.getLimit(limit,page);
                countOfEmployers = this.iEmployerService.getCount();
            }
            long pageCount = (long) Math.ceil((double) countOfEmployers / limit);
            req.setAttribute("employers", employers);
            req.setAttribute("page", page);
            req.setAttribute("pageCount", pageCount);
            req.getRequestDispatcher("views/employerList.jsp").forward(req, resp);
        }else {
            req.setAttribute("positions", this.iPositionService.getAll());
            req.setAttribute("departments", this.iDepartmentService.getAll());
            req.getRequestDispatcher("views/employerMain.jsp").forward(req, resp);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter(Params.NAME.getTitle());
        String salary = req.getParameter(Params.SALARY.getTitle());
        String departmentId = req.getParameter(Params.DEPARTMENT.getTitle());
        String positionId = req.getParameter(Params.POSITION.getTitle());
        if (name != "" && salary != null && departmentId != null && positionId != null) {
            long id = this.iEmployerService.add(name, Double.valueOf(salary), Long.valueOf(departmentId), Long.valueOf(positionId));
            req.setAttribute("registration","Сотрудник " + name + " успешно зарегистрирован под номером " + id);
        } else {
            req.setAttribute("registration", "Заполните все поля для регистрации");
        }
        req.setAttribute("positions", this.iPositionService.getAll());
        req.setAttribute("departments", this.iDepartmentService.getAll());
        req.getRequestDispatcher("views/employerMain.jsp").forward(req, resp);
        }
}


