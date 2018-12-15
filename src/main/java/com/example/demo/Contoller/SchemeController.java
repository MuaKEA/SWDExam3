package com.example.demo.Contoller;

import com.example.demo.Model.Repository.SchemeRepo;
import com.example.demo.Model.Scheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SchemeController {

@Autowired
    SchemeRepo schemeRepo;



@GetMapping("/kalender")
    public String calender(Model model){

    model.addAttribute("schema",new Scheme());


    return "schema";
}

@PostMapping("/kalender")
    public String calender(Scheme scheme){
    schemeRepo.save(scheme);

    return "redirect:/kalender";
}
 @GetMapping("/redigerKalender")
 public String redigerKalender(Model model, @RequestParam(value = "id") Long id){
    model.addAttribute("schema",schemeRepo.findById(id));




    return "editSchema";
 }

@PostMapping("/redigerKalender")
public String redigerKalender() {




    return "redirect:/index";
}




}
