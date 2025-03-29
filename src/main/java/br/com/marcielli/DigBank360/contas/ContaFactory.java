package br.com.marcielli.DigBank360.contas;

import java.util.Random;

import br.com.marcielli.DigBank360.contas.corrente.Corrente;
import br.com.marcielli.DigBank360.contas.poupanca.Poupanca;
import br.com.marcielli.DigBank360.helpers.CategoriaDaConta;
import br.com.marcielli.DigBank360.helpers.TipoDeConta;

public class ContaFactory {

	/*
	 * Esse método será estático, ele vai decidir se a conta a ser criada é Corrente
	 * ou Poupança Utilizei esse padrão de design porque a Classe Conta é abstract e
	 * não pode ser instanciada da maneira que eu precisava porque eu queria usar o
	 * Controller da Conta para decidir e não o Controller de Corrente ou Poupança
	 */
	
	public static Conta criarConta(Conta conta) {	
		
		Double saldoDaConta = conta.getSaldoDaConta();
		CategoriaDaConta categoriaDaConta = null;
		
		if (conta.getClass().getSimpleName().equalsIgnoreCase(("CORRENTE"))) {
			String numeroDaConta = gerarNumeroDaConta(conta).concat("-CC");			
			
			if(saldoDaConta <= 1000d) {
				categoriaDaConta = CategoriaDaConta.COMUM;
			}
			
			if(saldoDaConta > 1000d && saldoDaConta <= 5000d) {
				categoriaDaConta = CategoriaDaConta.SUPER;			}
			
			if(saldoDaConta > 5000d) {
				categoriaDaConta = CategoriaDaConta.PREMIUM;				
			}			
			
			return new Corrente(TipoDeConta.CORRENTE, numeroDaConta, conta.getCliente(), categoriaDaConta, saldoDaConta);
			
		} else if (conta.getClass().getSimpleName().equalsIgnoreCase("POUPANCA")) {
			
			String numeroDaConta = gerarNumeroDaConta(conta).concat("-PP");
			
			return new Poupanca(TipoDeConta.POUPANCA, numeroDaConta, conta.getCliente());
		} else {
			return null;
		}
		
	}

	public static String gerarNumeroDaConta(Conta conta) {
		
		int[] sequencia = new int[8];
		Random random = new Random();
		String minhaConta = "";		
		
		for(int i=0; i<sequencia.length; i++) {			
			sequencia[i] = 1 + random.nextInt(8);
		}
		
		for(int i=0; i<sequencia.length; i++) {			
			minhaConta += Integer.toString(sequencia[i]);
		}
	
		return minhaConta;
	}
}
