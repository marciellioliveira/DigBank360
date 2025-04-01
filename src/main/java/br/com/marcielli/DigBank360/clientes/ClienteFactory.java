package br.com.marcielli.DigBank360.clientes;

import java.time.LocalDate;
import java.util.List;

import br.com.marcielli.DigBank360.contas.Conta;

public class ClienteFactory {
	
	public static Cliente criarCliente(Cliente cliente) {		
		
		return new Cliente(cliente.getId(), cliente.getNome(), cliente.getCpf(), cliente.getDataNascimento(), cliente.getEndereco(), cliente.getContas());

	}

}
