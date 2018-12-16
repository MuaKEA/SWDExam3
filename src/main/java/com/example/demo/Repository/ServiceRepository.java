package com.example.demo.Repository;

import com.example.demo.Model.Service;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ServiceRepository extends CrudRepository<Service, Long> {
Service findByName(String name);
List<Service>findAll();
}

