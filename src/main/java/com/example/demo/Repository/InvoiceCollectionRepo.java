package com.example.demo.Repository;

import com.example.demo.Model.InvoiceCollection;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface InvoiceCollectionRepo extends CrudRepository<InvoiceCollection,Long> {
InvoiceCollection findByInvoiceId(Long id);
List<InvoiceCollection> findAll();
List<InvoiceCollection> findAllByInvoiceDateAndPaid(String date,Boolean b);
InvoiceCollection deleteByInvoiceId(Long id);

}
