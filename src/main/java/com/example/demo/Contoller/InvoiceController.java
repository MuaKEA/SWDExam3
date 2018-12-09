package com.example.demo.Contoller;

import com.example.demo.Model.Costumer;
import com.example.demo.Model.Invoice;
import com.example.demo.Model.Repository.CostumerRepository;
import com.example.demo.Model.Repository.InvoiceRepository;
import com.example.demo.Model.Repository.ServiceRepository;
import com.example.demo.Model.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class InvoiceController {
    public Long Id;

    @Autowired
    private InvoiceRepository invoiceRepository;
   @Autowired
   private ServiceRepository serviceRepository;
   @Autowired
   private CostumerRepository costumerRepository;


   @GetMapping("/visFaktura")
    public String showInvoice(@RequestParam(value = "id") Long id, Model model){
            model.addAttribute("invoice",invoiceRepository.findById(id));

            return "invoice";

    }


    @GetMapping("/vis_sendteFaktura")
    public String showInvoices(Model model) {
        for (Invoice v : invoiceRepository.findAll()) {
            System.out.println(v);
        }
        System.out.println("iam here");
        model.addAttribute("invoiceList", invoiceRepository.findAll());


        return "showInvoices";
    }

    @GetMapping("/opretFaktura")
    public String createInvoice(Model model){
        model.addAttribute("invoice", new Invoice());
        model.addAttribute("Service",serviceRepository.findAll());
        model.addAttribute("Costumer",costumerRepository.findAll());

        return "createInvoice";
    }


    @GetMapping("/opretKunde")
    public String createCostumer(Model model){
       model.addAttribute("createCostumer",new Costumer());
       return "createCostumer";
    }

    @PostMapping("/opretKunde")
    public String createCostumer (Costumer costumer){
       costumerRepository.save(costumer);

       return "redirect:/opretFaktura";
    }


    @PostMapping("/opretFaktura")
    public String createInvoice(Invoice invoice){
        invoiceRepository.save(invoice);
        return "redirect:/confirmation";
    }

    @GetMapping("/redigerFaktura")
    public String editInvoice(@RequestParam(value = "id") Long id, Model model){
        model.addAttribute("invoice", invoiceRepository.findById(id));
        return "editInvoice";
    }

    @PostMapping("/redigerFaktura")
    public String editInvoice(@ModelAttribute Invoice invoice){
        invoiceRepository.save(invoice);
        return "redirect:/editInvoice";
    }

    @GetMapping("/sletFaktura")
    public String deleteInvoice(@RequestParam(value = "id") Long id){
        invoiceRepository.deleteById(id);
        return "showInvoice";
    }


}
