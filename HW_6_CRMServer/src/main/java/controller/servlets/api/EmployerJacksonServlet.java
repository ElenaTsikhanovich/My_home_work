package controller.servlets.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Employer;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.EmployerService;
import service.api.IDepartmentService;
import service.api.IEmployerService;
import utils.AppContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "EmployerJacksonServlet", urlPatterns = "/employer_api")
public class EmployerJacksonServlet extends HttpServlet {
    private ObjectMapper objectMapper=new ObjectMapper();
    private ClassPathXmlApplicationContext context= AppContext.getContext();
    private IEmployerService iEmployerService;

    public EmployerJacksonServlet(){
       this.iEmployerService=context.getBean(IEmployerService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("registration",req.getParameter("registration"));
        req.getRequestDispatcher("views/employerMain.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Employer employer = objectMapper.readValue(req.getInputStream(), Employer.class);
        long id = this.iEmployerService.add(employer);
        String registration="Сотрудник " + employer.getName() + " успешно зарегистрирован под номером " + id;
        resp.sendRedirect(req.getContextPath()+"/employer_api?registration="+URLEncoder.encode(registration,"UTF-8"));
    }
}

