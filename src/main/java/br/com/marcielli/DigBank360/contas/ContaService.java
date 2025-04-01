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
	
//	@Autowired
//	private ContaRepository contaRepository;
//	
	
	 @PersistenceContext
     private EntityManager entityManager;

	@Autowired
	private ClienteService clienteService;
	
	private final List<ContaRepository> contaRepositories;
	
	@Autowired
	public ContaService(List<ContaRepository> contaRepositories) {
		this.contaRepositories = contaRepositories;
	}
	
	
	//CREATE
	@Transactional
	public Conta save(Conta conta) {
	
		try {
			
		
			entityManager.clear(); 

			Conta novaConta = ContaFactory.criarConta(conta);
			
			System.err.println("cpf "+novaConta.getCliente().getCpf());
			
			Cliente cliente = novaConta.getCliente();
			// Cliente já existe no banco, usando o merge para atualizar
			if(cliente != null && cliente.getCpf() != null) {
				 Cliente clienteExistente = clienteService.findClienteByCpf(cliente.getCpf());
				 if (clienteExistente != null) {
					 cliente = entityManager.merge(clienteExistente);
					 novaConta.setCliente(cliente);
				 }
				
			} else {
				 // Cliente é novo, vai ser persistido como uma nova entidade
	            entityManager.persist(cliente);  // Persistir o cliente novo
	            novaConta.setCliente(cliente);   // Associar cliente à nova conta
			}
			
			
			for(ContaRepository repositoryEscolhido : contaRepositories) {
				if(repositoryEscolhido instanceof CorrenteRepository && novaConta.getTipoDeConta() == TipoDeConta.CORRENTE) {
					
					String numeroDaConta = gerarNumeroDaConta(conta);		
					Double saldoDaConta = conta.getSaldoDaConta();
					CategoriaDaConta categoriaDaConta = null;
					
					if(saldoDaConta <= 1000d) {
						categoriaDaConta = CategoriaDaConta.COMUM;
					}
					
					if(saldoDaConta > 1000d && saldoDaConta <= 5000d) {
						categoriaDaConta = CategoriaDaConta.SUPER;			}
					
					if(saldoDaConta > 5000d) {
						categoriaDaConta = CategoriaDaConta.PREMIUM;				
					}			
					
					String numContaCorrente = numeroDaConta.concat("-CC");						
					
					Corrente contaCorrente = new Corrente(novaConta.getId(), novaConta.getCliente(), TipoDeConta.CORRENTE, novaConta.getSaldoDaConta(), novaConta.getNumeroDaConta());
					
					System.err.println("ID conta: "+contaCorrente.getId());
					System.err.println("id cliente: "+contaCorrente.getCliente().getId());
					System.err.println("cliete da conta: "+contaCorrente.getCliente().getNome());
					System.err.println("cliete da conta: "+contaCorrente.getCliente().getCpf());
					System.err.println("Numero da conta: "+contaCorrente.getNumeroDaConta());
					System.err.println("tipo da conta: "+contaCorrente.getTipoDeConta());
					System.err.println("saldo da conta: "+contaCorrente.getSaldoDaConta());		
					
					return repositoryEscolhido.save(contaCorrente);
					
				} else if(repositoryEscolhido instanceof PoupancaRepository && novaConta.getTipoDeConta() == TipoDeConta.POUPANCA) {
					System.err.println("POUPANÇA PORRA");
				}

			}
		
			return null;
			

						
		} catch (UnsuportedContaNotValidException e) {
			
			System.err.println("Erro: "+e.getMessage());
			return null;
			
		} catch (UnsuportedClientDontExistException e) {
			
			System.err.println("Erro: "+e.getMessage());
			return null;
			
		} catch (UnsuportedClientDataWrongException e) {
			
			System.err.println("Erro: "+e.getMessage());
			return null;
			
		} catch(UnsuportedContaSaldoNullException e) {
			
			System.err.println("Erro: "+e.getMessage());
			return null;
			
		} catch (Exception e) {
			
			System.err.println("Erro: "+e.getMessage());
			return null;
			
		}		
	}
	
	public String gerarNumeroDaConta(Conta conta) {
		
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

	
	public Optional<Conta> findById(Long id) {
	    for (ContaRepository repository : contaRepositories) {
	        if (repository instanceof CorrenteRepository) {
	            return ((CorrenteRepository) repository).findById(id);
	        } else if (repository instanceof PoupancaRepository) {
	            return ((PoupancaRepository) repository).findById(id);
	        }
	    }
	    return Optional.empty(); 
	}
	
	public Conta update(Conta conta) {
	    for (ContaRepository repository : contaRepositories) {
	        if (repository instanceof CorrenteRepository && conta instanceof Corrente) {
	            return ((CorrenteRepository) repository).save((Corrente) conta);
	        } else if (repository instanceof PoupancaRepository && conta instanceof Poupanca) {
	            return ((PoupancaRepository) repository).save((Poupanca) conta);
	        }
	    }
	    throw new IllegalArgumentException("Repositório não encontrado para o tipo de conta");
	}

	public void deleteById(Long id) {
	    for (ContaRepository repository : contaRepositories) {
	        if (repository instanceof CorrenteRepository) {
	            ((CorrenteRepository) repository).deleteById(id);
	            return;
	        } else if (repository instanceof PoupancaRepository) {
	            ((PoupancaRepository) repository).deleteById(id);
	            return;
	        }
	    }
	    throw new IllegalArgumentException("Conta não encontrada para o ID fornecido");
	}

	public List<Conta> getAll() {
	    List<Conta> allContas = new ArrayList<>();
	    for (ContaRepository repository : contaRepositories) {
	        if (repository instanceof CorrenteRepository) {
	            allContas.addAll(((CorrenteRepository) repository).findAll());
	        } else if (repository instanceof PoupancaRepository) {
	            allContas.addAll(((PoupancaRepository) repository).findAll());
	        }
	    }
	    return allContas;
	}

	public List<Conta> getAllById(Long id) {
	    List<Conta> contas = new ArrayList<>();
	    for (ContaRepository repository : contaRepositories) {
	        if (repository instanceof CorrenteRepository) {
	            contas.addAll(((CorrenteRepository) repository).findAllById(Collections.singletonList(id)));
	        } else if (repository instanceof PoupancaRepository) {
	            contas.addAll(((PoupancaRepository) repository).findAllById(Collections.singletonList(id)));
	        }
	    }
	    return contas;
	}


	
	
	
	//FIND BY ID
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
	
	
	//Validação de campos
//	public boolean validarTipoDeConta(TipoDeConta tipoEscolhido) {
//		
//		if(tipoEscolhido)
//		
//	}
	
	
	
	

}
