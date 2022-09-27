package br.com.ucsal.meta.agil.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ucsal.meta.agil.entity.FrameworkEntity;
import br.com.ucsal.meta.agil.exception.BusinessException;
import br.com.ucsal.meta.agil.exception.NotFoundException;
import br.com.ucsal.meta.agil.repository.FrameworkRepository;
import br.com.ucsal.meta.agil.util.MessageUtil;

@Service
public class FrameworkService {

	@Autowired
	private FrameworkRepository frameworkRepository;
	
	public List<FrameworkEntity> getAllFrameworks() {
		return frameworkRepository.findAll();
	}

	public FrameworkEntity save(FrameworkEntity framework) {
		Optional<FrameworkEntity> find = frameworkRepository.findByNmFramework(framework.getNmFramework());	
		if(find.isPresent()) {
			throw new BusinessException(MessageUtil.FAIL_SAVE + MessageUtil.FRAMEWORK_EXISTENTE);
		}
		return frameworkRepository.save(framework);
	}

	public FrameworkEntity atualiza(FrameworkEntity framework) {
		
		Optional<FrameworkEntity> find = frameworkRepository.findById(framework.getCdFramework());	
		if(find.isPresent()) {
			find.get().setNmFramework(framework.getNmFramework());
			find.get().setFlFramework(framework.getFlFramework());
			FrameworkEntity updated = frameworkRepository.save(find.get());
			return updated;
		}
		
		throw new NotFoundException(MessageUtil.FRAMEWORK_NAO_ENCONTRADO);
	}

	public FrameworkEntity deleta(Integer id)  {
		
		Long cdFramework = Long.parseLong(id.toString());
		Optional<FrameworkEntity> framework = frameworkRepository.findById(cdFramework);
		if(framework.isPresent()) {
			frameworkRepository.delete(framework.get());
			return framework.get();
		}
		
		throw new NotFoundException(MessageUtil.FRAMEWORK_NAO_ENCONTRADO);
	}

	public FrameworkEntity getFramework(Long cdFramework) {
		Optional<FrameworkEntity> framework = frameworkRepository.findById(cdFramework);
		if(framework.isEmpty()) {
			return null;
		}
		return framework.get();
	}

	
	
}
