package com.example.fullstackapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.fullstackapp.models.EmployeeDepartment;

@Repository
public interface DepartmentRepository extends MongoRepository<EmployeeDepartment, Long>{

}
