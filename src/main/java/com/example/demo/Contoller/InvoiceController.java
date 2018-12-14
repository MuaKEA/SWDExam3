package com.example.demo.Contoller;

import com.example.demo.Model.*;
import com.example.demo.Model.Repository.CustomerRepository;
import com.example.demo.Model.Repository.InvoiceCollectionRepo;
import com.example.demo.Model.Repository.InvoiceRepository;
import com.example.demo.Model.Repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Random;

@Controller
public class InvoiceController {
    public Long Id;
    public Long invoiceId=74L;

    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private InvoiceCollectionRepo invoiceCollectionRepo;

    @GetMapping("/visFaktura")
    public String showInvoice(@RequestParam(value = "id") Long id, Model model){
        model.addAttribute("invoice",invoiceRepository.findById(id));

        return "invoice";

    }


    @GetMapping("/visSendteFaktura")
    public String showInvoices(Model model) {

        model.addAttribute("invoiceList", invoiceCollectionRepo.findAll());


        return "showInvoices";
    }

    @GetMapping("/opretFaktura")
    public String createInvoice(Model model){
        InvoiceWrapper invoices= new InvoiceWrapper();
        List <Service> j= serviceRepository.findAll();

        System.out.println(j.size());


        for (int i = 0; i <j.size(); i++) {
            Service service=j.get(i);

            Invoice invoice = new Invoice();
            invoice.setService(service);
            invoices.addinvoice(invoice);
        }


        model.addAttribute("Invoices", invoices);
        model.addAttribute("Customer",customerRepository.findAll());

        return "createInvoice";
    }


    @PostMapping("/save")
    public String createInvoice(InvoiceWrapper invoiceWrapper){
        Long totalprice=0L;
        Long customerId=0L;

        long x = 1234567L;
        long y = 2345678L;
        Random r = new Random();
        long number = x+((long)(r.nextDouble()*(y-x)));
        invoiceId=number;
        System.out.println("1");
        for (int i = 0; i <invoiceWrapper.getInvoiceArrayList().size() ; i++) {
            System.out.println(invoiceWrapper.getInvoiceArrayList().get(i).getCustomer().getName());
            System.out.println(invoiceWrapper.getInvoiceArrayList().get(i).getCustomer().getFirmName());
            System.out.println(invoiceWrapper.getInvoiceArrayList().get(i).getCustomer().getEmail());
            customerId=invoiceWrapper.getInvoiceArrayList().get(i).getCustomer().getId();



            if(invoiceWrapper.getInvoiceArrayList().get(i).getPrice()==0 ||invoiceWrapper.getInvoiceArrayList().get(i).getUnit()==0){
                invoiceWrapper.getInvoiceArrayList().remove(i);

            }
            totalprice+=invoiceWrapper.getInvoiceArrayList().get(i).getPrice();


        }
        System.out.println("2");
        invoiceRepository.saveAll(invoiceWrapper.getInvoiceArrayList());
        Customer customer= customerRepository.findByid(customerId);
        System.out.println(customer);
        InvoiceCollection invoiceCollection = new InvoiceCollection(number,false,totalprice,customer.getFirmName(),customer.getEmail(),customer.getName(),invoiceWrapper.getInvoiceArrayList());
        invoiceCollectionRepo.save(invoiceCollection);
        return "redirect:/opretFaktura";
    }

    @GetMapping("/kvittering")
    public String confirmation(Model model){
        InvoiceCollection invoices=invoiceCollectionRepo.findByInvoiceId(invoiceId);

        for (int i = 0; i <invoices.getInvoices().size() ; i++) {
            System.out.println(invoices.getInvoices().get(i));
        }


        model.addAttribute("Customer", customerRepository.findByid(invoices.getId()));
        model.addAttribute("invoiceList", invoices);
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
        invoice1.setUnit(invoice.getUnit());
        invoice1.setPrice(invoice.getPrice());
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
        InvoiceCollection invoiceCollection =invoiceCollectionRepo.findByInvoiceId(id);
        invoiceCollection.setPaid(true);



        return "redirect:/visSendteFaktura";
    }

}