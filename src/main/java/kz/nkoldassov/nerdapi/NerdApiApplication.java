package kz.nkoldassov.nerdapi;

import kz.nkoldassov.nerdapi.beans.all.ShowConfigValuesOnStartup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class NerdApiApplication {

	@Autowired
	private ShowConfigValuesOnStartup showConfigValuesOnStartup;

	@PostConstruct
	public void init() {
		showConfigValuesOnStartup.show();
	}

	public static void main(String[] args) {
		SpringApplication.run(NerdApiApplication.class, args);
	}

}
