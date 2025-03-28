package br.com.marcielli.DigBank360.contas.corrente;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.marcielli.DigBank360.clientes.Cliente;
import br.com.marcielli.DigBank360.contas.Conta;
import br.com.marcielli.DigBank360.contas.poupanca.Poupanca;

@Service
public class CorrenteService {
	
	@Autowired
	private CorrenteRepository correnteRepository;
	
	//CREATE
	public Corrente save(Corrente corrente) {
	
		try {
			//Fazer métodos de validação
			return correnteRepository.save(corrente);
			
		} catch (Exception e) {
			return null;
		}		
	}
	
	//FIND BY ID
	public Optional<Corrente> findById(Long id) {		
		return correnteRepository.findById(id);
	}
	
	//UPDATE
	public Corrente update(Corrente corrente) {		
		return correnteRepository.save(corrente);
	}
	
	//DELETE BY ID
	public void deleteById(Long id) {
		correnteRepository.deleteById(id);		
	}
	
	//LIST ALL
	public List<Corrente> getAll(){
		return correnteRepository.findAll();
	}
	
	//Validação de campos
	
	
	
	
	
	
	

}
