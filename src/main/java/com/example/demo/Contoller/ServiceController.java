    package com.example.demo.Contoller;

    import com.example.demo.Repository.ServiceRepository;
    import com.example.demo.Model.Service;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.*;

    import java.util.Random;

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
            long x = 1000;
            long y = 10000;
            Random r = new Random();
            long number = x+((long)(r.nextDouble()*(y-x)));
            service.setServiceId(number);
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

        @PostMapping("/redigerService")
        public String editService(@ModelAttribute("serviceDelete") Service service){
            serviceRepository.deleteById(service.getId());
            serviceRepository.save(service);
            return "redirect:/visService";
        }

        @GetMapping("/sletService")
        public String deleteService(@RequestParam(value = "id") Long id){
            serviceRepository.deleteById(id);
            return "redirect:/visService";
        }
    }
