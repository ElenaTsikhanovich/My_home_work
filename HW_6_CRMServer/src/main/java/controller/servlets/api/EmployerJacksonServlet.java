package controller.servlets.api;

import model.Employer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
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
        long employId = iEmployerService.add(employer);
        return new ResponseEntity<>(employId,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<?> get(@PathVariable ("id") Long id){
        return new ResponseEntity<>(iEmployerService.get(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(iEmployerService.getAll(),HttpStatus.OK);
    }












    }
