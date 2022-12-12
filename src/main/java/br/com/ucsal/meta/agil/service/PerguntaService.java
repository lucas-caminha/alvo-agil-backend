package br.com.ucsal.meta.agil.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ucsal.meta.agil.entity.PerguntaEntity;
import br.com.ucsal.meta.agil.exception.NotFoundException;
import br.com.ucsal.meta.agil.repository.PerguntaRepository;
import br.com.ucsal.meta.agil.util.MessageUtil;

@Service
public class PerguntaService {
	
	@Autowired
	private PerguntaRepository perguntaRepository;

	public List<PerguntaEntity> getAllPerguntas() {
		return perguntaRepository.findAll();
	}

	public PerguntaEntity save(PerguntaEntity pergunta) {
		// Optional<List<PerguntaEntity>> find = perguntaRepository.findByDescPergunta(pergunta.getDescPergunta());	
		/**
		if(!find.get().isEmpty()) {
			throw new BusinessException(MessageUtil.FAIL_SAVE + MessageUtil.PERGUNTA_EXISTENTE);
		}
		**/
		return perguntaRepository.save(pergunta);
	}

	public PerguntaEntity atualiza(PerguntaEntity pergunta) {
		
		Optional<PerguntaEntity> find = perguntaRepository.findById(pergunta.getCdPergunta());	
		if(find.isPresent()) {		
			find.get().setFlPergunta(pergunta.getFlPergunta());
			find.get().setDescPergunta(pergunta.getDescPergunta());
			PerguntaEntity updated = perguntaRepository.save(find.get());
			return updated;
		}
		
		throw new NotFoundException(MessageUtil.PERGUNTA_NAO_ENCONTRADO);
	}

	public PerguntaEntity deleta(Integer id)  {
		
		Long cdPergunta = Long.parseLong(id.toString());
		Optional<PerguntaEntity> Pergunta = perguntaRepository.findById(cdPergunta);
		if(Pergunta.isPresent()) {
			perguntaRepository.delete(Pergunta.get());
			return Pergunta.get();
		}
		
		throw new NotFoundException(MessageUtil.PERGUNTA_NAO_ENCONTRADO);
	}

	public PerguntaEntity getPergunta(Long cdPergunta) {
		Optional<PerguntaEntity> pergunta = perguntaRepository.findById(cdPergunta);
		if(pergunta.isEmpty()) {
			return null;
		}
		return pergunta.get();
	}
	
	public PerguntaEntity buscaPerguntaPorId(Integer cdPergunta) {
		Long cdPerguntaL = Long.parseLong(cdPergunta.toString());
		Optional<PerguntaEntity> pergunta = perguntaRepository.findById(cdPerguntaL);
		if(pergunta.isPresent()) {
			return pergunta.get();
		}
		throw new NotFoundException(MessageUtil.PERGUNTA_NAO_ENCONTRADO);
	}



}
