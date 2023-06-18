package com.Ryoshi.DatabaseProgram.repository;

import com.Ryoshi.DatabaseProgram.model.Dogs;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DogRepository extends CrudRepository<Dogs, Long> {

    @Transactional
    void deleteAllByOwnerId(long owner_id);

}
