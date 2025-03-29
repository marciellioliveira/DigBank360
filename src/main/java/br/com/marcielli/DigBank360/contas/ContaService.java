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
			
			System.err.println("ID Cliente: "+conta.getCliente().getId());
			
			Conta newConta = ContaFactory.criarConta(conta);	
			
			//Se Cliente não existe > Precisa existir antes de criar a conta
			if(newConta.getCliente() == null) {
				throw new UnsuportedClientDontExistException("Você precisa ter um cliente cadastrado para abrir uma conta.");
			}
			
			//Se Cliente não tem dados válidos
			if(clienteService.save(conta.getCliente()) == null) {
				throw new UnsuportedClientDataWrongException("Dados do cliente inválido. Confira os dados e tente novamente.");
			}
			
			
			if(conta.getSaldoDaConta() == null) {
				throw new UnsuportedClientDataWrongException("Saldo da conta é nulo");
			}
			
			
			//conta.getCliente().setContas(contasDoCliente);
			//return update(newConta);
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
	
	//Validação de campos
//	public boolean validarTipoDeConta(TipoDeConta tipoEscolhido) {
//		
//		if(tipoEscolhido)
//		
//	}
	
	
	
	

}
