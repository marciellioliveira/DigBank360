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

	public static Conta criarConta(Conta conta) {
		
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
			Corrente contaCorrente = new Corrente(conta.getId(), conta.getCliente(), TipoDeConta.CORRENTE,
					conta.getSaldoDaConta(), conta.getNumeroDaConta());
		
			
//			System.err.println("ID conta: "+contaCorrente.getId());
//			System.err.println("id cliente: "+contaCorrente.getCliente().getId());
//			System.err.println("cliete da conta: "+contaCorrente.getCliente().getNome());
//			System.err.println("cliete da conta: "+contaCorrente.getCliente().getCpf());
//			System.err.println("Numero da conta: "+contaCorrente.getNumeroDaConta());
//			System.err.println("tipo da conta: "+contaCorrente.getTipoDeConta());
//			System.err.println("saldo da conta: "+contaCorrente.getSaldoDaConta());
	
			return contaCorrente;
			
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
			Poupanca contaPoupanca = new Poupanca(conta.getId(), conta.getCliente(),  TipoDeConta.POUPANCA,
					conta.getSaldoDaConta(), conta.getNumeroDaConta());
			return contaPoupanca;
		//	return new Poupanca(TipoDeConta.POUPANCA, numeroDaConta, conta.getCliente(), categoriaDaConta, saldoDaConta, acrescimoTaxaRendimento, taxaMensal);
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
