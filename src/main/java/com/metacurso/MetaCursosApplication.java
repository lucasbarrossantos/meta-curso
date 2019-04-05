package com.metacurso;

import com.metacurso.config.property.MetaCursoApiProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@EnableConfigurationProperties(MetaCursoApiProperty.class)
public class MetaCursosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetaCursosApplication.class, args);
	}

	@PostConstruct
	void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

}
