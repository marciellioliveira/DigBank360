package br.com.marcielli.DigBank360.contas;



import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import br.com.marcielli.DigBank360.clientes.Cliente;
import br.com.marcielli.DigBank360.contas.corrente.Corrente;
import br.com.marcielli.DigBank360.contas.poupanca.Poupanca;
import br.com.marcielli.DigBank360.helpers.CategoriaDaConta;
import br.com.marcielli.DigBank360.helpers.TipoDeCartao;
import br.com.marcielli.DigBank360.helpers.TipoDeConta;
import br.com.marcielli.DigBank360.helpers.TipoDeTransferencia;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@Table(name = "conta")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_conta", discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo( 
	    use = JsonTypeInfo.Id.NAME,
	    include = JsonTypeInfo.As.PROPERTY, 
	    property = "tipo_conta")  
	@JsonSubTypes({
	    @JsonSubTypes.Type(value = Corrente.class, name = "CORRENTE"),
	    @JsonSubTypes.Type(value = Poupanca.class, name = "POUPANCA")
	}) 

public abstract class Conta {
	
	
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Version
	private Long version;
	
	@ManyToOne(cascade = CascadeType.ALL) //tirei o all para testar
	@JoinColumn(name = "cliente_id")
	@JsonBackReference
	private Cliente cliente;	
	
	@Enumerated(EnumType.STRING)
	@JsonProperty("tipo_conta")	
	private TipoDeConta tipoDeConta;
	
	@Column(name = "categoria_conta")
	private CategoriaDaConta categoriaDaConta;
	
	@Column(name = "tipo_cartao")
	private TipoDeCartao tipoDeCartao;
	
	@Column(name = "tipo_transferencia")
	private TipoDeTransferencia tipoDeTransferencia;
	
	
	@Column(name = "saldo_conta")
	private Double saldoDaConta;	
	
	@Column(name = "numero_conta")
	private String numeroDaConta;
	
	
	public Conta() {}	
	
	
	
	public Conta(Long id, Long version, Cliente cliente, TipoDeConta tipoDeConta, CategoriaDaConta categoriaDaConta,
			TipoDeCartao tipoDeCartao, TipoDeTransferencia tipoDeTransferencia, Double saldoDaConta,
			String numeroDaConta) {
		super();
		this.id = id;
		this.version = version;
		this.cliente = cliente;
		this.tipoDeConta = tipoDeConta;
		this.categoriaDaConta = categoriaDaConta;
		this.tipoDeCartao = tipoDeCartao;
		this.tipoDeTransferencia = tipoDeTransferencia;
		this.saldoDaConta = saldoDaConta;
		this.numeroDaConta = numeroDaConta;
	}
	
	
	public Conta(Long id, Cliente cliente, TipoDeConta tipoDaConta, Double saldoDaConta, String numeroDaConta) {
		super();
		this.id = id;
		this.cliente = cliente;		
		this.tipoDeConta = tipoDaConta;
		this.saldoDaConta = saldoDaConta;
		this.numeroDaConta = numeroDaConta;
	}
	
	
	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}	

	public TipoDeConta getTipoDeConta() {
		return tipoDeConta;
	}

	public void setTipoDeConta(TipoDeConta tipoDeConta) {
		this.tipoDeConta = tipoDeConta;
	}

	public CategoriaDaConta getCategoriaDaConta() {
		return categoriaDaConta;
	}

	public void setCategoriaDaConta(CategoriaDaConta categoriaDaConta) {
		this.categoriaDaConta = categoriaDaConta;
	}

	public TipoDeCartao getTipoDeCartao() {
		return tipoDeCartao;
	}

	public void setTipoDeCartao(TipoDeCartao tipoDeCartao) {
		this.tipoDeCartao = tipoDeCartao;
	}

	public TipoDeTransferencia getTipoDeTransferencia() {
		return tipoDeTransferencia;
	}

	public void setTipoDeTransferencia(TipoDeTransferencia tipoDeTransferencia) {
		this.tipoDeTransferencia = tipoDeTransferencia;
	}

	public Double getSaldoDaConta() {
		return saldoDaConta;
	}

	public void setSaldoDaConta(Double saldoDaConta) {
		this.saldoDaConta = saldoDaConta;
	}

	public String getNumeroDaConta() {
		return numeroDaConta;
	}

	public void setNumeroDaConta(String numeroDaConta) {
		this.numeroDaConta = numeroDaConta;
	}

	public abstract Double exibirSaldo();	
	public abstract void enviarPix(Double valor);
	public abstract void receberPix(Double valor);

	@Override
	public String toString() {
		return "Conta [id=" + id + ", version=" + version + ", cliente=" + cliente + ", tipoDeConta=" + tipoDeConta
				+ ", categoriaDaConta=" + categoriaDaConta + ", tipoDeCartao=" + tipoDeCartao + ", tipoDeTransferencia="
				+ tipoDeTransferencia + ", saldoDaConta=" + saldoDaConta + ", numeroDaConta=" + numeroDaConta
				+ ", toString()=" + super.toString() + "]";
	}
	
	

}
