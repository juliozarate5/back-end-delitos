package co.edu.iudigital.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.iudigital.app.model.Usuario;

@Repository
public interface IUsuarioRepository extends CrudRepository<Usuario, Long>{
	
	public Usuario findByUsername(String username);
}
