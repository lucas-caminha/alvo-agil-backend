package br.com.ucsal.meta.agil.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import br.com.ucsal.meta.agil.view.AvaliacaoNotaView;


@org.springframework.stereotype.Repository
public interface AvaliacaoNotaViewRepository extends Repository<AvaliacaoNotaView, Long> {

	List<AvaliacaoNotaView> findByCdavaliacao(Long cdavaliacao);
	
}
