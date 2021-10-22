package controller.servlets;

import controller.utils.Params;
import model.Department;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.api.IDepartmentService;
import utils.AppContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/department")
public class DepartmentServlet {

    private IDepartmentService iDepartmentService;

    public DepartmentServlet(IDepartmentService iDepartmentService) {
        this.iDepartmentService = iDepartmentService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/id")
    public String get(Model model,
                      @RequestParam("id")Long id){
        Department department = iDepartmentService.get(id);
        if (department!=null){
            model.addAttribute("department",department);
            return "department";
        }else {
            model.addAttribute("exception","Отдела с таким id нет в базе данных");
            List<Department> departments = iDepartmentService.getAll();
            model.addAttribute("departments",departments);
            return "departmentMain";
        }
    }

    @RequestMapping(method = RequestMethod.GET,value = "/list")
    public String getAll(Model model){
            List<Department> departments = iDepartmentService.getAll();
            model.addAttribute("departments",departments);
            if(departments!=null){
                return "departmentList";
            } else return "departmentMain";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getMain(Model model){
        List<Department> departments = iDepartmentService.getAll();
        model.addAttribute("departments",departments);
        return "departmentMain";
    }








    /*
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       if(req.getParameter(Params.ID.getTitle())!=null){
           byId(req, resp);
        } else {
            List<Department> departments = this.iDepartmentService.getAll();
            req.setAttribute("departments",departments);
            if(req.getParameter(Params.LIST.getTitle())!=null){
                req.getRequestDispatcher("views/departmentList.jsp").forward(req,resp);
            } else {
                req.getRequestDispatcher("views/departmentMain.jsp").forward(req,resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter(Params.NAME.getTitle());
        String parentId = req.getParameter(Params.PARENT.getTitle());
        if (name!="" && parentId!=null) {
            long id = this.iDepartmentService.add(name, Long.valueOf(parentId));
            req.setAttribute("registration", name + " успешно зарегистрирован под номером " + id);
        }else {
            req.setAttribute("registration", "Заполните все поля для регистрации");
        }
        req.getRequestDispatcher("views/departmentMain.jsp").forward(req,resp);
    }

    private void byId(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter(Params.ID.getTitle());
        Department department = this.iDepartmentService.get(Long.valueOf(id));
        if(department!=null) {
            request.setAttribute("department", department);
            request.getRequestDispatcher("views/department.jsp").forward(request, response);
        } else {
            request.setAttribute("exception","Отдела с таким id нет в базе данных");
            request.getRequestDispatcher("views/departmentMain.jsp").forward(request,response);
        }
    }

     */
}
