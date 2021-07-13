package co.edu.iudigital.app.service.iface;

import java.util.List;

import co.edu.iudigital.app.exception.RestException;
import co.edu.iudigital.app.model.Caso;

public interface ICasoService {
	
	// consultar todos los casos
	public List<Caso> findAll() throws RestException;
	// crear un caso
	public Caso save(Caso caso) throws RestException;
	// inactivar caso
	public Boolean visible(Boolean visible, Long id) throws RestException;
	// consultar un caso por id
	public Caso findById(Long id) throws RestException;	
	
	
}
