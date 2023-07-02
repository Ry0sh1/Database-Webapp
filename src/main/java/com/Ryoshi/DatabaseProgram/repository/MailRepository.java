package com.Ryoshi.DatabaseProgram.repository;

import com.Ryoshi.DatabaseProgram.model.Mail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailRepository extends CrudRepository<Mail, Long> {
}
