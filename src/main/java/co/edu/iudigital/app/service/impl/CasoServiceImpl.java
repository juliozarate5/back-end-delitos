package co.edu.iudigital.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import co.edu.iudigital.app.dto.CasoDto;
import co.edu.iudigital.app.exception.ErrorDto;
import co.edu.iudigital.app.exception.NotFoundException;
import co.edu.iudigital.app.exception.RestException;
import co.edu.iudigital.app.model.Caso;
import co.edu.iudigital.app.repository.ICasoRepository;
import co.edu.iudigital.app.service.iface.ICasoService;
import co.edu.iudigital.app.util.ConstUtil;

@Service
public class CasoServiceImpl implements ICasoService{

	@Autowired
	private ICasoRepository casoRepository;
	
	@Override
	public List<CasoDto> findAll() throws RestException {
		List<Caso> casos =  casoRepository.findAll();
		if(casos.isEmpty() || casos.size() <= 0) {
			throw new NotFoundException(ErrorDto.getErrorDto(HttpStatus.NOT_FOUND.getReasonPhrase(),
                    ConstUtil.MESSAGE_NOT_FOUND, HttpStatus.NOT_FOUND.value()));
		}
		
		List<CasoDto> casosDto = new ArrayList<>();
		for(Caso caso: casos) {
			CasoDto casoDto = new CasoDto();
			casoDto.setId(caso.getId());
			casoDto.setFechaHora(caso.getFechaHora());
			casoDto.setLatitud(caso.getLatitud());
			casoDto.setLongitud(caso.getLongitud());
			casoDto.setAltitud(caso.getAltitud());
			casoDto.setVisible(caso.getVisible());
			casoDto.setDescripcion(caso.getDescripcion());
			casoDto.setUrlMap(caso.getUrlMap());
			casoDto.setRmiUrl(caso.getRmiUrl());
			casoDto.setUsuarioId(caso.getUsuario().getId());
			casoDto.setNombre(caso.getUsuario().getNombre());
			casoDto.setImage(caso.getUsuario().getImage());
			casosDto.add(casoDto);
		}
		return casosDto;
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
