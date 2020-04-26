package com.example.os.api.model;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.lang.NonNull;

public class OrdemServicoInput {
	
	@NotBlank
	private String descricao;
	@NonNull
	private BigDecimal preco;
	@Valid
	@NotNull
	private ClienteIdInput cliente;
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	public ClienteIdInput getCliente() {
		return cliente;
	}
	public void setCliente(ClienteIdInput cliente) {
		this.cliente = cliente;
	}
	
	

}
