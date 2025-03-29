package br.com.marcielli.DigBank360.contas;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.marcielli.DigBank360.clientes.Cliente;
import br.com.marcielli.DigBank360.clientes.ClienteService;
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
	
	@Autowired
	private ClienteService clienteService;
	
	public static Conta criarConta(Conta conta) {
		
		Cliente clienteExiste = conta.getCliente();
		System.err.println("Cliente: "+clienteExiste);
		
		//Tenho que chamar a List<Conta> do cliente aqui, toda vez quee eu adicionar uma conta preciso ver quem é o cliente e adicionar na lista tb
		
		Double saldoDaConta = conta.getSaldoDaConta();
		CategoriaDaConta categoriaDaConta = null;
		
		if (conta.getClass().getSimpleName().equalsIgnoreCase(("CORRENTE"))) {
			String numeroDaConta = gerarNumeroDaConta(conta);			
			
			if(saldoDaConta <= 1000d) {
				categoriaDaConta = CategoriaDaConta.COMUM;
			}
			
			if(saldoDaConta > 1000d && saldoDaConta <= 5000d) {
				categoriaDaConta = CategoriaDaConta.SUPER;			}
			
			if(saldoDaConta > 5000d) {
				categoriaDaConta = CategoriaDaConta.PREMIUM;				
			}			
			
			String numContaCorrente = numeroDaConta.concat("-CC");	
			
			
			//Ver se o id e cpf do cliente q eu to recebendo no parametro é igual o q ja existe existe, se sim, add conta pora ele
			//Tb posso ignorar o endereço do cliente no json pq ele ja vai ta add la 
			//for(Cliente clienteExiste : ClienteService.class.geta)


			return new Corrente(TipoDeConta.CORRENTE, numeroDaConta, conta.getCliente(), categoriaDaConta, saldoDaConta);
			
		} else if (conta.getClass().getSimpleName().equalsIgnoreCase("POUPANCA")) {
			
			String numeroDaConta = gerarNumeroDaConta(conta);
			Double acrescimoTaxaRendimento = 0.0d;
			Double taxaMensal = 0.0d;
			
			if(saldoDaConta <= 1000d) {
				categoriaDaConta = CategoriaDaConta.COMUM;
				acrescimoTaxaRendimento = 0.005d;
				taxaMensal = Math.pow(1+acrescimoTaxaRendimento, 1.0/12) - 1;
			}
			
			if(saldoDaConta > 1000d && saldoDaConta <= 5000d) {
				categoriaDaConta = CategoriaDaConta.SUPER;		
				acrescimoTaxaRendimento = 0.007d;
				taxaMensal = Math.pow(1+acrescimoTaxaRendimento, 1.0/12) - 1;
			}
			
			if(saldoDaConta > 5000d) {
				categoriaDaConta = CategoriaDaConta.PREMIUM;	
				acrescimoTaxaRendimento = 0.009d;
				taxaMensal = Math.pow(1+acrescimoTaxaRendimento, 1.0/12) - 1;
			}			
			
			String numContaPoupanca = numeroDaConta.concat("-PP");
			return new Poupanca(TipoDeConta.POUPANCA, numeroDaConta, conta.getCliente(), categoriaDaConta, saldoDaConta, acrescimoTaxaRendimento, taxaMensal);
		} else {
			return null;
		}
		
	}
	
//	public static boolean clienteExiste(Cliente cliente) {
//		for(Cliente clienteEx : clienteService.getAll()) {
//			if(clienteEx.getCpf().equals(cliente.getCpf())) {
//				return true;
//			}
//		}
//		
//		return false;
//	}
	
	

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
