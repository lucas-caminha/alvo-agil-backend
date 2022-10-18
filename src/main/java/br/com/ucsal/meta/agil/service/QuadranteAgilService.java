package br.com.ucsal.meta.agil.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.ucsal.meta.agil.entity.QuadranteAgilEntity;
import br.com.ucsal.meta.agil.exception.BusinessException;
import br.com.ucsal.meta.agil.exception.NotFoundException;
import br.com.ucsal.meta.agil.repository.QuadranteAgilRepository;
import br.com.ucsal.meta.agil.util.MessageUtil;

@Service
public class QuadranteAgilService {

	@Autowired
	private QuadranteAgilRepository quadranteAgilRepository;

	public List<QuadranteAgilEntity> getAllQuadranteAgeis() {
		return quadranteAgilRepository.findAll();
	}

	public QuadranteAgilEntity save(QuadranteAgilEntity quadranteAgil) {
		Optional<QuadranteAgilEntity> find = quadranteAgilRepository.findByNmQuadrante(quadranteAgil.getNmQuadrante());
		if (find.isPresent()) {
			throw new BusinessException(MessageUtil.FAIL_SAVE + MessageUtil.QUADRANTEAGIL_EXISTENTE);
		}
		return quadranteAgilRepository.save(quadranteAgil);
	}

	public QuadranteAgilEntity atualiza(QuadranteAgilEntity quadranteAgil) {

		Optional<QuadranteAgilEntity> find = quadranteAgilRepository.findById(quadranteAgil.getCdQuadrante());
		if (find.isPresent()) {
			find.get().setNmQuadrante(quadranteAgil.getNmQuadrante());
			find.get().setFlQuadrante(quadranteAgil.getFlQuadrante());
			find.get().setPerguntas(quadranteAgil.getPerguntas());
			QuadranteAgilEntity updated = quadranteAgilRepository.save(find.get());
			return updated;
		}

		throw new NotFoundException(MessageUtil.QUADRANTEAGIL_NAO_ENCONTRADO);
	}

	public QuadranteAgilEntity deleta(Integer id) {

		Long cdQuadranteAgil = Long.parseLong(id.toString());
		Optional<QuadranteAgilEntity> quadranteAgil = quadranteAgilRepository.findById(cdQuadranteAgil);
		if (quadranteAgil.isPresent()) {
			quadranteAgilRepository.delete(quadranteAgil.get());
			return quadranteAgil.get();
		}

		throw new NotFoundException(MessageUtil.QUADRANTEAGIL_NAO_ENCONTRADO);
	}

	public QuadranteAgilEntity getQuadranteAgil(Long cdQuadranteAgil) {
		Optional<QuadranteAgilEntity> quadranteAgil = quadranteAgilRepository.findById(cdQuadranteAgil);
		if(quadranteAgil.isEmpty()) {
			return null;
		}
		return quadranteAgil.get();
	}
}