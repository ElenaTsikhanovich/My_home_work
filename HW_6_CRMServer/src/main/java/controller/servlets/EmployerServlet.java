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
import org.springframework.web.bind.annotation.RestController;
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











   /*

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter(Params.ID.getTitle())!=null){
            byId(req, resp);

        }else if (req.getParameter(Params.PAGE.getTitle())!=null && req.getParameter(Params.SALARY_TO.getTitle())==null){
            paginationAll(req, resp);

        } else if(req.getParameter(Params.PAGE.getTitle())!=null && req.getParameter(Params.SALARY_TO.getTitle())!=null){
            paginationByNameAndSalary(req, resp);
        }else {
            req.setAttribute("positions", this.iPositionService.getAll());
            req.setAttribute("departments", this.iDepartmentService.getAll());
            req.getRequestDispatcher("views/employerMain.jsp").forward(req, resp);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter(Params.NAME.getTitle());
        String salary = req.getParameter(Params.SALARY.getTitle());
        String departmentId = req.getParameter(Params.DEPARTMENT.getTitle());
        String positionId = req.getParameter(Params.POSITION.getTitle());
        if (name != "" && salary != null && departmentId != null && positionId != null) {
            long id = this.iEmployerService.add(name, Double.valueOf(salary), Long.valueOf(departmentId), Long.valueOf(positionId));
            req.setAttribute("registration","Сотрудник " + name + " успешно зарегистрирован под номером " + id);
        } else {
            req.setAttribute("registration", "Заполните все поля для регистрации");
        }
        req.setAttribute("positions", this.iPositionService.getAll());
        req.setAttribute("departments", this.iDepartmentService.getAll());
        req.getRequestDispatcher("views/employerMain.jsp").forward(req, resp);
        }


        private void byId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String id = request.getParameter(Params.ID.getTitle());
            Employer employer = this.iEmployerService.get(Long.valueOf(id));
            if (employer != null) {
                request.setAttribute("employer", employer);
                request.getRequestDispatcher("views/employer.jsp").forward(request, response);
            } else {
                request.setAttribute("exception", "Сотрудника с таким id нет в базе данных");
                request.setAttribute("positions", this.iPositionService.getAll());
                request.setAttribute("departments", this.iDepartmentService.getAll());
                request.getRequestDispatcher("views/employerMain.jsp").forward(request, response);
            }
        }

        private void paginationAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String page = request.getParameter(Params.PAGE.getTitle());
            String limit = request.getParameter(Params.LIMIT.getTitle());
            List<Employer> employers=this.iEmployerService.getLimit(Integer.parseInt(limit),Integer.parseInt(page));
            Long countOfEmployers = this.iEmployerService.getCount();
            long pageCount = (long) Math.ceil((double) countOfEmployers / Long.parseLong(limit));
            request.setAttribute("employers", employers);
            request.setAttribute("page", page);
            request.setAttribute("pageCount", pageCount);
            request.getRequestDispatcher("views/employerList.jsp").forward(request, response);
        }

        private void paginationByNameAndSalary(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
            String page = request.getParameter(Params.PAGE.getTitle());
            String limit = request.getParameter(Params.LIMIT.getTitle());
            String name = request.getParameter(Params.NAME.getTitle());
            String from = request.getParameter(Params.SALARY_FROM.getTitle());
            String to = request.getParameter(Params.SALARY_TO.getTitle());
            EmployerParamsDTO employerParamsDTO = new EmployerParamsDTO();
            employerParamsDTO.setName(name);
            employerParamsDTO.setSalaryFrom(from.equalsIgnoreCase("") ? 0 : Double.parseDouble(from));
            employerParamsDTO.setSalaryTo(to.equalsIgnoreCase("") ? 99999999.99 : Double.parseDouble(to));
            employerParamsDTO.setPage(Integer.parseInt(page));
            employerParamsDTO.setLimit(Integer.parseInt(limit));
            List<Employer>employers = this.iEmployerService.find(employerParamsDTO);
            Long countOfEmployers=this.iEmployerService.getCountFromFind(employerParamsDTO);
            long pageCount = (long) Math.ceil((double) countOfEmployers / Long.parseLong(limit));
            String url="&name="+name+"&salaryFrom="+from+"&salaryTo="+to;
            request.setAttribute("url",url);
            request.setAttribute("employers", employers);
            request.setAttribute("page", page);
            request.setAttribute("pageCount", pageCount);
            request.getRequestDispatcher("views/employerList.jsp").forward(request, response);
        }

     */

}


