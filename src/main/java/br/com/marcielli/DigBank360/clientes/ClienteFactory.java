package br.com.marcielli.DigBank360.clientes;

public class ClienteFactory {
	
	public static Cliente criarCliente(Cliente cliente) {		
		
		return new Cliente(cliente.getId(), cliente.getNome(), cliente.getCpf(), cliente.getDataNascimento(), cliente.getEndereco(), cliente.getContas());
		
	}

}
