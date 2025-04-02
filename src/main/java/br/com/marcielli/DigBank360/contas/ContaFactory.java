package br.com.marcielli.DigBank360.contas;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.marcielli.DigBank360.clientes.Cliente;
import br.com.marcielli.DigBank360.clientes.ClienteFactory;
import br.com.marcielli.DigBank360.contas.corrente.Corrente;
import br.com.marcielli.DigBank360.contas.poupanca.Poupanca;
import br.com.marcielli.DigBank360.helpers.CategoriaDaConta;
import br.com.marcielli.DigBank360.helpers.TipoDeCartao;
import br.com.marcielli.DigBank360.helpers.TipoDeConta;
import br.com.marcielli.DigBank360.helpers.TipoDeTransferencia;
import jakarta.transaction.Transactional;

public class ContaFactory {

	public static TipoDeConta criarConta(Conta novaConta) {
		
//		Double saldoDaConta = novaConta.getSaldoDaConta();
//		CategoriaDaConta categoriaDaConta = null;
//		String numeroDaConta = gerarNumeroDaConta(novaConta);	
		
		
		if (novaConta.getClass().getSimpleName().equalsIgnoreCase(("CORRENTE"))) {		
			
			return TipoDeConta.CORRENTE;
			
//			if(saldoDaConta <= 1.000d) {
//				categoriaDaConta = CategoriaDaConta.COMUM;
//			}
//			
//			if(saldoDaConta > 1.000d && saldoDaConta <= 5.000d) {
//				categoriaDaConta = CategoriaDaConta.SUPER;			
//			}
//			
//			if(saldoDaConta > 5.000d) {
//				categoriaDaConta = CategoriaDaConta.PREMIUM;				
//			}			
//			
//			String numContaCorrente = numeroDaConta.concat("-CC");			
//			
//		
//			return new Corrente(novaConta.getCliente(), TipoDeConta.CORRENTE,
//					saldoDaConta, numContaCorrente, categoriaDaConta);
			
		} else if (novaConta.getClass().getSimpleName().equalsIgnoreCase("POUPANCA")) {
			return TipoDeConta.POUPANCA;
//			//String numeroDaConta = gerarNumeroDaConta(novaConta);
//			Double acrescimoTaxaRendimento = 0.0d;
//			Double taxaMensal = 0.0d;
//			
//			if(saldoDaConta <= 1000d) {
//				categoriaDaConta = CategoriaDaConta.COMUM;
//				acrescimoTaxaRendimento = 0.005d;
//				taxaMensal = Math.pow(1+acrescimoTaxaRendimento, 1.0/12) - 1;
//			}
//			
//			if(saldoDaConta > 1000d && saldoDaConta <= 5000d) {
//				categoriaDaConta = CategoriaDaConta.SUPER;		
//				acrescimoTaxaRendimento = 0.007d;
//				taxaMensal = Math.pow(1+acrescimoTaxaRendimento, 1.0/12) - 1;
//			}
//			
//			if(saldoDaConta > 5000d) {
//				categoriaDaConta = CategoriaDaConta.PREMIUM;	
//				acrescimoTaxaRendimento = 0.009d;
//				taxaMensal = Math.pow(1+acrescimoTaxaRendimento, 1.0/12) - 1;
//			}			
//			
//			String numContaPoupanca = numeroDaConta.concat("-PP");
			
//
//			return new Poupanca(novaConta.getId(), novaConta.getCliente(),  TipoDeConta.POUPANCA,
//					novaConta.getSaldoDaConta(), novaConta.getNumeroDaConta());
		
		} else {
			System.err.println("A conta não tem um tipo específico e por isso não foi criada");
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
