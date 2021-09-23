package controller.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Employer;
import service.EmployerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EmployerJacksonServlet", urlPatterns = "/employer_test")
public class EmployerJacksonServlet extends HttpServlet {
    private ObjectMapper objectMapper=new ObjectMapper();
    private EmployerService employerService;

    public EmployerJacksonServlet(){
        this.employerService=EmployerService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setAttribute("registration",req.getParameter("registration"));
        req.getRequestDispatcher("views/employerMain.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Employer employer = objectMapper.readValue(req.getInputStream(), Employer.class);
        long id = this.employerService.add(employer);
        String registration="Сотрудник " + employer.getName() + " успешно зарегистрирован под номером " + id;
        resp.sendRedirect(req.getContextPath()+"/employer_test?registration="+registration);
    }
    
}

