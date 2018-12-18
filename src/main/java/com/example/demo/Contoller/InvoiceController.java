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






    @GetMapping("/kvittering")
    public String confirmation(Model model){


        model.addAttribute("services",invoiceCollectionRepo.findByInvoiceId(invoiceId).getInvoices());
        model.addAttribute("invoiceList", invoiceCollectionRepo.findByInvoiceId(invoiceId));
        return "confirmation";
    }
    @GetMapping("/opretFaktura")
    public String createInvoice(Model model){
        InvoiceWrapper invoices= new InvoiceWrapper();
        generateobjecs(invoices);


        model.addAttribute("Invoices", invoices);
        model.addAttribute("Customer",customerRepository.findAll());

        return "createInvoice";
    }

    @GetMapping("/redigerFaktura")
    public String editInvoice(@RequestParam(value = "id") Long id, Model model){

    j=serviceRepository.findAll();
    InvoiceWrapper invoiceWrapper= new InvoiceWrapper();
    generateobjecs(invoiceWrapper);


        for (int i = 0; i <invoiceCollectionRepo.findByInvoiceId(id).getInvoices().size() ; i++) {
      invoiceWrapper.getInvoiceArrayList().get(i).setService(invoiceCollectionRepo.findByInvoiceId(id).getInvoices().get(i).getService());
      invoiceWrapper.getInvoiceArrayList().get(i).setUnit(invoiceCollectionRepo.findByInvoiceId(id).getInvoices().get(i).getUnit());
      invoiceWrapper.getInvoiceArrayList().get(i).setPrice(invoiceCollectionRepo.findByInvoiceId(id).getInvoices().get(i).getPrice());

        }


        model.addAttribute("Invoices", invoiceWrapper);
        model.addAttribute("Customer", customerRepository.findAll());
        return "editInvoice";
    }

    private void generateobjecs(InvoiceWrapper invoiceWrapper) {
        j= serviceRepository.findAll();
        for (int i = 0; i <j.size(); i++) {
            Service servio=j.get(i);
            Invoice invoices = new Invoice();
            invoices.setService(servio);
            invoiceWrapper.addinvoice(invoices);
        }
    }

    @PostMapping("/editedInvoice")
    public String editInvoice(InvoiceCollection invoiceCollection){
        System.out.println(invoiceCollection.getInvoices().get(1));



        return "redirect:/visSendteFaktura";
    }


    @PostMapping("/save")
    public String createInvoice(InvoiceWrapper invoiceWrapper){
        Long totalprice=0L;
        Long customerId=0L;


        for (int i = 0; i <invoiceWrapper.getInvoiceArrayList().size() ; i++) {
            customerId=invoiceWrapper.getInvoiceArrayList().get(i).getCustomer().getId();
            invoiceWrapper.getInvoiceArrayList().get(i).setService(j.get(i));

            if(invoiceWrapper.getInvoiceArrayList().get(i).getPrice()==0 ||invoiceWrapper.getInvoiceArrayList().get(i).getUnit()==0){
                invoiceWrapper.getInvoiceArrayList().remove(i);
            }else
                totalprice+=invoiceWrapper.getInvoiceArrayList().get(i).getPrice();


        }
        DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd 00:00:00 zzz yyyy");
        Long totalpricewithtax=(totalprice/100L*25) + totalprice ;
        invoiceRepository.saveAll(invoiceWrapper.getInvoiceArrayList());
        Customer customer= customerRepository.findByid(customerId);
        System.out.println(totalprice);
        System.out.println(totalpricewithtax);
        InvoiceCollection invoiceCollection = new InvoiceCollection(generateRandomLong(),false,totalpricewithtax,customer.getFirmName(),customer.getEmail(),customer.getName(),dateFormat.format(invoiceWrapper.getInvoiceCalendar())+"",dateFormat.format(invoiceWrapper.getDueCalendar()) +"",invoiceWrapper.getInvoiceArrayList());
        invoiceCollectionRepo.save(invoiceCollection);
        return "redirect:/kvittering";
    }


    @GetMapping("/sletFaktura")
    public String deleteInvoice(@RequestParam(value = "id") Long id){
        InvoiceCollection invoiceCollection = invoiceCollectionRepo.findByInvoiceId(id);
        invoiceCollectionRepo.delete(invoiceCollection);
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

        List<InvoiceCollection> invoiceCollectionList =invoiceCollectionRepo.findAllByInvoiceDateAndPaid(dateFormat.format(date)+"",true);
        if(invoiceCollectionList.size()==0){

        }else
            System.out.println("List is not empty");


            for (int i = 0; i <invoiceCollectionList.size() ; i++) {
                totalprice+=invoiceCollectionList.get(i).getTotalPris();
            }
    }

    return totalprice;
}
public Long generateRandomLong(){
        long x = 1234567L;
    long y = 2345678L;
    Random r = new Random();
    long number = x+((long)(r.nextDouble()*(y-x)));
    invoiceId=number;

    return number;
}
}