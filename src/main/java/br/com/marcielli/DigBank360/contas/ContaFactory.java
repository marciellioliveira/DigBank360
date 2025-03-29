package br.com.marcielli.DigBank360.contas;

import java.util.Random;

import br.com.marcielli.DigBank360.contas.corrente.Corrente;
import br.com.marcielli.DigBank360.contas.poupanca.Poupanca;

public class ContaFactory {

	/*Esse método será estático, ele vai decidir se a conta a ser criada é Corrente ou Poupança
	Utilizei esse padrão de design porque a Classe Conta é abstract e não pode ser instanciada da maneira que eu precisava
	porque eu queria usar o Controller da Conta para decidir e não o Controller de Corrente ou Poupança */
	
	public static Conta criarConta(String tipo) {
	
		if(tipo.equalsIgnoreCase(("CONTA_CORRENTE"))) {
			
			return new Corrente();
		} else if(tipo.equalsIgnoreCase("CONTA_POUPANCA")) {
			return new Poupanca();
		}
		 throw new IllegalArgumentException("Tipo de conta desconhecido");
	}
	
	
}
