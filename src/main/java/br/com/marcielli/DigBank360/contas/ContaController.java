package br.com.marcielli.DigBank360.contas;

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

import br.com.marcielli.DigBank360.clientes.Cliente;
import br.com.marcielli.DigBank360.clientes.ClienteService;

@RestController
@RequestMapping("/conta")
public class ContaController {

	@Autowired
	private ContaService contaService;
//	
//	@Autowired
//	private ClienteService clienteService;

	// GET Rota Inicial
	@GetMapping("/")
	public String inicio() {
		return "Rest API de Banco Digital com Spring Boot JPA, H2 database, lombok e Padrão de Projeto de Separação em Camadas";
	}
	
	//POST
	@PostMapping("/save")	//Ta salvando o cliente mas a conta não ta passando pro service 
	public ResponseEntity<String> create(@RequestBody Conta conta) {

		
		 System.out.println("Tipo de Conta: " + conta.getTipoDeConta().getDescricao());
		
			
//			for(Cliente clienteExiste : clienteService.getAll()) {
//				if(clienteExiste.getId().equals(conta.getCliente().getId())) {
//					System.err.println("Cliente ID: "+conta.getCliente().getId());
//				}
//			}
		
		
			Conta added = contaService.save(conta);
			
			if (added != null) {
			
				return new ResponseEntity<>("Conta adicionada com sucesso!", HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>("Conta não foi adicionada!\nDigite os dados corretamente.",
						HttpStatus.NOT_ACCEPTABLE);
			}
	
	}


	// GET CONTAS BY ID
	@GetMapping("/findById/{id}")
	public ResponseEntity<?> findById(@PathVariable long id) {
		return contaService.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	// ATUALIZA
	@PutMapping("/update/{id}")
	public ResponseEntity<Conta> update(@PathVariable Long id, @RequestBody Conta conta) {

		return contaService.findById(id).map(record -> {
			record.setNumeroDaConta(conta.getNumeroDaConta());
			record.setSaldoDaConta(conta.getSaldoDaConta());
			record.setTipoDeCartao(conta.getTipoDeCartao());
			record.setTipoDeConta(conta.getTipoDeConta());
			record.setTipoDeTransferencia(conta.getTipoDeTransferencia());
			record.setCategoriaDaConta(conta.getCategoriaDaConta());
			record.setCliente(conta.getCliente());

			Conta updated = contaService.update(conta);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	// DELETAR
	@DeleteMapping("/deletebyId/{id}")
	public void delete(@PathVariable Long id) {
		contaService.findById(id).map(record -> {
			contaService.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}

	// GET ALL
	@GetMapping("/all")
	public List<Conta> getAllClients() {
		return contaService.getAll();
	}

}
