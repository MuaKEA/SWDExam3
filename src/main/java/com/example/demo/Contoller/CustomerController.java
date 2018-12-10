package com.example.demo.Contoller;

import com.example.demo.Model.Customer;
import com.example.demo.Model.Customer;
import com.example.demo.Model.Repository.CustomerRepository;
import com.example.demo.Model.Repository.CustomerRepository;
import com.example.demo.Model.Repository.InvoiceRepository;
import com.example.demo.Model.Repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CustomerController {
@Autowired
private CustomerRepository customerRepository;
@Autowired
private ServiceRepository serviceRepository;
@Autowired
private InvoiceRepository invoiceRepository;
public Long id;

    @GetMapping("/opretKunde")
    public String createCostumer(Model model){
        model.addAttribute("customer",new Customer());
        return "createCustomer";
    }

    @PostMapping("/opretKunde")
    public String createCostumer (Customer customer) {
        customerRepository.save(customer);

        return "redirect:/createCustomer";
    }

    @GetMapping("/redigerKunde")
    public String editCostomer(@RequestParam(value = "id") Long id, Model model){
        model.addAttribute("editCustomer", customerRepository.findById(id));

        return "editCustomer";
    }

    @PostMapping("/redigerKunde")
    public String editCustomer(@ModelAttribute Customer customer){
        customerRepository.save(customer);
        return "redirect:/createCustomer";
    }

    @GetMapping("/sletKunde")
    public String deleteCustomer(@RequestParam(value = "id") Long id){
        customerRepository.deleteById(id);

        return "redirect:/createCustomer";
    }

}
