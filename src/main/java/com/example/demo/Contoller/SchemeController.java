package com.example.demo.Contoller;

import com.example.demo.Repository.ScheduleRepo;
import com.example.demo.Model.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SchemeController {

@Autowired
ScheduleRepo scheduleRepo;

@GetMapping("/kalender")
    public String calender(Model model){

    Schedule schedule=scheduleRepo.findByScheduleid(1L);
    model.addAttribute("schedule",schedule );
    return "schedule";
}

@PostMapping("/kalender")
    public String calender(Schedule schedule){
      scheduleRepo.deleteAll();
     schedule.setScheduleid(1L);
      scheduleRepo.save(schedule);

    return "redirect:/kalender";
}





}
