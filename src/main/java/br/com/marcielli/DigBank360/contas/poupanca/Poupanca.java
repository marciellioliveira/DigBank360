package br.com.marcielli.DigBank360.contas.poupanca;

import br.com.marcielli.DigBank360.clientes.Cliente;
import br.com.marcielli.DigBank360.contas.Conta;
import br.com.marcielli.DigBank360.contas.corrente.Corrente;
import br.com.marcielli.DigBank360.helpers.CategoriaDaConta;
import br.com.marcielli.DigBank360.helpers.TipoDeCartao;
import br.com.marcielli.DigBank360.helpers.TipoDeConta;
import br.com.marcielli.DigBank360.helpers.TipoDeTransferencia;
import jakarta.annotation.Nonnull;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue(value = "Poupanca")
public class Poupanca extends Conta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Double acrescimoTaxaRendimento;
	private Double taxaMensal;
	
	public Poupanca(Long id, Cliente cliente, TipoDeConta tipoDeConta, CategoriaDaConta categoriaDaConta, TipoDeCartao tipoDeCartao, 
			TipoDeTransferencia tipoDeTransferencia, Double saldoDaConta, String numeroDaConta) {
		super(id, cliente, tipoDeConta, categoriaDaConta, tipoDeCartao, tipoDeTransferencia, saldoDaConta);
				
		setSaldoDaConta(saldoDaConta);
		if(saldoDaConta <= 1000) {
			categoriaDaConta = CategoriaDaConta.COMUM;
			super.setCategoriaDaConta(categoriaDaConta);
			this.acrescimoTaxaRendimento = 0.005d;
			
			this.taxaMensal = Math.pow(1+acrescimoTaxaRendimento, 1.0/12) - 1;
			
			System.out.println("Saldo: "+saldoDaConta);
			System.out.println("Categoria: "+categoriaDaConta);
			System.out.println("Taxa de Rendimento Anual: "+acrescimoTaxaRendimento);
		}
		
		if(saldoDaConta > 1000 && saldoDaConta <= 5000) {
			categoriaDaConta = CategoriaDaConta.SUPER;
			super.setCategoriaDaConta(categoriaDaConta);
			this.acrescimoTaxaRendimento = 0.007d;
			
			this.taxaMensal = Math.pow(1+acrescimoTaxaRendimento, 1.0/12) - 1;
			
			System.out.println("Saldo: "+saldoDaConta);
			System.out.println("Categoria: "+categoriaDaConta);
			System.out.println("Taxa de Rendimento Anual: "+acrescimoTaxaRendimento);
		}
		
		if(saldoDaConta > 5000) {
			categoriaDaConta = CategoriaDaConta.PREMIUM;
			super.setCategoriaDaConta(categoriaDaConta);
			this.acrescimoTaxaRendimento = 0.009d;
			
			this.taxaMensal = Math.pow(1+acrescimoTaxaRendimento, 1.0/12) - 1;
			
			System.out.println("Saldo: "+saldoDaConta);
			System.out.println("Categoria: "+categoriaDaConta);
			System.out.println("Taxa de Rendimento Anual: "+acrescimoTaxaRendimento);
		}
	}
	
	public Poupanca(TipoDeConta tipo, String numeroDaConta, Cliente cliente, CategoriaDaConta categoriaDaConta, Double saldoDaConta, Double acrescimoTaxaRendimento, Double taxaMensal) {
		
		super();
		setTipoDeConta(tipo);
		setNumeroDaConta(numeroDaConta);	
		setCliente(cliente);
		setCategoriaDaConta(categoriaDaConta);
		setSaldoDaConta(saldoDaConta);
		this.acrescimoTaxaRendimento = acrescimoTaxaRendimento;
		this.taxaMensal = taxaMensal;
		System.out.println("\nCONTA: "+TipoDeConta.CORRENTE+" n° "+numeroDaConta);
		System.out.println("SALDO: "+saldoDaConta);
		System.out.println("CATEGORIA: "+categoriaDaConta);
		System.err.println("ACRESCIMO DE RENDIMENTO: "+acrescimoTaxaRendimento);
		System.err.println("TAXA MENSAL: "+taxaMensal);
		System.out.println("CLIENTE: "+cliente.getNome()+" - CPF: "+cliente.getCpf()+" - DATA DE NASCIMENTO: "+cliente.getDataNascimento()+"\nENDEREÇO: "
		+cliente.getEndereco()+"\nCONTAS: "+cliente.getContas());
	}
	
	public Poupanca() {}

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAcrescimoTaxaRendimento() {
		return acrescimoTaxaRendimento;
	}

	public void setAcrescimoTaxaRendimento(Double acrescimoTaxaRendimento) {
		this.acrescimoTaxaRendimento = acrescimoTaxaRendimento;
	}

	public Double getTaxaMensal() {
		return taxaMensal;
	}

	public void setTaxaMensal(Double taxaMensal) {
		this.taxaMensal = taxaMensal;
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
	
	public void atualizaCategoria(Double valorAntigo, Double valor, int enviaOuRecebe) {
		CategoriaDaConta categoria;
		
		Double total = 0.0;
		//Valor que ta enviando ou recebendo do pix
		
		if(enviaOuRecebe == 1) { //enviaOuRecebe = 1 (Envia pix)
			total += valorAntigo - valor;
		} else if (enviaOuRecebe == 2) { //enviaOuRecebe = 2 (Recebe Pix)
			total += valorAntigo + valor;
		}
					
		if(total <= 1000) {
			categoria = CategoriaDaConta.COMUM;
			super.setCategoriaDaConta(categoria);
			
		}
		
		if(total > 1000 && total <= 5000) {
			categoria = CategoriaDaConta.SUPER;
			super.setCategoriaDaConta(categoria);
		}
		
		if(total > 5000) {
			categoria = CategoriaDaConta.PREMIUM;
			super.setCategoriaDaConta(categoria);		
		}			
	}
	
	public void acrescentarTaxaRendimento(Cliente cliente) {		
		//Poupanca cps = new ContaPoupancaService();
		//cps.creditarTaxaVigenteMensal(cliente);
	}

}
