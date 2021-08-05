package co.edu.iudigital.app.service.iface;

import java.util.List;

import co.edu.iudigital.app.dto.DelitoDto;
import co.edu.iudigital.app.exception.RestException;
import co.edu.iudigital.app.model.Delito;

public interface IDelitoService {
	
	public List<DelitoDto> findAll() throws RestException;
	
	public Delito findById(Long id) throws RestException;
	
	public Delito save(Delito delito) throws RestException;
	
	public void delete(Long id);
}
