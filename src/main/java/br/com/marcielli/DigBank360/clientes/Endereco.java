package br.com.marcielli.DigBank360.clientes;


import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@Table(name = "tb_endereco")
public class Endereco {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Version
	@Column(name = "version")
	private Long version;
	
	private String cep;
	private String cidade;
	private String estado;
	private String rua;
	private String numero;
	private String bairro;
	private String complemento;	
	
	@OneToOne(mappedBy = "endereco")
	//@JsonIgnoreProperties("endereco")
	@JsonBackReference
	private Cliente cliente;
	
	
	
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



	public String getCep() {
		return cep;
	}



	public void setCep(String cep) {
		this.cep = cep;
	}



	public String getCidade() {
		return cidade;
	}



	public void setCidade(String cidade) {
		this.cidade = cidade;
	}



	public String getEstado() {
		return estado;
	}



	public void setEstado(String estado) {
		this.estado = estado;
	}



	public String getRua() {
		return rua;
	}



	public void setRua(String rua) {
		this.rua = rua;
	}



	public String getNumero() {
		return numero;
	}



	public void setNumero(String numero) {
		this.numero = numero;
	}



	public String getBairro() {
		return bairro;
	}



	public void setBairro(String bairro) {
		this.bairro = bairro;
	}



	public String getComplemento() {
		return complemento;
	}



	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}



	public Cliente getCliente() {
		return cliente;
	}



	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}



	@Override
	public String toString() {
		return "Rua "+getRua()+", n° "+getNumero()+", Bairro "+getBairro()+", Complemento "+getComplemento()+", Cidade "+getCidade()+"/"+getEstado()+", Cep "+getCep();
	}	
}
