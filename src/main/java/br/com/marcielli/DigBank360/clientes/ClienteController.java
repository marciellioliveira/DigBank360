package br.com.marcielli.DigBank360.clientes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ClienteController { //Controlador entre o BD (Repository) e o Usuário

	@Autowired
	private ClienteService clienteService;
	
	@GetMapping("/") //http://localhost:8085/api/
	public String inicio() {
		return "Rest API de Banco Digital com Spring Boot JPA, H2 database, lombok e Padrão de Projeto de Separação em Camadas";
	}
	
}
