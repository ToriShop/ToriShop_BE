package com.torishop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class TorishopApplication {
	public static void main(String[] args) {
		SpringApplication.run(TorishopApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("http://localhost:3000") // 허용할 원본
						.allowedMethods("GET", "POST", "PUT", "DELETE")
						.allowedHeaders("Content-Type", "Authorization", "AUTH-TOKEN")
						.allowCredentials(true) // 인증 쿠키를 사용해야 하는 경우 true로 설정
						.exposedHeaders("Access-Control-Allow-Origin"); // 브라우저가 접근해야 할 응답 헤더
			}
		};
	}

}
