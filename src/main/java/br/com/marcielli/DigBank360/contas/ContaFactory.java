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

	public static TipoDeConta getTipoEscolhidoPeloCliente(Conta novaConta) {

		if (novaConta.getClass().getSimpleName().equalsIgnoreCase(("CORRENTE"))) {		
			
			return TipoDeConta.CORRENTE;

			
		} else if (novaConta.getClass().getSimpleName().equalsIgnoreCase("POUPANCA")) {
			return TipoDeConta.POUPANCA;
	
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
