package advogados.associados.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EntityScan(basePackages = {"advogados.associados.backend.model"})
@ComponentScan(basePackages = {"advogados.*"})
@EnableJpaRepositories(basePackages = {"advogados.associados.backend.repository"})
@EnableTransactionManagement
@EnableAutoConfiguration
public class AdvogadosAssociadosBackendApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(AdvogadosAssociadosBackendApplication.class, args);
	}
	
	/* Utilizando CORS para fazer o mapeamento de rotas global da aplicação */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		
		/* Utilizando o CORS para liberar apenas requisições POST, PUT e GET para o cliente localhost:8080 */
		registry.addMapping("/audiencia/**").
		allowedMethods("*"). 
		allowedOrigins("*");
	}
}
