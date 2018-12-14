package com.example.demo.Model.Repository;

import com.example.demo.Model.InvoiceCollection;
import org.springframework.data.repository.CrudRepository;


public interface InvoiceCollectionRepo extends CrudRepository<InvoiceCollection,Long> {
InvoiceCollection findByInvoiceId(Long id);
}
