package br.com.ucsal.meta.agil.dto;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class UsuarioDTO {
	
	private Long id;
	private String email;
	private String nome;
	private String senha;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public UsernamePasswordAuthenticationToken toToken() {
		return new UsernamePasswordAuthenticationToken(this.nome, this.senha);
	}

}
