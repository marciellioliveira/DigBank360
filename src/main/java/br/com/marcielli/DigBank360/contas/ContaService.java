package br.com.marcielli.DigBank360.contas;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.marcielli.DigBank360.clientes.Cliente;
import br.com.marcielli.DigBank360.contas.corrente.Corrente;

@Service
public class ContaService {
	
	@Autowired
	private ContaRepository contaRepository;
	
	//CREATE
	public Conta save(Conta conta) {
	
		try {
			//Fazer métodos de validação
			return contaRepository.save(conta);
			
		} catch (Exception e) {
			return null;
		}		
	}
	
	//FIND BY ID
	public Optional<Conta> findById(Long id) {		
		return contaRepository.findById(id);
	}
	
	//UPDATE
	public Conta update(Conta conta) {		
		return contaRepository.save(conta);
	}
	
	//DELETE BY ID
	public void deleteById(Long id) {
		contaRepository.deleteById(id);		
	}
	
	//LIST ALL
	public List<Conta> getAll(){
		return contaRepository.findAll();
	}
	
	//Validação de campos


}
