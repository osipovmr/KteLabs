package osipovmr.KteLabs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KteLabsApplication {

	public static void main(String[] args) {
		SpringApplication.run(KteLabsApplication.class, args);
	}

}
