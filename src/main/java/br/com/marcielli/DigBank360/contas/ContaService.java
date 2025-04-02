package br.com.marcielli.DigBank360.contas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.marcielli.DigBank360.clientes.Cliente;
import br.com.marcielli.DigBank360.clientes.ClienteRepository;
import br.com.marcielli.DigBank360.clientes.ClienteService;
import br.com.marcielli.DigBank360.contas.corrente.Corrente;
import br.com.marcielli.DigBank360.contas.corrente.CorrenteRepository;
import br.com.marcielli.DigBank360.contas.poupanca.Poupanca;
import br.com.marcielli.DigBank360.contas.poupanca.PoupancaRepository;
import br.com.marcielli.DigBank360.exception.clientes.UnsuportedClientDataWrongException;
import br.com.marcielli.DigBank360.exception.clientes.UnsuportedClientDontExistException;
import br.com.marcielli.DigBank360.exception.contas.UnsuportedContaNotValidException;
import br.com.marcielli.DigBank360.exception.contas.UnsuportedContaSaldoNullException;
import br.com.marcielli.DigBank360.helpers.CategoriaDaConta;
import br.com.marcielli.DigBank360.helpers.TipoDeConta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class ContaService {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private ClienteService clienteService;

	private final List<ContaRepository> contaRepositories;

	@Autowired
	public ContaService(List<ContaRepository> contaRepositories) {
		this.contaRepositories = contaRepositories;
	}

	@Transactional
	public Conta save(Conta conta) {

		try {

			entityManager.clear();

//			Cliente cliente = conta.getCliente();
//			// Cliente já existe no banco, usando o merge para atualizar
//			if (cliente != null && cliente.getCpf() != null) {
//				Cliente clienteExistente = clienteService.findClienteByCpf(cliente.getCpf());
//				if (clienteExistente != null) {
//					cliente = entityManager.merge(clienteExistente);
//					conta.setCliente(cliente);
//				}
//
//			} else {
//				// Cliente é novo, vai ser persistido como uma nova entidade
//				entityManager.persist(cliente); // Persistir o cliente novo
//				conta.setCliente(cliente); // Associar cliente à nova conta
//			}

			TipoDeConta tipoDeConta = ContaFactory.getTipoEscolhidoPeloCliente(conta);

			Double saldoDaConta = conta.getSaldoDaConta();
			CategoriaDaConta categoriaDaConta = null;
			String numeroDaConta = gerarNumeroDaConta(conta);

			for (ContaRepository repositoryEscolhido : contaRepositories) {

				if (repositoryEscolhido instanceof CorrenteRepository && tipoDeConta == TipoDeConta.CORRENTE) {

					if (saldoDaConta <= 1.000d) {
						categoriaDaConta = CategoriaDaConta.COMUM;
					}

					if (saldoDaConta > 1.000d && saldoDaConta <= 5.000d) {
						categoriaDaConta = CategoriaDaConta.SUPER;
					}

					if (saldoDaConta > 5.000d) {
						categoriaDaConta = CategoriaDaConta.PREMIUM;
					}

					String numContaCorrente = numeroDaConta.concat("-CC");

					Conta contaCorrente = new Corrente(conta.getCliente(), TipoDeConta.CORRENTE, saldoDaConta,
							numContaCorrente, categoriaDaConta);
					
					//Peghr todas as contas do banco
					//Se conta existe, merge igual cliente fez 
					//se conta não existe, cria uma com persist
					
					System.err.println("ID: "+contaCorrente.getId());
					System.err.println("Numero conta: "+contaCorrente.getNumeroDaConta());
					
					
					//Se cliente existe, pega o ID do cliente e adiciona a conta a ele
					//Se cliente não existe, cria o cliente, pega o ID e adiciona a conta a ele
					
					Cliente cliente = conta.getCliente();
					
					// Cliente já existe no banco, usando o merge para atualizar
					if (cliente != null && cliente.getCpf() != null) { //Cliente da conta que estou recebendo como parametro
						Cliente clienteExistente = clienteService.findClienteByCpf(cliente.getCpf()); //Peguei o cliente existente
						if (clienteExistente != null) { //confirmo se existe mesmo
							contaCorrente.setCliente(clienteExistente); //Add o cliente a conta
							clienteExistente.getContas().add(contaCorrente); //Adiciona na lista do cliente a conta nova
							entityManager.merge(clienteExistente);
							
						}

					} else {
						// Cliente é novo, vai ser persistido como uma nova entidade com a conta
						cliente.getContas().add(contaCorrente); //Add conta corrente no cliente
						contaCorrente.setCliente(cliente); //Adiciono o cliente que recebi de parametro na conta						
						entityManager.persist(cliente); // Persistir o cliente novo
						
						
					}
					
					
					
					 //Adiciona uma conta
					return savePersist(contaCorrente); //Salvo a conta se o cliente existe ou se foi persistido agora
					//return repositoryEscolhido.save(contaCorrente);

				} else if (repositoryEscolhido instanceof PoupancaRepository && tipoDeConta == TipoDeConta.POUPANCA) {

					Double acrescimoTaxaRendimento = 0.0d;
					Double taxaMensal = 0.0d;

					if (saldoDaConta <= 1.000d) {
						categoriaDaConta = CategoriaDaConta.COMUM;
						acrescimoTaxaRendimento = 0.005d;
						taxaMensal = Math.pow(1 + acrescimoTaxaRendimento, 1.0 / 12) - 1;
					}

					if (saldoDaConta > 1.000d && saldoDaConta <= 5.000d) {
						categoriaDaConta = CategoriaDaConta.SUPER;
						acrescimoTaxaRendimento = 0.007d;
						taxaMensal = Math.pow(1 + acrescimoTaxaRendimento, 1.0 / 12) - 1;
					}

					if (saldoDaConta > 5.000d) {
						categoriaDaConta = CategoriaDaConta.PREMIUM;
						acrescimoTaxaRendimento = 0.009d;
						taxaMensal = Math.pow(1 + acrescimoTaxaRendimento, 1.0 / 12) - 1;
					}

					String numContaPoupanca = numeroDaConta.concat("-PP");

					Conta contaPoupanca = new Poupanca(conta.getCliente(), TipoDeConta.POUPANCA, saldoDaConta,
							numContaPoupanca, categoriaDaConta, acrescimoTaxaRendimento, taxaMensal);

					return repositoryEscolhido.save(contaPoupanca);
				}

			}

			return null;

		} catch (UnsuportedContaNotValidException e) {

			System.err.println("Erro: " + e.getMessage());
			return null;

		} catch (UnsuportedClientDontExistException e) {

			System.err.println("Erro: " + e.getMessage());
			return null;

		} catch (UnsuportedClientDataWrongException e) {

			System.err.println("Erro: " + e.getMessage());
			return null;

		} catch (UnsuportedContaSaldoNullException e) {

			System.err.println("Erro: " + e.getMessage());
			return null;

		} catch (Exception e) {

			System.err.println("Erro: " + e.getMessage());
			return null;

		}
	}

	public String gerarNumeroDaConta(Conta conta) {

		int[] sequencia = new int[8];
		Random random = new Random();
		String minhaConta = "";

		for (int i = 0; i < sequencia.length; i++) {
			sequencia[i] = 1 + random.nextInt(8);
		}

		for (int i = 0; i < sequencia.length; i++) {
			minhaConta += Integer.toString(sequencia[i]);
		}

		return minhaConta;
	}
	
	

	public Optional<Conta> findById(Long id) {
		for (ContaRepository repository : contaRepositories) {
			if (repository instanceof CorrenteRepository) {
				
				return repository.findById(id);
				//return ((CorrenteRepository) repository).findById(id);
			} else if (repository instanceof PoupancaRepository) {
				return repository.findById(id);
				//return ((PoupancaRepository) repository).findById(id);
			}
		}
		return Optional.empty();
	}
	
	@Transactional
	public Conta savePersist(Conta conta) {
		
		try {
			
			entityManager.persist(conta);
			return conta;
			
		} catch (Exception e) {
			throw new IllegalArgumentException("Conta não pode ser criada");
		}
		
		//return entityManager.persist(conta);
		
	}

	@Transactional
	public Conta updateMerge(Conta conta) {
	
				
		try {
			
			entityManager.merge(conta.getId());
			return conta;
				 
		} catch (Exception e) {
			throw new IllegalArgumentException("Conta não pode ser atualizada");
		}
		
	}

//	@Transactional
//	public Conta update(Conta conta) {
//		for (ContaRepository repository : contaRepositories) {
//			if (repository instanceof CorrenteRepository && conta instanceof Corrente) {
//				//return ((CorrenteRepository) repository).save((Corrente) conta);
//				entityManager.merge(conta.getId());
//				 
//			} else if (repository instanceof PoupancaRepository && conta instanceof Poupanca) {
//				//return ((PoupancaRepository) repository).save((Poupanca) conta);
//				entityManager.merge(conta.getId());
//			}
//		}
//		throw new IllegalArgumentException("Conta não pode ser atualizada");
//	}

	public void deleteById(Long id) {
		for (ContaRepository repository : contaRepositories) {
			if (repository instanceof CorrenteRepository) {
				
				repository.deleteById(id);
				
				//((CorrenteRepository) repository).deleteById(id);
				//return; 
			} else if (repository instanceof PoupancaRepository) {
				
				repository.deleteById(id);
//				((PoupancaRepository) repository).deleteById(id);
//				return;
			}
		}
		throw new IllegalArgumentException("Conta não pode ser deletada");
	}

	public List<Conta> getAll() {
		
		for (ContaRepository repository : contaRepositories) {
			if (repository instanceof CorrenteRepository) {				
				return repository.findAll();				
			
			} else if (repository instanceof PoupancaRepository) {
				return repository.findAll();
			}
		}
		return null;
	}

	public List<Conta> getAllById(Long id) {
	
		for (ContaRepository repository : contaRepositories) {
			if (repository instanceof CorrenteRepository) {
				
				return repository.findAllById(Collections.singletonList(id));	
				
			} else if (repository instanceof PoupancaRepository) {
				
				return repository.findAllById(Collections.singletonList(id));	
			}
		}
		return null;
	}
	
	

	// FIND BY ID
//	public Optional<Conta> findById(Long id) {		
//		return contaRepository.findById(id);
//	}
//	
//	//UPDATE
//	public Conta update(Conta conta) {		
//		return contaRepository.save(conta);
//	}
//	
//	//DELETE BY ID
//	public void deleteById(Long id) {
//		contaRepository.deleteById(id);		
//	}
//	
//	//LIST ALL
//	public List<Conta> getAll(){
//		return contaRepository.findAll();
//	}
//	
//	public List<Conta> getAllById(Long id){
//		return contaRepository.findAllById(id);
//	}

	// Validação de campos
//	public boolean validarTipoDeConta(TipoDeConta tipoEscolhido) {
//		
//		if(tipoEscolhido)
//		
//	}

}
