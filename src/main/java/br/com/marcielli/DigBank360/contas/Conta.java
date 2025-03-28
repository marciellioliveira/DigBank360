package br.com.marcielli.DigBank360.contas;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.marcielli.DigBank360.clientes.Cliente;
import br.com.marcielli.DigBank360.helpers.CategoriaDaConta;
import br.com.marcielli.DigBank360.helpers.TipoDeConta;
import br.com.marcielli.DigBank360.helpers.TipoDeTransferencia;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_conta")
@NoArgsConstructor
@AllArgsConstructor
@Data
public abstract class Conta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(mappedBy = "conta")
	@JsonIgnoreProperties("conta")
	private Cliente cliente;
	
	//private String cpfDonoConta;
	private TipoDeConta tipoDeConta;
	private CategoriaDaConta categoriaDaConta;
	private float saldoDaConta;
	private String numeroDaConta;
	private TipoDeTransferencia tipoDeTransferencia;
		
	
	public abstract float exibirSaldo();	
	public abstract void enviarPix(float valor);
	public abstract void receberPix(float valor);
	
	@Override
	public String toString() {
		return "\nNúmero da Conta: "+numeroDaConta;
	}	

}
