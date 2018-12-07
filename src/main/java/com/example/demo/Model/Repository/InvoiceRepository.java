package com.example.demo.Model.Repository;

import com.example.demo.Model.Invoice;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceRepository extends CrudRepository<Invoice, Long>{
}
