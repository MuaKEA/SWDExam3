package com.example.demo.Contoller;

import com.example.demo.Model.*;

import com.example.demo.Repository.CustomerRepository;
import com.example.demo.Repository.InvoiceCollectionRepo;
import com.example.demo.Repository.InvoiceRepository;
import com.example.demo.Repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class InvoiceController {
    public Long Id;
    public Long invoiceId;


    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private InvoiceCollectionRepo invoiceCollectionRepo;
    public List <Service> j;


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
         j= serviceRepository.findAll();

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
        System.out.println(j.get(1) +  " =iam here");
        Long totalprice=0L;
        Long customerId=0L;
        long x = 1234567L;
        long y = 2345678L;
        Random r = new Random();
        long number = x+((long)(r.nextDouble()*(y-x)));
        invoiceId=number;

        for (int i = 0; i <invoiceWrapper.getInvoiceArrayList().size() ; i++) {
            customerId=invoiceWrapper.getInvoiceArrayList().get(i).getCustomer().getId();
            invoiceWrapper.getInvoiceArrayList().get(i).setService(j.get(i));

            if(invoiceWrapper.getInvoiceArrayList().get(i).getPrice()==0 ||invoiceWrapper.getInvoiceArrayList().get(i).getUnit()==0){
                invoiceWrapper.getInvoiceArrayList().remove(i);
            }
            totalprice+=invoiceWrapper.getInvoiceArrayList().get(i).getPrice();


        }
        DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd 00:00:00 zzz yyyy");
        Long totalpricewithtax=(totalprice/100L*25) + totalprice ;
        invoiceRepository.saveAll(invoiceWrapper.getInvoiceArrayList());
        Customer customer= customerRepository.findByid(customerId);
        System.out.println(totalprice);
        System.out.println(totalpricewithtax);
        InvoiceCollection invoiceCollection = new InvoiceCollection(number,false,totalpricewithtax,customer.getFirmName(),customer.getEmail(),customer.getName(),dateFormat.format(invoiceWrapper.getInvoiceCalendar())+"",dateFormat.format(invoiceWrapper.getDueCalendar()) +"",invoiceWrapper.getInvoiceArrayList());
        invoiceCollectionRepo.save(invoiceCollection);
        return "redirect:/kvittering";
    }

    @GetMapping("/kvittering")
    public String confirmation(Model model){



        model.addAttribute("invoiceList", invoiceCollectionRepo.findByInvoiceId(invoiceId));
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
        invoiceCollectionRepo.deleteById(id);
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

        invoiceCollectionRepo.save(invoiceCollection);


        return "redirect:/visSendteFaktura";
    }

    @GetMapping("/statistik")
     public String statistik(Model model){
        ArrayList<Long> mothdayyear= new ArrayList<>();
        mothdayyear.add(statistikregner(1));
        mothdayyear.add(statistikregner(3));
        mothdayyear.add(statistikregner(12));

        model.addAttribute("statistics",mothdayyear);

        return "statisticInvoice" ;





    }

public Long statistikregner(int minusmoth){

    DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd 00:00:00 zzz yyyy");
    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.MONTH, -minusmoth);
    Calendar cal1 = Calendar.getInstance();


    Calendar start = Calendar.getInstance();
    start.setTime(cal.getTime());
    Calendar end = Calendar.getInstance();
    end.setTime(cal1.getTime());
    Long totalprice=0L;
    for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {

        List<InvoiceCollection> invoiceCollectionList =invoiceCollectionRepo.findAllByInvoiceDate(dateFormat.format(date)+"");
        if(invoiceCollectionList.size()==0){

        }else
            System.out.println("List is not empty");


            for (int i = 0; i <invoiceCollectionList.size() ; i++) {
                totalprice+=invoiceCollectionList.get(i).getTotalPris();
            }
    }

    return totalprice;
}

}