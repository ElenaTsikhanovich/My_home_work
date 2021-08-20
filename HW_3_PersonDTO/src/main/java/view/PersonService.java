package view;

import model.Person;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class PersonService {

    private static final String FIRST_PARAM = "firstName";
    private static final String SECOND_PARAM = "lastName";
    private static final String THIRD_PARAM = "age";

    private static final String SESSION_NAME = "user";

    public void saveCookie(HttpServletResponse response, String key, String value) {
        if (value != null) {
            Cookie cookie = new Cookie(key, value);
            cookie.setMaxAge(Math.toIntExact(TimeUnit.DAYS.toSeconds(1)));
            response.addCookie(cookie);
        } else throw new IllegalArgumentException("Не указан один из обязательны параметров!");
    }

    public void saveSession(HttpServletRequest request, String firstname, String secondName, Integer age) {
        Person person = new Person(firstname, secondName, age);
        HttpSession session = request.getSession();
        session.setAttribute(SESSION_NAME, person);
    }

    public Person getFromCookie(HttpServletRequest request) {
        Person person = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            String firstName = Arrays.stream(cookies).filter(x -> FIRST_PARAM.equalsIgnoreCase(x.getName()))
                    .map(Cookie::getValue).findFirst().orElse(null);
            String lastName = Arrays.stream(cookies).filter(x -> SECOND_PARAM.equalsIgnoreCase(x.getName()))
                    .map(Cookie::getValue).findFirst().orElse(null);
            String age = Arrays.stream(cookies).filter(x -> THIRD_PARAM.equalsIgnoreCase(x.getName()))
                    .map(Cookie::getValue).findFirst().orElse(null);
            if (firstName == null || lastName == null || age == null) {
                throw new IllegalArgumentException("Не указан один из обязательных параметров!");
            }
            person = new Person(firstName, lastName, Integer.parseInt(age));
        }
        return person;
    }

    public Person getFromSession(HttpServletRequest request) {
        Person person = null;
        HttpSession session = request.getSession();
        if (!session.isNew()) {
            Object attribute = session.getAttribute(SESSION_NAME);
            if (attribute != null) {
                person = (Person) attribute;
            }
        }
        return person;
    }
}

