package br.com.ucsal.meta.agil.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.ucsal.meta.agil.entity.CerimoniaEntity;
import br.com.ucsal.meta.agil.exception.NotFoundException;
import br.com.ucsal.meta.agil.repository.CerimoniaRepository;
import br.com.ucsal.meta.agil.util.MessageUtil;

@Service
public class CerimoniaService {

	@Autowired
	private CerimoniaRepository cerimoniaRepository;

	public List<CerimoniaEntity> getAllCerimonias() {
		return cerimoniaRepository.findAll();
	}

	public CerimoniaEntity save(CerimoniaEntity cerimonia) {
		Optional<CerimoniaEntity> find = cerimoniaRepository.findByNmCerimonia(cerimonia.getNmCerimonia());
		if (find.isPresent()) {
			throw new BusinessException(MessageUtil.FAIL_SAVE + MessageUtil.CERIMONIA_EXISTENTE);
		}
		return cerimoniaRepository.save(cerimonia);
	}

	public CerimoniaEntity atualiza(CerimoniaEntity cerimonia) {

		Optional<CerimoniaEntity> find = cerimoniaRepository.findById(cerimonia.getCdCerimonia());
		if (find.isPresent()) {
			find.get().setNmCerimonia(cerimonia.getNmCerimonia());
			find.get().setFlCerimonia(cerimonia.getFlCerimonia());
			CerimoniaEntity updated = cerimoniaRepository.save(find.get());
			return updated;
		}

		throw new NotFoundException(MessageUtil.CERIMONIA_NAO_ENCONTRADO);
	}

	public CerimoniaEntity deleta(Integer id) {

		Long cdCerimonia = Long.parseLong(id.toString());
		Optional<CerimoniaEntity> cerimonia = cerimoniaRepository.findById(cdCerimonia);
		if (cerimonia.isPresent()) {
			cerimoniaRepository.delete(cerimonia.get());
			return cerimonia.get();
		}

		throw new NotFoundException(MessageUtil.CERIMONIA_NAO_ENCONTRADO);
	}
}