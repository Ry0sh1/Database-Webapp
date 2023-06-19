package com.Ryoshi.DatabaseProgram.repository;

import com.Ryoshi.DatabaseProgram.model.Dogs;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface DogRepository extends CrudRepository<Dogs, Long> {

    @Transactional
    void deleteAllByOwnerId(long owner_id);

    @Query("SELECT breed FROM Dogs")
    List<String> getBreed();

}
