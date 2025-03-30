package br.com.marcielli.DigBank360.contas.corrente;


import java.util.ArrayList;

import br.com.marcielli.DigBank360.clientes.Cliente;
import br.com.marcielli.DigBank360.contas.Conta;
import br.com.marcielli.DigBank360.helpers.CategoriaDaConta;
import br.com.marcielli.DigBank360.helpers.TipoDeCartao;
import br.com.marcielli.DigBank360.helpers.TipoDeConta;
import br.com.marcielli.DigBank360.helpers.TipoDeTransferencia;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
//@NoArgsConstructor
//@AllArgsConstructor
@Data
public class Corrente extends Conta {
	
	@Id
	@Nonnull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Double taxaManutencaoMensal;
	//private String nomeDonoDaConta;
	
	
	public Corrente(Long id, Cliente cliente, TipoDeConta tipoDeConta, CategoriaDaConta categoriaDaConta, TipoDeCartao tipoDeCartao, 
			TipoDeTransferencia tipoDeTransferencia, Double saldoDaConta, String numeroDaConta) {
		super(id, cliente, tipoDeConta, categoriaDaConta, tipoDeCartao, tipoDeTransferencia, saldoDaConta);		
			
		setSaldoDaConta(saldoDaConta);
		this.taxaManutencaoMensal = setTaxaManutencaoMensal(saldoDaConta);
		
		if(saldoDaConta <= 1000d) {
			categoriaDaConta = CategoriaDaConta.COMUM;
			super.setCategoriaDaConta(categoriaDaConta);
			
			System.out.println("Saldo: "+saldoDaConta);
			System.out.println("Categoria: "+categoriaDaConta);
			System.out.println("Taxa de Manutenção Mensal: "+getTaxaManutencaoMensal());			

			//this.nomeDonoDaConta = cliente.getNome();
		}
		
		if(saldoDaConta > 1000d && saldoDaConta <= 5000d) {
			categoriaDaConta = CategoriaDaConta.SUPER;
			super.setCategoriaDaConta(categoriaDaConta);
			System.out.println("Saldo: "+saldoDaConta);
			System.out.println("Categoria: "+categoriaDaConta);
			System.out.println("Taxa de Manutenção Mensal: "+getTaxaManutencaoMensal());
			
		//	this.nomeDonoDaConta = cliente.getNome();
		}
		
		if(saldoDaConta > 5000d) {
			categoriaDaConta = CategoriaDaConta.PREMIUM;
			super.setCategoriaDaConta(categoriaDaConta);
			System.out.println("Saldo: "+saldoDaConta);
			System.out.println("Categoria: "+categoriaDaConta);
			System.out.println("Taxa de Manutenção Mensal: "+getTaxaManutencaoMensal());
			
			//this.nomeDonoDaConta = cliente.getNome();
		}
	}
	
//	public Corrente() {
//		super(id, tipoDeConta, categoriaDaConta, numeroDaConta, clientesaldoDaConta);
//		setTipoDeConta(tipo);
//		setNumeroDaConta(numeroDaConta);
//		setCategoriaDaConta(categoriaDaConta);
//		setSaldoDaConta(saldoDaConta);
//		this.taxaManutencaoMensal = setTaxaManutencaoMensal(saldoDaConta);
////		Corrente novaConta = new Corrente();
////		novaConta.setTipoDeConta(tipo);
////		novaConta.setNumeroDaConta(numeroDaConta);
////		novaConta.setCategoriaDaConta(categoriaDaConta);
////		novaConta.setSaldoDaConta(saldoDaConta);
////		novaConta.setTaxaManutencaoMensal(saldoDaConta);
////		novaConta.setCliente(cliente);
////		ArrayList<Conta> listCont = new ArrayList<Conta>();
////		listCont.add(novaConta);
//		
//		
//		System.out.println("\nCONTA: "+TipoDeConta.CORRENTE+" n° "+numeroDaConta);
//		System.out.println("SALDO: "+saldoDaConta);
//		System.out.println("CATEGORIA: "+categoriaDaConta);
//		System.out.println("TAXA DE MANUTENÇÃO MENSAL: "+getTaxaManutencaoMensal());
//		System.out.println("CLIENTE: "+cliente.getNome()+" - CPF: "+cliente.getCpf()+" - DATA DE NASCIMENTO: "+cliente.getDataNascimento()+"\nENDEREÇO: "
//		+cliente.getEndereco()+"\nCONTAS: "+cliente.getContas());
//		
//		
//		
//		
//		
//	}

	public void criarContaFactory(Corrente conta) {
		
	}
	
	@Override
	public Double exibirSaldo() {
		return getSaldoDaConta();
	}

	@Override
	public void enviarPix(Double valor) {
		setSaldoDaConta(getSaldoDaConta() - valor);
	}

	@Override
	public void receberPix(Double valor) {
		setSaldoDaConta(getSaldoDaConta() + valor);		
	}
	
	public void atualizaCategoria(float valorAntigo, float valor, int enviaOuRecebe) {
		CategoriaDaConta categoria;
		
		float total = (float) 0.0;
		//Valor que ta enviando ou recebendo do pix
		
		if(enviaOuRecebe == 1) { //enviaOuRecebe = 1 (Envia pix)
			total += valorAntigo - valor;
		} else if (enviaOuRecebe == 2) { //enviaOuRecebe = 2 (Recebe Pix)
			total += valorAntigo + valor;
		}
					
		if(total <= 1000d) {
			categoria = CategoriaDaConta.COMUM;
			super.setCategoriaDaConta(categoria);
			
		}
		
		if(total > 1000d && total <= 5000d) {
			categoria = CategoriaDaConta.SUPER;
			super.setCategoriaDaConta(categoria);
		}
		
		if(total > 5000d) {
			categoria = CategoriaDaConta.PREMIUM;
			super.setCategoriaDaConta(categoria);
		}
	}
	
	public Double setTaxaManutencaoMensal(Double valor) {
		
		if(valor <= 1000d) {		
			this.taxaManutencaoMensal = 12.00d;		
		}
		
		if(valor  > 1000d && valor  <= 5000d) {			
			this.taxaManutencaoMensal = 8.00d;			
		}
		
		if(valor  > 5000d) {		
			this.taxaManutencaoMensal = 0d;		
		}
		return taxaManutencaoMensal;
	}
	
	public void descontarTaxaManutencaoMensal(Cliente cliente) {	
		
		
//		ContaCorrenteService ccService = new ContaCorrenteService();
//		ccService.descontarTaxaManutencaoMensal(cliente);		
	}


}
