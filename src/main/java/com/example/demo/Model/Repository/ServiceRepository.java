package com.example.demo.Model.Repository;

import com.example.demo.Model.Service;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ServiceRepository extends CrudRepository<Service, Long> {
Service findByName(String name);
List<Service>findAll();
}

