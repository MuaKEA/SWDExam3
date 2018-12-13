package com.example.demo.Model.Repository;

import com.example.demo.Model.Invoice;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InvoiceRepository extends CrudRepository<Invoice, Long>{

List <Invoice>findByInvoiceId(Long id);
List<Invoice>findByInvoiceIdAndCustomerId(Long id,Long costumerid);

}
