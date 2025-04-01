package br.com.marcielli.DigBank360.clientes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import exception.clientes.UnsuportedClientDuplicatedExistException;
import jakarta.transaction.Transactional;


@RestController
@RequestMapping("/cliente")
public class ClienteController { //Controlador entre o BD (Repository) e o Usuário

	@Autowired
	private ClienteService clienteService;
	
	//GET Rota Inicial
	@GetMapping("/") //http://localhost:8085/api/
	public String inicio() {
		return "Rest API de Banco Digital com Spring Boot JPA, H2 database, lombok e Padrão de Projeto de Separação em Camadas";
	}	
	
	//POST
	@PostMapping("/save")	
	public ResponseEntity<String> create(@RequestBody Cliente cliente) {	
		
		
			System.err.println("Nome: "+cliente.getNome());
			System.err.println("CPF: "+cliente.getCpf());
			Cliente added = clienteService.save(cliente);
			
			if(added != null) {
				return new ResponseEntity<>("Cliente "+cliente.getNome()+" adicionado com sucesso!", HttpStatus.CREATED);	
				
				
				
			} else {
				return new ResponseEntity<>("Cliente "+cliente.getNome()+" não foi adicionado!\nDigite os dados corretamente.", HttpStatus.NOT_ACCEPTABLE);	
			
				
			}
	
		
		
	}
	
	//GET CLIENTS BY ID
	@GetMapping("/findById/{id}")
	public ResponseEntity findById(@PathVariable long id){
		
		return clienteService.findById(id)
				.map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	//ATUALIZA
	@PutMapping("/update/{id}")
	public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody Cliente cliente){
		
		return clienteService.findById(id)
				.map(record -> {
					record.setNome(cliente.getNome());
					record.setCpf(cliente.getCpf());
					record.setDataNascimento(cliente.getDataNascimento());
					record.setEndereco(cliente.getEndereco());
					Cliente updated = clienteService.update(cliente);
					return ResponseEntity.ok().body(updated);
				}).orElse(ResponseEntity.notFound().build());
	}
	
	//DELETAR
	@DeleteMapping("/deletebyId/{id}")
	public void delete(@PathVariable Long id){		 
		clienteService.findById(id)
		.map(record -> {
			clienteService.deleteById(id);
			 return ResponseEntity.ok().build();
           }).orElse(ResponseEntity.notFound().build());		
	}
	
	//GET ALL
	@GetMapping("/all")
	public List<Cliente> getAllClients(){
		return clienteService.getAll();
	}	
}
