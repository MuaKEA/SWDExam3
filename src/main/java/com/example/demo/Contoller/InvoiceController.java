package com.example.demo.Contoller;

import com.example.demo.Model.Repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InvoiceController {

    @Autowired
    private InvoiceRepository invoiceRepository;

    //@GetMapping("/createInvoice")

}
