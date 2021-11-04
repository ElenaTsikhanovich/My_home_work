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









}
