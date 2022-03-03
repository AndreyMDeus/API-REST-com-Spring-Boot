package com.amd.apidio.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.amd.apidio.domain.enums.TipoCliente;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private String sobrenome;

    @Column(unique=true) /* Essa anotação do JPA não permite campos com mesmo conteúdo na tabela */
	private String email;

	@Column(nullable = false, unique = true)
	private String cpfOuCnpj;

	/* Internamente o TipoCliente vai ser armazenado como um tipo Inteiro mas quando exibido será como TipoCliente */
	@Column(nullable = false)
	private Integer tipo;
	
	/* Aqui o JSON protege a serialização ciclica, ou seja, o cliente pode serializar o endereço mas o endereço não pode serializar o cliente */
	/* Associação de cliente com endereço - o cliente tem vários endereços */
	@OneToMany(mappedBy = "cliente", cascade=CascadeType.ALL) /* O parâmertro cascade permite refletir nas entidades associadas alterações ou exclusões, para não permitir é só deixar sem esse parâmetro */
	private List<Endereco> enderecos = new ArrayList<>(); 
	
	/* Como telefones é uma classe fraca não é obrigatório criar uma classe então pode-se criar uma lista sem repetição */
	@ElementCollection
	@CollectionTable(name = "TELEFONE")
	private Set<String> telefones = new HashSet<>();
	
	/* Construtores da classe */
	
	public Cliente() {
	}

	public Cliente(Integer id, String nome, String sobrenome, String email, String cpfOuCnpj, TipoCliente tipo) {
		super();
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.tipo = (tipo==null) ? null : tipo.getCod();
	}

	/* Getters e Setters */
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public TipoCliente getTipo() {
		return TipoCliente.toEnum(tipo);
	}

	public void setTipo(TipoCliente tipo) {
		this.tipo = tipo.getCod();
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Set<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(id, other.id);
	}

	
	

	
}
