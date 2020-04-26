package com.example.os.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.os.api.model.Comentario;
import com.example.os.domain.exception.EntidadeNaoEncontradaException;
import com.example.os.domain.exception.NegocioException;
import com.example.os.domain.model.Cliente;
import com.example.os.domain.model.OrdemServico;
import com.example.os.domain.model.StatusOrdemServico;
import com.example.os.domain.repository.ClienteRepository;
import com.example.os.domain.repository.ComentarioRepository;
import com.example.os.domain.repository.OrdemServicoRepository;

@Service
public class GestaoOrdemServicoService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private OrdemServicoRepository ordemServicoRespository;
	
	@Autowired
	private ComentarioRepository comentarioRepository;

	public OrdemServico criar(OrdemServico ordemServico) {
		
		Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
				.orElseThrow(() -> new NegocioException("Cliente não encontrado"));
		ordemServico.setCliente(cliente);
		ordemServico.setStatus(StatusOrdemServico.ABERTA);
		ordemServico.setDataAbertura(OffsetDateTime.now());
		
		return ordemServicoRespository.save(ordemServico);
	}
	
	public void finalizar(Long ordemServicoId) {

		OrdemServico ordemServico = buscar(ordemServicoId);
		
		ordemServico.finalizar();
		
		ordemServicoRespository.save(ordemServico);
	}

	private OrdemServico buscar(Long ordemServicoId) {
		return ordemServicoRespository.findById(ordemServicoId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("OS não encontrada"));
	}
	
	
	public Comentario adicionarComentario(Long ordemServicoId, String descricao) {
		
		OrdemServico ordemServico = buscar(ordemServicoId);
		
		Comentario comentario = new Comentario();
		comentario.setDataEnvio(OffsetDateTime.now());
		comentario.setDescricao(descricao);
		comentario.setOrdemServico(ordemServico);
		
		return comentarioRepository.save(comentario);
	}
}
