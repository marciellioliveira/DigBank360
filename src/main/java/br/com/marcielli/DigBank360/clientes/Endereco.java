package br.com.marcielli.DigBank360.clientes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@Table(name = "tb_endereco")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Endereco {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String cep;
	private String cidade;
	private String estado;
	private String rua;
	private String numero;
	private String bairro;
	private String complemento;	
	
	@OneToOne(mappedBy = "endereco")
	@JsonIgnoreProperties("endereco")
	private Cliente cliente;
	
	@Override
	public String toString() {
		return "Rua "+getRua()+", n° "+getNumero()+", Bairro "+getBairro()+", Complemento "+getComplemento()+", Cidade "+getCidade()+"/"+getEstado()+", Cep "+getCep();
	}	
}
