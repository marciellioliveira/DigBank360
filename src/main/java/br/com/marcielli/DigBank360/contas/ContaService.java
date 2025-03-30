package br.com.marcielli.DigBank360.contas;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.marcielli.DigBank360.clientes.ClienteRepository;
import br.com.marcielli.DigBank360.clientes.ClienteService;
import exception.clientes.UnsuportedClientDataWrongException;
import exception.clientes.UnsuportedClientDontExistException;
import exception.contas.UnsuportedContaNotValidException;
import exception.contas.UnsuportedContaSaldoNullException;


@Service
public class ContaService {
	
	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	
	//CREATE
	public Conta save(Conta conta) {
	
		try {
		
			if(conta.getClass().getSimpleName().equals(null)) {
				throw new UnsuportedContaNotValidException("Tipo de conta desconhecido.");
			}			
						
			//Se o cliente existe, ele já cria a conta.
			Conta newConta = ContaFactory.criarConta(conta);	
			

			return contaRepository.save(newConta);
						
		} catch (UnsuportedContaNotValidException e) {
			
			System.err.println("Erro: "+e.getMessage());
			return null;
			
		} catch (UnsuportedClientDontExistException e) {
			
			System.err.println("Erro: "+e.getMessage());
			return null;
			
		} catch (UnsuportedClientDataWrongException e) {
			
			System.err.println("Erro: "+e.getMessage());
			return null;
			
		} catch(UnsuportedContaSaldoNullException e) {
			
			System.err.println("Erro: "+e.getMessage());
			return null;
			
		} catch (Exception e) {
			
			System.err.println("Erro: "+e.getMessage());
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
	
	public List<Conta> getAllById(Long id){
		return contaRepository.findAllById(id);
	}
	
	//Validação de campos
//	public boolean validarTipoDeConta(TipoDeConta tipoEscolhido) {
//		
//		if(tipoEscolhido)
//		
//	}
	
	
	
	

}
