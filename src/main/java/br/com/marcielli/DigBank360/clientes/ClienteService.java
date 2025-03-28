package br.com.marcielli.DigBank360.clientes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	//CREATE
	public Cliente save(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	//FIND BY ID
	public Optional<Cliente> findById(Long id) {
		return clienteRepository.findById(id);
	}
	
	//UPDATE
	public Cliente update(Cliente cliente) {		
		return clienteRepository.save(cliente);
	}
	
	//DELETE BY ID
	public void deleteById(Long id) {
		clienteRepository.deleteById(id);
		
	}
	
	//LIST ALL
	public List<Cliente> getAll(){
		return clienteRepository.findAll();
	}

}
