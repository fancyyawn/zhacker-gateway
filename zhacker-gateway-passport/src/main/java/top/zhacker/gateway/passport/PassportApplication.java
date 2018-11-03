package top.zhacker.gateway.passport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PassportApplication {

	public static void main(String[] args) {
		SpringApplication.run(PassportApplication.class, args);
	}
}
