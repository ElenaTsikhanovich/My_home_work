package controller.servlets;


import model.Department;
import model.Employer;
import model.Position;
import model.dto.EmployerParamsDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.api.IDepartmentService;
import service.api.IEmployerService;
import service.api.IPositionService;
import java.util.List;


@Controller
@RequestMapping("/employer")
public class EmployerServlet{

    private IEmployerService iEmployerService;
    private IDepartmentService iDepartmentService;
    private IPositionService iPositionService;

    public EmployerServlet(IEmployerService iEmployerService, IDepartmentService iDepartmentService, IPositionService iPositionService) {
        this.iEmployerService = iEmployerService;
        this.iDepartmentService = iDepartmentService;
        this.iPositionService = iPositionService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/id")
    public String get(Model model,
                      @RequestParam("id")Long id){
        Employer employer = iEmployerService.get(id);
        if (employer!=null){
            model.addAttribute("employer",employer);
            return "employer";
        }else {
            model.addAttribute("exception","Отдела с таким id нет в базе данных");
            List<Department> departments = iDepartmentService.getAll();
            model.addAttribute("departments",departments);
            List<Position> positions = iPositionService.getAll();
            model.addAttribute("positions",positions);
            return "employerMain";
        }
    }
//TODO объединить лимит и серч
    @RequestMapping(method = RequestMethod.GET,value = "/limit")
    public String getAllWithPagination(Model model,
            @RequestParam("page")Integer page,
            @RequestParam("limit")Integer limit){
        List<Employer> employers = iEmployerService.getLimit(limit, page);
        Long countOfEmployers = iEmployerService.getCount();
        long pageCount = (long) Math.ceil((double) countOfEmployers /limit);
        model.addAttribute("employers", employers);
        model.addAttribute("page", page);
        model.addAttribute("pageCount", pageCount);
        return "employerList";
    }


    @RequestMapping(method = RequestMethod.GET,value ="/search")
    public String getWithSearch(Model model,
            @RequestParam("page")Integer page,
            @RequestParam("limit")Integer limit,
            @RequestParam("name")String name,
            @RequestParam("salaryFrom") String salaryFrom,
            @RequestParam("salaryTo") String salaryTo){
        EmployerParamsDTO employerParamsDTO = new EmployerParamsDTO();
        employerParamsDTO.setPage(page);
        employerParamsDTO.setLimit(limit);
        employerParamsDTO.setName(name);
        employerParamsDTO.setSalaryFrom(salaryFrom.equalsIgnoreCase("")?0:Double.parseDouble(salaryFrom));
        employerParamsDTO.setSalaryTo(salaryTo.equalsIgnoreCase("")?99999999.99:Double.parseDouble(salaryTo));
        List<Employer> employers = iEmployerService.find(employerParamsDTO);
        Long countFromFind = iEmployerService.getCountFromFind(employerParamsDTO);
        long pageCount = (long) Math.ceil((double) countFromFind /limit);
        String url="&name="+name+"&salaryFrom="+salaryFrom+"&salaryTo="+salaryTo;
        model.addAttribute("url",url);
        model.addAttribute("employers", employers);
        model.addAttribute("page", page);
        model.addAttribute("pageCount", pageCount);
        return "employerList";

    }


    @RequestMapping(method = RequestMethod.GET)
    public String getMain(Model model){
        List<Department> departments = iDepartmentService.getAll();
        model.addAttribute("departments",departments);
        List<Position> positions = iPositionService.getAll();
        model.addAttribute("positions",positions);
        return "employerMain";
    }













}


