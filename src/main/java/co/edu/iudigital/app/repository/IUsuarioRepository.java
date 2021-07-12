package co.edu.iudigital.app.repository;

import org.springframework.data.repository.CrudRepository;

import co.edu.iudigital.app.model.Usuario;

public interface IUsuarioRepository extends CrudRepository<Usuario, Long>{
	
	public Usuario findByUsername(String username);
}
