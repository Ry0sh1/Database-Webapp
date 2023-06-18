package com.Ryoshi.DatabaseProgram.repository;

import com.Ryoshi.DatabaseProgram.model.Owner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends CrudRepository<Owner, Long> { }
