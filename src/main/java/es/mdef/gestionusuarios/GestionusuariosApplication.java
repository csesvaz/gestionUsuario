package es.mdef.gestionusuarios;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestionusuariosApplication {
//	public static final Logger log = LoggerFactory.getLogger(GestionusuariosApplication.class);
	public static void main(String[] args) {

//		System.err.println(new BCryptPasswordEncoder().encode("password"));
		SpringApplication.run(GestionusuariosApplication.class, args);
	}

}
