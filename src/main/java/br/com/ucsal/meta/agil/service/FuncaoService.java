package br.com.ucsal.meta.agil.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ucsal.meta.agil.entity.FuncaoEntity;
import br.com.ucsal.meta.agil.exception.BusinessException;
import br.com.ucsal.meta.agil.exception.NotFoundException;
import br.com.ucsal.meta.agil.repository.FuncaoRepository;
import br.com.ucsal.meta.agil.util.MessageUtil;

@Service
public class FuncaoService {
	
	@Autowired
	private FuncaoRepository funcaoRepository;

	public List<FuncaoEntity> getAllFuncoes() {
		return funcaoRepository.findAll();
	}
	
	public FuncaoEntity save(FuncaoEntity entity) {
		Optional<List<FuncaoEntity>> find = funcaoRepository.findByNmFuncao(entity.getNmFuncao());	
		if(!find.get().isEmpty()) {
			throw new BusinessException(MessageUtil.FAIL_SAVE + MessageUtil.FUNCAO_EXISTENTE);
		}
		return funcaoRepository.save(entity);
	}

	public FuncaoEntity atualiza(FuncaoEntity entity) {
		Optional<FuncaoEntity> find = funcaoRepository.findById(entity.getCdFuncao());	
		if(find.isPresent()) {
			find.get().setNmFuncao(entity.getNmFuncao());
			find.get().setFlFuncao(entity.getFlFuncao());
			FuncaoEntity updated = 
					funcaoRepository.save(find.get());
			return updated;
		}
		
		throw new NotFoundException(MessageUtil.FUNCAO_NAO_ENCONTRADA);
	}

	public FuncaoEntity deleta(Integer id) {
		Long cdFuncao = Long.parseLong(id.toString());
		Optional<FuncaoEntity> funcao = funcaoRepository.findById(cdFuncao);
		if(funcao.isPresent()) {
			funcaoRepository.delete(funcao.get());
			return funcao.get();
		}
		
		throw new NotFoundException(MessageUtil.FUNCAO_NAO_ENCONTRADA);
	}



}
