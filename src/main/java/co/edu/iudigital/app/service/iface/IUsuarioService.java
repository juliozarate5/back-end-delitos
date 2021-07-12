package co.edu.iudigital.app.service.iface;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import co.edu.iudigital.app.exception.RestException;
import co.edu.iudigital.app.model.Usuario;

public interface IUsuarioService extends UserDetailsService{
	
	public List<Usuario> findAll() throws RestException;
	
	public Usuario findById(Long id) throws RestException;
	
	public Usuario save(Usuario usuario) throws RestException;
}
