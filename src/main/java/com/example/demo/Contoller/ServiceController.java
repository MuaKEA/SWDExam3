package com.example.demo.Contoller;

import com.example.demo.Model.Repository.ServiceRepository;
import com.example.demo.Model.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ServiceController {
    @Autowired
    private ServiceRepository serviceRepository;

    @GetMapping("/opretService")
    public String showInvoice(Model model){
        model.addAttribute("service", new Service());
        return "createService";
    }

    @PostMapping("/opretService")
    public String showInvoice(Service service){
        serviceRepository.save(service);
        return "redirect:/menu";
    }

}
