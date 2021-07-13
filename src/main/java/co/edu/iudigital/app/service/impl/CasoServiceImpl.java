package co.edu.iudigital.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.iudigital.app.exception.RestException;
import co.edu.iudigital.app.model.Caso;
import co.edu.iudigital.app.repository.ICasoRepository;
import co.edu.iudigital.app.service.iface.ICasoService;

@Service
public class CasoServiceImpl implements ICasoService{

	@Autowired
	private ICasoRepository casoRepository;
	
	@Override
	public List<Caso> findAll() throws RestException {
		return casoRepository.findAll();
	}

	@Override
	public Caso save(Caso caso) throws RestException {
		return casoRepository.save(caso);
	}

	@Override
	public Boolean visible(Boolean visible, Long id) throws RestException {
		return casoRepository.setVisible(visible, id);
	}

	@Override
	public Caso findById(Long id) throws RestException {
		return casoRepository.findById(id).get();
	}

}
