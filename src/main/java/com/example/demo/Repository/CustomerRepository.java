package com.example.demo.Repository;

import com.example.demo.Model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer,Long> {

Customer findByName(String name);
Customer findByid(Long id);
}
