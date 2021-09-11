/*package controller.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Employer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EmployerJacksonServlet", urlPatterns = "/employer_test")
public class EmployerJacksonServlet extends HttpServlet {
    private ObjectMapper objectMapper=new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Employer employer = objectMapper.readValue(req.getInputStream(), Employer.class);

    }
}


 */