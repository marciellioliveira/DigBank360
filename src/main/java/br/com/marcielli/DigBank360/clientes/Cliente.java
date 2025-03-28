package br.com.marcielli.DigBank360.clientes;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.marcielli.DigBank360.contas.Conta;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_cliente")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	
	private String nome;
	
	private Long cpf;
	
	private LocalDate dataNascimento;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_id")
	@JsonIgnoreProperties("cliente")
	private Endereco endereco;	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "conta_id")
	@JsonIgnoreProperties("cliente")
	private Conta conta;
	
	@Override
	public String toString() {		
		return ""+getId()+"\nNome: "+getNome()+"\nCpf: "+getCpf()+"\nNasc: "+getDataNascimento()+"\nEndereço: "+getEndereco();
	}
}
