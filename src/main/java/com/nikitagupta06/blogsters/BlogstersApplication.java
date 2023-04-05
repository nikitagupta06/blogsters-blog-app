package com.nikitagupta06.blogsters;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BlogstersApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogstersApplication.class, args);
	}

}
