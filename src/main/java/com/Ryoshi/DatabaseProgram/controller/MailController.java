package com.Ryoshi.DatabaseProgram.controller;

import com.Ryoshi.DatabaseProgram.model.Mail;
import com.Ryoshi.DatabaseProgram.repository.MailRepository;
import com.Ryoshi.DatabaseProgram.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/mail")
public class MailController {

    private final UserRepository userRepository;
    private final MailRepository mailRepository;
    private final PasswordEncoder passwordEncoder;

    public MailController(UserRepository userRepository,
                          MailRepository mailRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.mailRepository = mailRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String createMail(Model model){
        model.addAttribute("mail", new Mail());
        model.addAttribute("user", userRepository.findAll());
        return "mail";
    }

    @PostMapping
    public String sendMail(Mail mail, Principal principal){
        mail.setAuthor(userRepository.findByUsername(principal.getName())
                .orElseThrow(()->new UsernameNotFoundException("User Not Found")));
        mail.setTitle(passwordEncoder.encode(mail.getTitle()));
        mail.setContent(passwordEncoder.encode(mail.getContent()));
        mailRepository.save(mail);
        return "redirect:/index";
    }

}
