package com.dong.music;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.WebApplicationInitializer;

@SpringBootApplication
@EnableCaching
@MapperScan("com.dong.music.mapper")
public class MusicApplication extends SpringBootServletInitializer implements WebApplicationInitializer {
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MusicApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(MusicApplication.class, args);
	}
}
