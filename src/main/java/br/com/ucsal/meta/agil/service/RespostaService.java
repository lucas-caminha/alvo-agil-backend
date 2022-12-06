package br.com.ucsal.meta.agil.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ucsal.meta.agil.entity.AvaliacaoEntity;
import br.com.ucsal.meta.agil.entity.PerguntaEntity;
import br.com.ucsal.meta.agil.entity.RespostaEntity;
import br.com.ucsal.meta.agil.repository.RespostaRepository;

@Service
public class RespostaService {

	@Autowired
	private RespostaRepository respostaRepository;
	
	public RespostaEntity save(RespostaEntity resposta) {
		return respostaRepository.save(resposta);
	}
	
	public List<RespostaEntity> saveAll(List<RespostaEntity> respostas) {
		return respostaRepository.saveAll(respostas);
	}
	
	public void delete(Long cdResposta) {
		respostaRepository.deleteById(cdResposta);
	}
	
	public List<RespostaEntity> getAllPerguntas() {
		return respostaRepository.findAll();
	}
	
	public List<RespostaEntity> buscaRespostasByAvaliacao(AvaliacaoEntity avaliacao) {
		Optional<List<RespostaEntity>> respostas = respostaRepository.findByAvaliacao(avaliacao);
		return respostas.get();
	}
	
	public RespostaEntity buscaRespostasByAvaliacaoAndPergunta(AvaliacaoEntity avaliacao, PerguntaEntity pergunta) {
		Optional<RespostaEntity> resposta = respostaRepository.findByAvaliacaoAndPergunta(avaliacao, pergunta);
		return resposta.get();
	}
}
