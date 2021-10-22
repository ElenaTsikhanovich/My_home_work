package controller.servlets;

import model.Position;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.api.IPositionService;

import java.util.List;


@Controller
@RequestMapping("/position")
public class PositionServlet {

    private IPositionService iPositionService;

    public PositionServlet(IPositionService iPositionService) {
        this.iPositionService = iPositionService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/id")
    public String get(Model model,
            @RequestParam("id")Long id) {
        Position position = iPositionService.get(id);
        if(position!=null) {
            model.addAttribute("position", position);
            return "position";
        }else {
            model.addAttribute("exception","Должности с таким id нет в базе данных");
            return "positionMain";
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public String getAll(Model model){
        List<Position> positions = iPositionService.getAll();
        model.addAttribute("positions",positions);
        return "positionList";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getMain(){
        return "positionMain";
    }








    /*

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id;
        if ((id=req.getParameter(Params.ID.getTitle())) != null) {
            Position position = this.iPositionService.get(Long.valueOf(id));
            if(position!=null) {
                req.setAttribute("position", position);
                req.getRequestDispatcher("views/position.jsp").forward(req, resp);
            } else {
                req.setAttribute("exception","Должности с таким id нет в базе данных");
                req.getRequestDispatcher("views/positionMain.jsp").forward(req,resp);
            }
        }
        else if (req.getParameter(Params.LIST.getTitle())!=null) {
            List<Position> positions = this.iPositionService.getAll();
            req.setAttribute("positions", positions);
            req.getRequestDispatcher("views/positionList.jsp").forward(req, resp);
        }
        else req.getRequestDispatcher("views/positionMain.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter(Params.NAME.getTitle());
        if(name!="") {
            long id = this.iPositionService.add(name);
            req.setAttribute("registration", "Должность " + name + " внесена в базу под номером " + id);
        } else {
            req.setAttribute("registration", "Заполните все поля для регистрации");
        }
        req.getRequestDispatcher("views/positionMain.jsp").forward(req,resp);
    }

     */
}
