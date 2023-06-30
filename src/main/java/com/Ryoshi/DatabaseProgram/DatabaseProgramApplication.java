package com.Ryoshi.DatabaseProgram;

import com.Ryoshi.DatabaseProgram.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class DatabaseProgramApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatabaseProgramApplication.class, args);
	}

}
