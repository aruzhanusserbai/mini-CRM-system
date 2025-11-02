package resApi.api.contollers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import resApi.api.entities.Application;
import resApi.api.services.RequestsService;

@Controller
@RequiredArgsConstructor
public class RequestsController {
    private final RequestsService requestsService;

    @GetMapping("/addReq")
    public String addReqPage(Model model){
        model.addAttribute("request", new Application());
        return "addReq";
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("requests", requestsService.getRequests());
        return "index";
    }

    @PutMapping("/addRequest")
    public String addRequest(Application request){
        requestsService.addRequest(request);
        return "redirect:/";
    }

    @PostMapping("/pageMore")
    public String pageMore(Model model, @RequestParam("id") Long id){
        model.addAttribute("request", requestsService.getRequest(id));
        return "pageMore";
    }

    @PutMapping("/handleRequest")
    public String handleRequest(@RequestParam("id")Long id){
        requestsService.handleRequest(id);
        return "redirect:/";
    }

    @DeleteMapping("/deleteRequest")
    public String deleteRequest(@RequestParam("id")Long id){
        requestsService.delete(id);
        return "redirect:/";
    }

    @GetMapping("/notHandled")
    public String notHandled(Model model){
        model.addAttribute("requests", requestsService.notHandled());
        return "notHandled";
    }

    @GetMapping("/handled")
    public String handled(Model model){
        model.addAttribute("requests", requestsService.handled());
        return "handled";
    }
}
