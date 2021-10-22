package controller.servlets.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Department;
import model.Employer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.api.IDepartmentService;

@RestController
@RequestMapping("/api/departments")
public class DepartmentJacksonServlet {

    private IDepartmentService iDepartmentService;

    public DepartmentJacksonServlet(IDepartmentService iDepartmentService) {
        this.iDepartmentService = iDepartmentService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> add(@RequestBody Department department){
       return new ResponseEntity<>(iDepartmentService.add(department),HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<?> get(@PathVariable("id")Long depId){
        return new ResponseEntity<>(iDepartmentService.get(depId), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(iDepartmentService.getAll(),HttpStatus.OK);
    }






    /*
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

     */
}
