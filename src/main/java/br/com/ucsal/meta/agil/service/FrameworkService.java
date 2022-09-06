package br.com.ucsal.meta.agil.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ucsal.meta.agil.entity.FrameworkEntity;
import br.com.ucsal.meta.agil.repository.FrameworkRepository;

@Service
public class FrameworkService {

	@Autowired
	private FrameworkRepository frameworkRepository;
	
	public List<FrameworkEntity> getAllFrameworks() {
		return frameworkRepository.findAll();
	}

	public FrameworkEntity save(FrameworkEntity entity) {
		return frameworkRepository.save(entity);
	}

	public FrameworkEntity atualiza(FrameworkEntity entity) {
		return frameworkRepository.save(entity);
	}

	public void deleta(FrameworkEntity entity) {
		frameworkRepository.delete(entity);
	}

	
	
}
