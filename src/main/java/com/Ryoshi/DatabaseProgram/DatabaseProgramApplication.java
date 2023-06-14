package com.Ryoshi.DatabaseProgram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@Controller
public class DatabaseProgramApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatabaseProgramApplication.class, args);
	}

	@GetMapping("/notIndex")
	public String redirectToNotIndex(){
		return "redirect:notIndex.html";
	}

}
