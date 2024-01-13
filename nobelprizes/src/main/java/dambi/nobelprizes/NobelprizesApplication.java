package dambi.nobelprizes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Klase hau aplikazioaren main-a eraikizten du. Main honetan SpringBoot-ek bere
 * run funtzioa exekutatzen du eta aplikazioa martxan jartzen da. Horretarako, lehenik 
 * SpringBootApplication anotazioa erabiltzen da eta bestetik SprinApplication instantziaren
 * run funtzioa exekutatzen da.
 * 
 * @author Julen Herrero
 */
@SpringBootApplication
public class NobelprizesApplication {

	public static void main(String[] args) {
		SpringApplication.run(NobelprizesApplication.class, args);
	}

}
