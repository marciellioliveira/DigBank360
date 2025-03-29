package br.com.marcielli.DigBank360.contas;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import br.com.marcielli.DigBank360.clientes.Cliente;
import br.com.marcielli.DigBank360.contas.corrente.Corrente;
import br.com.marcielli.DigBank360.contas.poupanca.Poupanca;
import br.com.marcielli.DigBank360.helpers.CategoriaDaConta;
import br.com.marcielli.DigBank360.helpers.TipoDeCartao;
import br.com.marcielli.DigBank360.helpers.TipoDeConta;
import br.com.marcielli.DigBank360.helpers.TipoDeTransferencia;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_conta")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonTypeInfo( //Essa anotação permite que o Jackson saiba qual classe concreta ele tem que instanciar de acordo com o Json.
	    use = JsonTypeInfo.Id.NAME, //Vai identificar o tipo real de uma instância
	    include = JsonTypeInfo.As.PROPERTY, //Mapeia os tipos de contas para o valor correspondente ao que enviei no JSON
	    property = "tipoConta")  
	@JsonSubTypes({
	    @JsonSubTypes.Type(value = Corrente.class, name = "CORRENTE"),
	    @JsonSubTypes.Type(value = Poupanca.class, name = "POUPANCA")
	}) //Com essa @, eu posso usar a classe Conta abstrata no Controller porque configurei a hierarquia de classes 
public abstract class Conta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@OneToOne(mappedBy = "conta")
//	@JsonIgnoreProperties("conta")
	
	@ManyToOne //Many = Varias contas ToOne para um cliente
	private Cliente cliente;	
	
	@Column(name = "tipo_conta")
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
		
	
	public abstract Double exibirSaldo();	
	public abstract void enviarPix(Double valor);
	public abstract void receberPix(Double valor);
	
//	@Override
//	public String toString() {
//		return "\nNúmero da Conta: "+numeroDaConta;
//	}	

}
