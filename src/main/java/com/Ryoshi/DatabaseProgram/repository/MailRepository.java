package com.Ryoshi.DatabaseProgram.repository;

import com.Ryoshi.DatabaseProgram.model.Mail;
import com.Ryoshi.DatabaseProgram.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MailRepository extends CrudRepository<Mail, Long> {

    List<Mail> findAllByRecipient(User recipient);

}
