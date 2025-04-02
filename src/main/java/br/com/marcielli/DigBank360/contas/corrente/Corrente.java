package br.com.marcielli.DigBank360.contas.corrente;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.marcielli.DigBank360.clientes.Cliente;
import br.com.marcielli.DigBank360.contas.Conta;
import br.com.marcielli.DigBank360.helpers.CategoriaDaConta;
import br.com.marcielli.DigBank360.helpers.TipoDeCartao;
import br.com.marcielli.DigBank360.helpers.TipoDeConta;
import br.com.marcielli.DigBank360.helpers.TipoDeTransferencia;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@DiscriminatorValue(value = "CORRENTE")
public class Corrente extends Conta {
	
//	@Id	
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
	
	private Double taxaManutencaoMensal;
	
	public Corrente() {}
	
	public Corrente(Long id, Long version, Cliente cliente, TipoDeConta tipoDeConta, CategoriaDaConta categoriaDaConta,
			TipoDeCartao tipoDeCartao, TipoDeTransferencia tipoDeTransferencia, Double saldoDaConta,
			String numeroDaConta) {
		super(id, version, cliente, tipoDeConta, categoriaDaConta, tipoDeCartao, tipoDeTransferencia, saldoDaConta,
				numeroDaConta);
		super.setSaldoDaConta(saldoDaConta);
		super.setCliente(cliente);
		super.setCategoriaDaConta(categoriaDaConta);
		this.taxaManutencaoMensal = setTaxaManutencaoMensal(saldoDaConta);
		
		
	}

	public Corrente(Double taxaManutencaoMensal) {
		super();
		this.taxaManutencaoMensal = taxaManutencaoMensal;
	}
	
	@JsonCreator
	public Corrente(@JsonProperty("cliente") Cliente cliente, TipoDeConta tipoDaConta, @JsonProperty("saldoDaConta") Double saldoDaConta, String numeroDaConta, CategoriaDaConta categoriaDaConta) {
	  //  super.setId(id);
	    super.setCliente(cliente);
	    super.setTipoDeConta(tipoDaConta);
	    super.setSaldoDaConta(saldoDaConta);
	    super.setNumeroDaConta(numeroDaConta);
	    super.setCategoriaDaConta(categoriaDaConta);
		this.taxaManutencaoMensal = taxaManutencaoMensal;		
	}


	public Double getTaxaManutencaoMensal() {
		return taxaManutencaoMensal;
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
					
		if(total <= 1.000d) {
			categoria = CategoriaDaConta.COMUM;
			super.setCategoriaDaConta(categoria);
			
		}
		
		if(total > 1.000d && total <= 5.000d) {
			categoria = CategoriaDaConta.SUPER;
			super.setCategoriaDaConta(categoria);
		}
		
		if(total > 5.000d) {
			categoria = CategoriaDaConta.PREMIUM;
			super.setCategoriaDaConta(categoria);
		}
	}
	
	public Double setTaxaManutencaoMensal(Double valor) {
		
		if(valor <= 1.000d) {		
			this.taxaManutencaoMensal = 12.00d;		
		}
		
		if(valor  > 1.000d && valor  <= 5.000d) {			
			this.taxaManutencaoMensal = 8.00d;			
		}
		
		if(valor  > 5.000d) {		
			this.taxaManutencaoMensal = 0d;		
		}
		return taxaManutencaoMensal;
	}
	
	public void descontarTaxaManutencaoMensal(Cliente cliente) {	
		
		
//		ContaCorrenteService ccService = new ContaCorrenteService();
//		ccService.descontarTaxaManutencaoMensal(cliente);		
	}

//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}

	@Override
	public String toString() {
		return "Corrente [taxaManutencaoMensal=" + taxaManutencaoMensal + ", getTaxaManutencaoMensal()="
				+ getTaxaManutencaoMensal() + ", exibirSaldo()=" + exibirSaldo() + ", getVersion()=" + getVersion()
				+ ", getId()=" + getId() + ", getCliente()=" + getCliente() + ", getTipoDeConta()=" + getTipoDeConta()
				+ ", getCategoriaDaConta()=" + getCategoriaDaConta() + ", getTipoDeCartao()=" + getTipoDeCartao()
				+ ", getTipoDeTransferencia()=" + getTipoDeTransferencia() + ", getSaldoDaConta()=" + getSaldoDaConta()
				+ ", getNumeroDaConta()=" + getNumeroDaConta() + ", toString()=" + super.toString() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}
	
	


}
