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
import java.util.List;

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

    @GetMapping("/send")
    public String createMail(Model model){
        model.addAttribute("mail", new Mail());
        model.addAttribute("user", userRepository.findAll());
        return "mail";
    }

    @PostMapping("/send")
    public String sendMail(Mail mail, Principal principal){
        mail.setAuthor(userRepository.findByUsername(principal.getName())
                .orElseThrow(()->new UsernameNotFoundException("User Not Found")));
        mailRepository.save(mail);
        return "redirect:/index";
    }

    @GetMapping("/inbox")
    public String showInbox(Model model, Principal principal){
        List<Mail> mailList = mailRepository.findAllByRecipient(userRepository.findByUsername(principal.getName())
                .orElseThrow(()->new UsernameNotFoundException("User Not Found")));
        model.addAttribute("mailList", mailList);
        return "mail-inbox";
    }

}
