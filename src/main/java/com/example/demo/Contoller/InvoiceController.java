package com.example.demo.Contoller;

import com.example.demo.Model.Invoice;
import com.example.demo.Model.Repository.InvoiceRepository;
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

    @GetMapping("/visFaktura")
    public String showInvoice(Model model){
            model.addAttribute("invoice",invoiceRepository.findAll());

            return "showInvoice";

    }


    @GetMapping("opretFaktura")
    public String createInvoice(Model model){
        model.addAttribute("invoice", new Invoice());

        return "createInvoice";
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

    @PostMapping("redigerFaktura")
    public String editInvoice(@ModelAttribute Invoice invoice){
        invoiceRepository.save(invoice);
        return "redirect:/editInvoice";
    }

    @GetMapping("sletFaktura")
    public String deleteInvoice(@RequestParam(value = "id") Long id){
        invoiceRepository.deleteById(id);
        return "showInvoice";
    }

}
