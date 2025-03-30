package br.com.marcielli.DigBank360.clientes;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.processing.Pattern;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.marcielli.DigBank360.contas.Conta;
import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_cliente")
//@NoArgsConstructor
//@AllArgsConstructor
@Data
public class Cliente {
	
	@Id
	@Nonnull
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	
	private String nome;	
	
	@Nonnull
	private Long cpf;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dataNascimento;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_id")
	@JsonIgnoreProperties("cliente")
	private Endereco endereco;		
	
	@OneToMany(cascade = CascadeType.ALL) //One Cliente para ToMany Muitas contas
	@JoinColumn(name = "contas_id")
	@JsonIgnoreProperties("cliente")
	private List<Conta> contas;
	
	public Cliente(Long id, String nome, Long cpf, LocalDate dataNascimento, Endereco endereco, List<Conta> contas) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.endereco = endereco;
		this.contas = contas;
	}
	
	public Cliente() {}
	
	
	//@OneToOne(cascade = CascadeType.ALL)	
//	@JoinColumn(name = "conta_id")
//	@JsonIgnoreProperties("cliente")
	
	
	
	@Override
	public String toString() {		
		return ""+getId()+"\nNome: "+getNome()+"\nCpf: "+getCpf()+"\nNasc: "+getDataNascimento()+"\nEndereço: "+getEndereco();
	}
}
