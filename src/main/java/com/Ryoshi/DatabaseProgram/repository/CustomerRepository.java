package com.Ryoshi.DatabaseProgram.repository;

import com.Ryoshi.DatabaseProgram.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer,Long> {
}
