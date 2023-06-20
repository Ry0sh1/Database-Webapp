package com.Ryoshi.DatabaseProgram.repository;

import com.Ryoshi.DatabaseProgram.model.Dogs;
import com.Ryoshi.DatabaseProgram.model.Owner;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DogRepository extends CrudRepository<Dogs, Long> {

    @Transactional
    void deleteAllByOwnerId(long owner_id);

    @Query("SELECT breed FROM Dogs")
    List<String> getBreed();

    List<Dogs> findAllByBreed(String breed);
    List<Dogs> findAllByOwner(Owner owner);

}
