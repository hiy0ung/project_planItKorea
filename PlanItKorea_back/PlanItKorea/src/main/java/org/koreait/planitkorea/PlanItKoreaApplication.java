package org.koreait.planitkorea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PlanItKoreaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlanItKoreaApplication.class, args);
	}

}
