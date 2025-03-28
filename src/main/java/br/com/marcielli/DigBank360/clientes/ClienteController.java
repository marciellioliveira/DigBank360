package br.com.marcielli.DigBank360.clientes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class ClienteController { //Controlador entre o BD (Repository) e o Usuário

	@Autowired
	private ClienteService clienteService;
	
	//GET Rota Inicial
	@GetMapping("/") //http://localhost:8085/api/
	public String inicio() {
		return "Rest API de Banco Digital com Spring Boot JPA, H2 database, lombok e Padrão de Projeto de Separação em Camadas";
	}	
	
	//GET - Listar
	@GetMapping
	public List<Cliente> getAllClients(){
		return clienteService.getAll();
	}
	
	//POST
	@PostMapping
	public Cliente create(@RequestBody Cliente cliente) {
		return clienteService.save(cliente);
	}
	
	//DELETE
	
	
	
	
	
//private String nome;
	
//	private Long cpf;
//	
//	private LocalDate dataNascimento;
//	
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "tb_endereco", referencedColumnName = "id")
//	private Endereco endereco;	
	
	
	
	
	
}
