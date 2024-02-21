package br.com.ffrantz.PrimeiroProjetoSpring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.ffrantz.entity.Cliente;
import br.com.ffrantz.repository.IClienteRepository;

@SpringBootApplication
@Configuration
@EnableJpaRepositories(basePackages = "br.com.ffrantz.repository")
@EntityScan("br.com.ffrantz.*")
@ComponentScan(basePackages = "br.com.ffrantz")
public class PrimeiroProjetoSpringApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(PrimeiroProjetoSpringApplication.class);

	@Autowired
	IClienteRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(PrimeiroProjetoSpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("StartApplication...");
		Cliente cliente = createCliente();
		repository.save(cliente);
	}

	private Cliente createCliente() {
		return Cliente.builder().cidade("SP").cpf(10101010101L).email("Teste@teste.com").end("Endereço").estado("SP")
				.nome("Felipe").idade(37).tel(11999999999L).build();
	}
}
