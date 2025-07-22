package api.gestao.beneficiario.APIDeGestaoDeBeneficiariosDePlanoDeSaude;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ApiDeGestaoDeBeneficiariosDePlanoDeSaudeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiDeGestaoDeBeneficiariosDePlanoDeSaudeApplication.class, args);
	}

}
