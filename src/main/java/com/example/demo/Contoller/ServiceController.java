package com.example.demo.Contoller;

import com.example.demo.Model.Repository.ServiceRepository;
import com.example.demo.Model.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ServiceController {
    @Autowired
    private ServiceRepository serviceRepository;

    @GetMapping("/opretService")
    public String createService(Model model){
        model.addAttribute("service", new Service());
        return "createService";
    }

    @PostMapping("/opretService")
    public String createService(Service service){
        serviceRepository.save(service);
        return "redirect:/visService";
    }

    @GetMapping("/visService")
    public String showService(Model model){
        model.addAttribute("serviceList", serviceRepository.findAll());
        return "showService";
    }

    @GetMapping("/redigerService")
    public String editService(@RequestParam(value = "id") Long id, Model model){
        model.addAttribute("service", serviceRepository.findById(id));
        return "editService";
    }
    //kan ikke finde id
    @PostMapping("/redigerService")
    public String editService(@ModelAttribute("serviceDelete") Service service){
        System.out.println(service.getId());
        serviceRepository.deleteById(service.getId());
        serviceRepository.save(service);
        return "redirect:/visService";
    }

    @GetMapping("/sletService")
    public String deleteService(@RequestParam(value = "id") Long id){
        serviceRepository.deleteById(id);
        return "redirect:/visService";
    }
   /* @GetMapping ("/sletService/{id}")
        public String deleteService (){

        return "redirect:/visService";

    }*/

}
