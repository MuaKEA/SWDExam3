package com.example.demo.Model.Repository;

import com.example.demo.Model.InvoiceCollection;
import org.springframework.data.repository.CrudRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


public interface InvoiceCollectionRepo extends CrudRepository<InvoiceCollection,Long> {
InvoiceCollection findByInvoiceId(Long id);
List<InvoiceCollection> findAll();
List<InvoiceCollection> findAllByInvoiceDate(String date);
}
