package controller.servlets.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Position;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.api.IPositionService;

@RestController
@RequestMapping("/api/positions")
public class PositionJacksonServlet {

    private IPositionService iPositionService;

    public PositionJacksonServlet(IPositionService iPositionService) {
        this.iPositionService = iPositionService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> add(@RequestBody Position position){
        return new ResponseEntity<>(iPositionService.add(position), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Long id){
        return new ResponseEntity<>(iPositionService.get(id),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(iPositionService.getAll(),HttpStatus.OK);
    }






    /*
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("registration",req.getParameter("registration"));
        req.getRequestDispatcher("views/positionMain.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Position position = objectMapper.readValue(req.getInputStream(), Position.class);
        final long id = this.iPositionService.add(position);
        String registration="Должность " + position.getName() + " внесена в базу под номером " + id;
        resp.sendRedirect(req.getContextPath()+"/position_api?registration="+ URLEncoder.encode(registration,"UTF-8"));
    }

     */
}
