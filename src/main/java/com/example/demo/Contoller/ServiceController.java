package com.example.demo.Contoller;

import com.example.demo.Model.Repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServiceController {
    @Autowired
    private ServiceRepository serviceRepository;

    @GetMapping("/createService")
    public String showInvoice(MO)


}