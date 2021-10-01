package controller.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import controller.utils.Params;
import model.Department;
import model.Employer;
import model.Position;
import model.dto.EmployerParamsDTO;
import service.DepartmentService;
import service.EmployerService;
import service.PositionService;
import service.api.IDepartmentService;
import service.api.IEmployerService;
import service.api.IPositionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "EmployerServlet", urlPatterns = "/employer")
public class EmployerServlet extends HttpServlet {
    private IEmployerService iEmployerService;
    private IDepartmentService iDepartmentService;
    private IPositionService iPositionService;

    public EmployerServlet() {
        this.iEmployerService = EmployerService.getInstance();
        this.iDepartmentService = DepartmentService.getInstance();
        this.iPositionService = PositionService.getInstance();
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
        }
        else if ((req.getParameter(Params.PAGE.getTitle())) != null) {
            String limitParam = req.getParameter(Params.LIMIT.getTitle());
            String pageParam = req.getParameter(Params.PAGE.getTitle());
            long limit = Long.parseLong(limitParam);
            long page = Long.parseLong(pageParam);
            List<Employer> limitEmployers =
                    this.iEmployerService.getLimit(limit, page);
            long countOfEmployers = this.iEmployerService.getCount();
            long pageCount = (long) Math.ceil((double) countOfEmployers / limit);
            req.setAttribute("employers", limitEmployers);
            req.setAttribute("page", page);
            req.setAttribute("pageCount", pageCount);
            req.getRequestDispatcher("views/employerList.jsp").forward(req, resp);
        }
        else if(req.getParameter(Params.SALARY_FROM.getTitle())!=null){
            String name = req.getParameter(Params.NAME.getTitle());
            String from = req.getParameter(Params.SALARY_FROM.getTitle());
            String to = req.getParameter(Params.SALARY_TO.getTitle());
            EmployerParamsDTO employerParamsDTO = new EmployerParamsDTO();
            employerParamsDTO.setName(name);
            employerParamsDTO.setSalaryFrom(Double.valueOf(from));
            employerParamsDTO.setSalaryTo(Double.valueOf(to));
            List<Employer> employers = this.iEmployerService.find(employerParamsDTO);
            req.setAttribute("employers",employers);
            req.getRequestDispatcher("views/employerList.jsp").forward(req,resp);
        }
        else {
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


