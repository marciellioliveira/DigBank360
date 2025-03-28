package br.com.marcielli.DigBank360.clientes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;

}
