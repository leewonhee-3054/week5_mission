package umc.mydiagram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MydiagramApplication {

	public static void main(String[] args) {
		SpringApplication.run(MydiagramApplication.class, args);
	}

}
