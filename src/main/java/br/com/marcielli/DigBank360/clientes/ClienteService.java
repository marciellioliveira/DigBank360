package br.com.marcielli.DigBank360.clientes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	
	//LISTAR
	public List<Cliente> getAll(){
		return clienteRepository.findAll();
	}
	
	//CRIAR
	public Cliente save(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	//DELETAR
//	public void delete(Long id) {
//		return clienteRepository.deleteById(id);
//	}
//	
	
	
	
	
	
	

}
