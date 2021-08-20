package controller;

import model.Person;
import view.PersonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "PersonServlet",urlPatterns = "/")
public class PersonServlet extends HttpServlet {

    private static final String FIRST_PARAM = "firstName";
    private static final String SECOND_PARAM = "lastName";
    private static final String THIRD_PARAM = "age";

    private static final String HEADER_NAME = "Person";

    private static PersonService personService = new PersonService();
    private static Person person=null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();

        if (personService.getFromCookie(req) != null) {
            person = personService.getFromCookie(req);
        }
        if (personService.getFromSession(req) != null) {
            person = personService.getFromSession(req);
        }
        if(person==null){
            writer.write("Пользователь не найден!");
        } else writer.write("<h2>Данные пользователя</h2>" +
                "<p>Имя: " + person.getFirstName() + "</p>" +
                "<p>Фамилия: " + person.getLastName() + "</p>" +
                "<p>Возраст: " + person.getAge() + "</p>");
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter(FIRST_PARAM);
        String secondName = req.getParameter(SECOND_PARAM);
        String age = req.getParameter(THIRD_PARAM);

        String header = req.getHeader(HEADER_NAME);
        if (header == null) {
            throw new NullPointerException("Не указано хранилище!");
        }
        if (header != null) {
            switch (header) {
                case "cookie":
                    personService.saveCookie(resp, FIRST_PARAM, firstName);
                    personService.saveCookie(resp, SECOND_PARAM, secondName);
                    personService.saveCookie(resp, THIRD_PARAM, age);
                    break;
                case "session":
                    personService.saveSession(req,firstName,secondName,Integer.parseInt(age));
                    break;
                default:
                    throw new IllegalArgumentException("Не верно указано хранилище!");
            }
        }
    }
}






