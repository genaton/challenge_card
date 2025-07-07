package br.com.pos_tech_dev_foudation.challenge_card;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengeCardApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(ChallengeCardApplication.class, args);

		
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Bem vindo ao Challange API");
		System.out.println("Versão do Java: " + System.getProperty("java.version"));
		
		
		
	}
	

}
