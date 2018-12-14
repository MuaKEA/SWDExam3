package com.example.demo.Contoller;

import com.example.demo.Model.Customer;
import com.example.demo.Model.Invoice;
import com.example.demo.Model.InvoiceWrapper;
import com.example.demo.Model.Repository.CustomerRepository;
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

import java.util.ArrayList;
import java.util.List;

@Controller
public class InvoiceController {
    public Long Id;
    public Long invoiceId=6L;

    @Autowired
    private InvoiceRepository invoiceRepository;
   @Autowired
   private ServiceRepository serviceRepository;
   @Autowired
    private CustomerRepository customerRepository;

   @GetMapping("/visFaktura")
    public String showInvoice(@RequestParam(value = "id") Long id, Model model){
            model.addAttribute("invoice",invoiceRepository.findById(id));

            return "invoice";

    }


    @GetMapping("/visSendteFaktura")
    public String showInvoices(Model model) {

        model.addAttribute("invoiceList", invoiceRepository.findAll());


        return "showInvoices";
    }

    @GetMapping("/opretFaktura")
    public String createInvoice(Model model){
        InvoiceWrapper invoices= new InvoiceWrapper();

        for (int i = 0; i <10 ; i++) {
            Invoice invoice = new Invoice();
          invoiceId=invoice.getInvoiceId();
            invoices.addinvoice(invoice);
        }


       model.addAttribute("Invoices", invoices);
        model.addAttribute("Service",serviceRepository.findAll());
        model.addAttribute("Customer",customerRepository.findAll());

        return "createInvoice";
    }

/*
    @GetMapping("/opretKunde")
    public String createCostumer(Model model){
       model.addAttribute("createCostumer",new Costumer());
       return "createCostumer";
    }

    @PostMapping("/opretKunde")
    public String createCostumer (Costumer costumer){
       costumerRepository.save(costumer);

       return "redirect:/createInvoice";
    }
*/

    @PostMapping("/save")
    public String createInvoice(InvoiceWrapper invoiceWrapper){
        ArrayList<Invoice> invoiceArrayList=invoiceWrapper.getInvoiceArrayList();
        System.out.println("iam here");
        for (int i = 0; i <invoiceArrayList.size() ; i++) {
              invoiceRepository.save(invoiceArrayList.get(i));
        }


        return "redirect:/opretFaktura";
    }

    @GetMapping("/kvittering")
    public String confirmation(Model model){
        List<Invoice> invoices=invoiceRepository.findByInvoiceId(invoiceId);
        Long customerid=null;
        for (int i = 0; i <invoices.size(); i++) {
            System.out.println(invoices.get(1).getService().getName());
            customerid=invoices.get(i).getCustomer().getId();
            System.out.println(customerid);
            System.out.println(invoices.get(i));
        }



        model.addAttribute("Customer", customerRepository.findByid(customerid));
        model.addAttribute("invoiceList", invoiceRepository.findByInvoiceId(invoiceId));
        return "confirmation";
    }


    @GetMapping("/redigerFaktura")
    public String editInvoice(@RequestParam(value = "id") Long id, Model model){
            Id=id;
        System.out.println(invoiceRepository.findById(id) + " GET:from invocie id" );
        model.addAttribute("invoice", invoiceRepository.findById(id));
        return "editInvoice";
    }



    @PostMapping("/redigerFaktura")
    public String editInvoice(Invoice invoice){
        System.out.println(invoice+" Invoice invoice");
        Invoice invoice1 = new Invoice();
        invoice1.setInvoiceId(invoice.getInvoiceId());
        invoice1.setPayed(invoice.getPayed());
        invoice1.setUnit(invoice.getUnit());
        invoice1.setPrice(invoice.getPrice());
        invoice1.setTotalPrice(invoice.getTotalPrice());
        invoice1.setService(serviceRepository.findByName(invoice.getService().getName()));
        invoice1.setCustomer(customerRepository.findByName(invoice.getCustomer().getName()));
        invoiceRepository.deleteById(Id);

        invoiceRepository.save(invoice1);
        System.out.println(invoice1+ "invoice1");
        return "redirect:/visSendteFaktura";
    }

    @GetMapping("/sletFaktura")
    public String deleteInvoice(@RequestParam(value = "id") Long id){
        invoiceRepository.deleteById(id);
        return "redirect:/visSendteFaktura";
    }
    @GetMapping("/all")
    public String showAll(Model model) {
        model.addAttribute("invoices", invoiceRepository.findAll());
        return "books/allBooks";
    }

    @GetMapping("/markereregning")
    public String markereregning(@RequestParam(value = "id") Long id){

//       Invoice invoice=invoiceRepository.findByInvoiceId(id);
//        System.out.println(invoiceRepository.findById(id));
//       invoice.setPayed(true);
//        invoiceRepository.save(invoice);
//        System.out.println(invoiceRepository.findById(id));

        return "redirect:/visSendteFaktura";
    }

}
