package br.com.marcielli.DigBank360.contas;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ContaService {
	
	@Autowired
	private ContaRepository contaRepository;
	
	//CREATE
	public Conta save(Conta conta) {
	
		try {
			//Fazer métodos de validação
			if(!gerarNumeroDaConta(conta)) {
				
			}
			
			String tipo = conta.getTipoDeConta().toString();
			Conta novaConta = ContaFactory.criarConta(tipo);
			System.err.println("Tipo de Conta: "+tipo);
			return contaRepository.save(novaConta);
			
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
//	public boolean validarTipoDeConta(TipoDeConta tipoEscolhido) {
//		
//		if(tipoEscolhido)
//		
//	}
	
	public boolean gerarNumeroDaConta(Conta conta) {
		
		String tipoDeConta = conta.getTipoDeConta().toString();
		
		int[] sequencia = new int[8];
		Random random = new Random();
		String minhaConta = "";		
		
		for(int i=0; i<sequencia.length; i++) {			
			sequencia[i] = 1 + random.nextInt(8);
		}
		
		for(int i=0; i<sequencia.length; i++) {			
			minhaConta += Integer.toString(sequencia[i]);
		}		
		
		if(tipoDeConta.equalsIgnoreCase("CORRENTE")) {
			minhaConta.concat("-CC");
		} else if(tipoDeConta.equalsIgnoreCase("POUPANCA")) {
			minhaConta.concat("-PP");
		} else {
			 throw new IllegalArgumentException("Tipo de conta desconhecido");
		}
		
		conta.setNumeroDaConta(minhaConta);
	
		return true;
	}
	

}
