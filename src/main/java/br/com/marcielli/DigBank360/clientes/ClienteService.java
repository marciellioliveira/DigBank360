package br.com.marcielli.DigBank360.clientes;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.marcielli.DigBank360.contas.Conta;
import br.com.marcielli.DigBank360.contas.ContaRepository;
import br.com.marcielli.DigBank360.contas.ContaService;
import br.com.marcielli.DigBank360.exception.clientes.UnsuportedAnoNascimentoException;
import br.com.marcielli.DigBank360.exception.clientes.UnsuportedBairroException;
import br.com.marcielli.DigBank360.exception.clientes.UnsuportedCepException;
import br.com.marcielli.DigBank360.exception.clientes.UnsuportedCidadeException;
import br.com.marcielli.DigBank360.exception.clientes.UnsuportedClientDontExistException;
import br.com.marcielli.DigBank360.exception.clientes.UnsuportedClientDuplicatedExistException;
import br.com.marcielli.DigBank360.exception.clientes.UnsuportedComplementoException;
import br.com.marcielli.DigBank360.exception.clientes.UnsuportedCpfException;
import br.com.marcielli.DigBank360.exception.clientes.UnsuportedDiaNascimentoException;
import br.com.marcielli.DigBank360.exception.clientes.UnsuportedEstadoException;
import br.com.marcielli.DigBank360.exception.clientes.UnsuportedMesNascimentoException;
import br.com.marcielli.DigBank360.exception.clientes.UnsuportedNameException;
import br.com.marcielli.DigBank360.exception.clientes.UnsuportedNumeroException;
import br.com.marcielli.DigBank360.exception.clientes.UnsuportedRuaException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
//	@Autowired
//	private ContaRepository contaRepository;
//	
	@Autowired
	private EntityManager entityManager;
//	
	
	//CREATE
	@Transactional
	public Cliente save(Cliente cliente) {
		
		try {
			
			if(!validarNome(cliente.getNome())) {
				throw new UnsuportedNameException("Nome inválido");
			}
			
			if(!validarAnoNascimento(cliente.getDataNascimento().getYear())) {
				throw new UnsuportedAnoNascimentoException("Ano de Nascimento inválido");
			}
			
			if(!validarMesNascimento(cliente.getDataNascimento().getMonthValue())) {
				throw new UnsuportedMesNascimentoException("Mês de Nascimento inválido");
			}
			
			if(!validarDiaNascimento(cliente.getDataNascimento().getDayOfMonth())) {
				throw new UnsuportedDiaNascimentoException("Dia de Nascimento inválido");
			}
			
			if(cliente.getEndereco().getCep().equals(null)) {
				throw new UnsuportedCepException("Cep inválido");
			}
			
			if(cliente.getEndereco().getEstado().equals(null)) {
				throw new UnsuportedEstadoException("Estado inválido");
			}		
			
			if(cliente.getEndereco().getCidade().equals(null)) {
				throw new UnsuportedCidadeException("Cidade inválido");
			}		
			
			if(cliente.getEndereco().getBairro().equals(null)) {
				throw new UnsuportedBairroException("Bairro inválido");
			}
			
			if(cliente.getEndereco().getRua().equals(null)) {
				throw new UnsuportedRuaException("Rua inválido");
			}
			
			if(cliente.getEndereco().getNumero().equals(null)) {
				throw new UnsuportedNumeroException("Número inválido");
			}
			
			if(cliente.getEndereco().getComplemento().equals(null)) {
				throw new UnsuportedComplementoException("Complemento inválido");
			}		
			
			if(!isCpfValido(cliente.getCpf())) {
				throw new UnsuportedCpfException("CPF inválido");
			}

			//Cliente id sempre vai ser nulo porque ele só vai ter id quando clienteRepository.save(cliente)
			for(Cliente clienteExiste : getAll()) {
				if(clienteExiste.getCpf().equals(cliente.getCpf()) || clienteExiste.getNome().equals(cliente.getNome()) ) {
					throw new UnsuportedClientDuplicatedExistException("Cliente duplicado. Cadastre um novo cliente.");
				}
			}
			
			
				
		return clienteRepository.save(cliente);			
			
			
		} catch(UnsuportedClientDuplicatedExistException e) {
			
			System.err.println("Erro: "+e.getMessage());
			return null;
			
		} catch (UnsuportedNameException e) {
			
			System.err.println("Erro: "+e.getMessage());
			return null;
			
		} catch (UnsuportedAnoNascimentoException e) {
			
			System.err.println("Erro: "+e.getMessage());
			return null;
			
		}  catch (UnsuportedMesNascimentoException e) {
			
			System.err.println("Erro: "+e.getMessage());
			return null;
			
		}  catch (UnsuportedDiaNascimentoException e) {
			
			System.err.println("Erro: "+e.getMessage());
			return null;
			
		}  catch (UnsuportedCepException e) {
			
			System.err.println("Erro: "+e.getMessage());
			return null;
			
		}  catch (UnsuportedEstadoException e) {
			
			System.err.println("Erro: "+e.getMessage());
			return null;
			
		}  catch (UnsuportedCidadeException e) {
			
			System.err.println("Erro: "+e.getMessage());
			return null;
			
		}  catch (UnsuportedBairroException e) {
			
			System.err.println("Erro: "+e.getMessage());
			return null;
			
		}  catch (UnsuportedRuaException e) {
			
			System.err.println("Erro: "+e.getMessage());
			return null;
			
		}  catch (UnsuportedNumeroException e) {
			
			System.err.println("Erro: "+e.getMessage());
			return null;
			
		}  catch (UnsuportedComplementoException e) {
			
			System.err.println("Erro: "+e.getMessage());
			return null;
			
		}  catch (UnsuportedCpfException e) {
			
			System.err.println("Erro: "+e.getMessage());
			return null;
		} catch (NullPointerException e) {
			
			System.err.println("Erro: "+e.getMessage());
			return null;
			
		} catch (Exception e) {
			
			System.err.println("Erro: "+e.getMessage());
			return null;
		}
	}
	

	public Cliente findClienteByCpf(Long long1) {
	    // Consulta o cliente pelo CPF e retorna a lista de resultados
	    List<Cliente> clientes = entityManager.createQuery("SELECT c FROM Cliente c WHERE c.cpf = :cpf", Cliente.class)
	            .setParameter("cpf", long1)
	            .getResultList();  // Retorna uma lista, nunca lança exceção

	    // Se a lista estiver vazia, significa que não encontrou nenhum cliente com esse CPF
	    if (clientes.isEmpty()) {
	        return null;
	    } else {
	        // Se encontrar, retorna o primeiro cliente encontrado
	        return clientes.get(0);
	    }
	}
	
//	@Transactional(rollbackOn = Exception.class)
//	public Cliente merge(Cliente cliente) {
//		if (cliente == null || cliente.getNome() == null || cliente.getCpf() == null) {
//	        throw new IllegalArgumentException("Cliente inválido");
//	    }
//		return entityManager.merge(cliente);
//	}
	
	//FIND BY ID
	public Optional<Cliente> findById(Long id) {
		return clienteRepository.findById(id);
	}
	
	//UPDATE
	public Cliente update(Cliente cliente) {		
		return clienteRepository.save(cliente);
	}
	
	//DELETE BY ID
	public void deleteById(Long id) {
		clienteRepository.deleteById(id);		
	}
	
	//LIST ALL
	public List<Cliente> getAll(){
		return clienteRepository.findAll();
	}
	
	
	
	
	
	
	//Validação de campos
	public boolean isCpfValido(Long cpf) {
		
		String cpfNotValid = cpf.toString();
		
		try {	
		    String cpfValida = Long.toString(cpf);
		    
		    cpfValida = cpfValida.replaceAll("[^0-9]", "");
		    // Verifica se o CPF possui 11 dígitos
		    if (cpfValida.length() != 11) {
		    	return false;
		    }
		    
		    // Verifica se o CPF possui todos os dígitos iguais (exemplo: 111.111.111-11)
		    if (cpfValida.matches("(\\d)\\1{10}")) {
		    	return false;
		    }
		    
		    // Cálculo do primeiro dígito verificador
		    int valor = 0;
		    int j = 10;
		    
		    for (int i = 0; i < 9; i++) {
		        char letra = cpfValida.charAt(i);
		        int caracter = letra - '0';
		        valor += caracter * j;
		        j--;
		    }
		    
		    int resultado = (valor * 10) % 11;
		    if (resultado == 10) {
		        resultado = 0;
		    }
		    
		    // Verifica se o primeiro dígito verificador está correto
		    if (resultado != (cpfValida.charAt(9) - '0')) {
		       return false;
		    }
		    
		    // Cálculo do segundo dígito verificador
		    int valor2 = 0;
		    int b = 11;
		    
		    for (int i = 0; i < 10; i++) {
		        char letra2 = cpfValida.charAt(i);
		        int caracter2 = letra2 - '0';
		        valor2 += caracter2 * b;
		        b--;
		    }
		    
		    int resultado2 = (valor2 * 10) % 11;
		    if (resultado2 == 10) {
		        resultado2 = 0;
		    }
		    
		    // Verifica se o segundo dígito verificador está correto
		    if (resultado2 != (cpfValida.charAt(10) - '0')) {
		    	return false;
		    }
		} catch (Exception e) {
			return false;
		}

	    return true;
	}
	
	public boolean validarNome(String nome) {
		
		for(int i=0; i<nome.length(); i++) {			
			char letra = nome.charAt(i);
			Boolean flag = Character.isDigit(letra);
			
			if(flag) {
				return false;
				//throw new UnsuportedNameException("O nome '"+nome+"' digitado possui número.\nO nome de cliente deve conter apenas letras, tente adicionar novamente!");
			}
		}			
		
		if(nome.contains(",") || nome.contains(".") || nome.contains("!") || nome.contains("\\") || nome.contains("\"") || nome.contains("/") || nome.contains("#") || nome.contains("$") || nome.contains("%") || nome.contains("&") || nome.contains("*") || nome.contains(":") || nome.contains(";") || nome.contains("+") || nome.contains("<") || nome.contains(">") || nome.contains("=") || nome.contains("?") || nome.contains("@") || nome.contains("[") || nome.contains("]") || nome.contains("_") || nome.contains("{") || nome.contains("}") || nome.contains("|")) {
			return false;
			//throw new UnsuportedNameException("O nome '"+nome+"' digitado possui caracter especial.\nO nome de cliente deve conter apenas letras, tente adicionar novamente!");
		}	
		
		String tam = "";
		
		if(nome.length() < 2 || nome.length()> 100) {
			
			if(nome.length() < 2) {
				tam = "MAIOR";
			}
			
			if(nome.length() > 2) {
				tam = "MENOR";
			}
			return false;
			//throw new UnsuportedNameException("Você tentou cadastrar um cliente chamado '"+nome+"' com "+nome.length()+" caracter no nome.\nTente cadastrar um cliente com um nome "+tam+".");
		}
		
		return true;
	}	
	
	public boolean validarAnoNascimento(int anoNac) {	
		
		if(String.valueOf(Math.abs(anoNac)).length() != 4) {
			return false;
			//throw new DataNascNotValidException("Você digitou o número: "+anoNac+". O ano deve ter 4 dígitos. Exemplo: 1990");
		}		
		
		int anoAtual = LocalDate.now().getYear();
		
		int minhaIdade = anoAtual - anoNac;
		
		
		if(minhaIdade < 18) {
			return false;
			//throw new DataNascNotValidException("O cliente que você tentou cadastrar é menor de idade.\\nTente adicionar um cliente com idade maior ou igual a 18 anos.\n");
		}
		
		if(anoNac < 1900) {
			return false;
			//throw new DataNascNotValidException("\nIdade avançada. ");
		}
		
		return true;

	}
	
	public boolean validarMesNascimento (int mesNac) {	
		
		String mesForm = String.format("%02d", Math.abs(mesNac));
		
		if(mesForm.length() != 2) {
			return false;
			//throw new DataNascNotValidException("Você digitou o número: "+mesNac+". O mês de nascimento deve ter 2 dígitos. Exemplo: 01");
		}		
		

		if(mesNac <= 0 || mesNac > 12) {
			return false;
			//throw new DataNascNotValidException("O mês de nascimento deve estar entre 1 a 12.");
		}
		
		return true;

	}
	
	public boolean validarDiaNascimento (int diaNasc) {	
		
		String diaForm = String.format("%02d", Math.abs(diaNasc));
		
		if(diaForm.length() != 2) {
			return false;
			//throw new DataNascNotValidException("Você digitou o número: "+diaNasc+". O dia de nascimento deve ter 2 dígitos. Exemplo: 01");
		}	
	
		
		if(diaNasc <= 0 || diaNasc >31) {
			throw new UnsuportedDiaNascimentoException("O dia de nascimento deve estar entre 01 e 31");
		}
		
		return true;
	}
	
	public boolean validarCep(String cep)  {
		
		if(!cep.contains("-")) {
			if(cep.length() != 9) {				
				return false;
				//throw new EnderecoNotValidException("Você digitou um cep com "+cep.length()+" caracteres. Digite um CEP com 9 caracteres com traço no final.");		
			}	
		} 
		
		if(cep.length() != 9) {	
			return false;
			//throw new EnderecoNotValidException("Você digitou um cep com "+cep.length()+" caracteres. Digite um CEP com 9 caracteres com traço no final.");		
		}	
			
			int numOcorrencias = 1;		
			HashMap<Character, Integer> findDuplicated = new HashMap<Character, Integer>();
			String novoCep = "";
			
			
			char[] meuCep = cep.toCharArray();
			
			for(int i=0; i<meuCep.length; i++) {
				if(!findDuplicated.containsKey(meuCep[i])) {
					findDuplicated.put(meuCep[i], 1);
					novoCep += meuCep[i];
					
				} else {
					findDuplicated.put(meuCep[i], 1);
					numOcorrencias++; 
				}
			}
			
			if(numOcorrencias >= 8) {
				return false;
				//throw new EnderecoNotValidException("O CEP '"+cep+"' digitado não é válido. Por favor, digite um CEP válido.");
			}
			
			return true;
	}
	
	public boolean validarCidade(String cidade){
		String pattern = "([a-zA-Z])\\1*";
		if(cidade.matches(pattern)) {
			return false;
			//throw new EnderecoNotValidException("Tente cadastrar uma cidade existente!");
		}
		
		
		int numOcorrencias = 1;		
		HashMap<Character, Integer> findDuplicated = new HashMap<Character, Integer>();
		String novaCidade = "";
		
		
		char[] minhaCidade = cidade.toCharArray();
		
		for(int i=0; i<minhaCidade.length; i++) {
			if(!findDuplicated.containsKey(minhaCidade[i])) {
				findDuplicated.put(minhaCidade[i], 1);
				novaCidade += minhaCidade[i];
				
			} else {
				findDuplicated.put(minhaCidade[i], 1);
				numOcorrencias++; 
			}
		}
		
		if(numOcorrencias >= 11) {
			return false;
			//throw new EnderecoNotValidException("A cidade '"+cidade+"' digitada não é válida. Por favor, digite uma cidade válida.");
		}		
		return true;
	}
	
	public boolean validarEstado(String estado)  {
		String pattern = "([a-zA-Z])\\1*";
		if(estado.matches(pattern)) {
			return false;
			//throw new EnderecoNotValidException("Tente cadastrar um estado existente!");
		}		
		
		int numOcorrencias = 1;		
		HashMap<Character, Integer> findDuplicated = new HashMap<Character, Integer>();
		String novoEstado = "";
		
		
		char[] meuEstado = estado.toCharArray();
		
		for(int i=0; i<meuEstado.length; i++) {
			if(!findDuplicated.containsKey(meuEstado[i])) {
				findDuplicated.put(meuEstado[i], 1);
				novoEstado += meuEstado[i];
				
			} else {
				findDuplicated.put(meuEstado[i], 1);
				numOcorrencias++; 
			}
		}
		
		if(numOcorrencias >= 11) {
			return false;
			//throw new EnderecoNotValidException("O estado '"+estado+"' digitada não é válido. Por favor, digite um estado válido.");
		}		
		return true;
	}
	
	public boolean validarRua(String rua) {
		String pattern = "([a-zA-Z])\\1*";
		if(rua.matches(pattern)) {
			return false;
			//throw new EnderecoNotValidException("Tente cadastrar uma rua existente!");
		}		
		
		int numOcorrencias = 1;		
		HashMap<Character, Integer> findDuplicated = new HashMap<Character, Integer>();
		String novaRua = "";
		
		
		char[] minhaRua = rua.toCharArray();
		
		for(int i=0; i<minhaRua.length; i++) {
			if(!findDuplicated.containsKey(minhaRua[i])) {
				findDuplicated.put(minhaRua[i], 1);
				novaRua += minhaRua[i];
				
			} else {
				findDuplicated.put(minhaRua[i], 1);
				numOcorrencias++; 
			}
		}
		
		if(numOcorrencias >= 11) {
			return false;
			//throw new EnderecoNotValidException("A rua'"+rua+"' digitada não é válida. Por favor, digite uma rua válida.");
		}		
		return true;
	}
	
	public boolean validarNumero(String numero)  {
		String pattern = "([a-zA-Z])\\1*";
		if(numero.matches(pattern)) {
			return false;
			//throw new EnderecoNotValidException("Tente cadastrar uma número existente!");
		}		
		
		int numOcorrencias = 1;		
		HashMap<Character, Integer> findDuplicated = new HashMap<Character, Integer>();
		String novoNumero = "";
		
		
		char[] meuNumero = numero.toCharArray();
		
		for(int i=0; i<meuNumero.length; i++) {
			if(!findDuplicated.containsKey(meuNumero[i])) {
				findDuplicated.put(meuNumero[i], 1);
				novoNumero += meuNumero[i];
				
			} else {
				findDuplicated.put(meuNumero[i], 1);
				numOcorrencias++; 
			}
		}
		
		if(numOcorrencias >= 11) {
			return false;
			//throw new EnderecoNotValidException("O número '"+numero+"' digitado não é válido. Por favor, digite um número válido.");
		}		
		return true;
	}
	
	public boolean validarBairro(String bairro){
		String pattern = "([a-zA-Z])\\1*";
		if(bairro.matches(pattern)) {
			return false;
			//throw new EnderecoNotValidException("Tente cadastrar um bairro existente!");
		}		
		
		int numOcorrencias = 1;		
		HashMap<Character, Integer> findDuplicated = new HashMap<Character, Integer>();
		String novoBairro = "";
		
		
		char[] meuBairro = bairro.toCharArray();
		
		for(int i=0; i<meuBairro.length; i++) {
			if(!findDuplicated.containsKey(meuBairro[i])) {
				findDuplicated.put(meuBairro[i], 1);
				novoBairro += meuBairro[i];
				
			} else {
				findDuplicated.put(meuBairro[i], 1);
				numOcorrencias++; 
			}
		}
		
		if(numOcorrencias >= 11) {
			return false;
			//throw new EnderecoNotValidException("O bairro '"+bairro+"' digitado não é válido. Por favor, digite um bairro válido.");
		}		
		return true;
	}
	
	public boolean validarComplemento(String complemento){
		String pattern = "([a-zA-Z])\\1*";
		if(complemento.matches(pattern)) {
			return false;
			//throw new EnderecoNotValidException("Tente cadastrar um complemento existente!");
		}		
		
		int numOcorrencias = 1;		
		HashMap<Character, Integer> findDuplicated = new HashMap<Character, Integer>();
		String novoComplemento = "";
		
		
		char[] meuComplemento = complemento.toCharArray();
		
		for(int i=0; i<meuComplemento.length; i++) {
			if(!findDuplicated.containsKey(meuComplemento[i])) {
				findDuplicated.put(meuComplemento[i], 1);
				novoComplemento += meuComplemento[i];
				
			} else {
				findDuplicated.put(meuComplemento[i], 1);
				numOcorrencias++; 
			}
		}
		
		if(numOcorrencias >= 11) {
			return false;
			//throw new EnderecoNotValidException("O complemento '"+complemento+"' digitado não é válido. Por favor, digite um complemento válido.");
		}		
		return true;
	}
	
	
	

}
