package co.edu.iudigital.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.iudigital.app.exception.ErrorDto;
import co.edu.iudigital.app.exception.NotFoundException;
import co.edu.iudigital.app.exception.RestException;
import co.edu.iudigital.app.model.Delito;
import co.edu.iudigital.app.repository.IDelitoRepository;
import co.edu.iudigital.app.service.iface.IDelitoService;
import co.edu.iudigital.app.util.ConstUtil;

@Service
public class DelitoServiceImpl implements IDelitoService{

	@Autowired
	private IDelitoRepository delitoRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Delito> findAll() throws RestException{
		List<Delito> usuarios = delitoRepository.findAll();
		if(usuarios == null) {
			throw new NotFoundException(ErrorDto.getErrorDto(HttpStatus.NOT_FOUND.getReasonPhrase(),
                    ConstUtil.MESSAGE_NOT_FOUND, HttpStatus.NOT_FOUND.value()));
		}
		//TODO: usuariosDto
		return usuarios;
	}

	@Override
	@Transactional(readOnly = true)
	public Delito findById(Long id) throws RestException{
		return delitoRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Delito save(Delito delito) throws RestException{
		return delitoRepository.save(delito);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		delitoRepository.deleteById(id);
	}

}