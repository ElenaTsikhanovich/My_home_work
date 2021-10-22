package controller.servlets.api;

import model.Employer;
import model.dto.EmployerParamsDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.api.IEmployerService;

@RestController
@RequestMapping("/api/employers")
public class EmployerJacksonServlet {

    private IEmployerService iEmployerService;

    public EmployerJacksonServlet(IEmployerService iEmployerService) {
        this.iEmployerService = iEmployerService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> add(@RequestBody Employer employer){
        return new ResponseEntity<>(iEmployerService.add(employer), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<?> get(@PathVariable ("id") Long id){
        return new ResponseEntity<>(iEmployerService.get(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(iEmployerService.getAll(),HttpStatus.OK);
    }

















    /*

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
        resp.sendRedirect(req.getContextPath()+"/employer_api?registration="+ URLEncoder.encode(registration,"UTF-8"));
    }


     */

    }
