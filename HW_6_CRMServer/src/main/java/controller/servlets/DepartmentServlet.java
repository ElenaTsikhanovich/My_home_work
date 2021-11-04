package controller.servlets;

import controller.utils.Params;
import model.Department;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
            return "departmentMain";
        }
    }

    @RequestMapping(method = RequestMethod.GET,value = "/list")
    public String getAll(Model model){
                return "departmentList";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getMain(Model model){
        return "departmentMain";
    }


    @ModelAttribute("departments")
    public List<Department> parentAttribute(Model model){
        List<Department> departments = iDepartmentService.getAll();
        return departments;
    }






}
