package co.edu.iudigital.app.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.iudigital.app.exception.ErrorDto;
import co.edu.iudigital.app.exception.InternalServerErrorException;
import co.edu.iudigital.app.exception.RestException;
import co.edu.iudigital.app.model.Role;
import co.edu.iudigital.app.model.Usuario;
import co.edu.iudigital.app.repository.IUsuarioRepository;
import co.edu.iudigital.app.service.iface.IUsuarioService;
import co.edu.iudigital.app.util.ConstUtil;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UsuarioService implements IUsuarioService{

	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	@Override
	@Transactional(readOnly = true)// por ser consulta, readOnly
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByUsername(username);
		if(usuario == null) {
			//log.error("Error de login, no existe usuario: "+ email);
			throw new UsernameNotFoundException("Error de login, no existe usuario: "+ username);
		}
		List<GrantedAuthority> authorities = new ArrayList<>();
		for(Role role: usuario.getRoles()) {
			GrantedAuthority authority = new SimpleGrantedAuthority(role.getNombre());
			System.out.println("Rol: " + authority.getAuthority());
			authorities.add(authority);	
		}					
		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true,authorities);
	}

	@Override
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario findById(Long id) throws RestException{
		return usuarioRepository.findById(id).get();
	}

	@Override
	public Usuario save(Usuario usuario) throws RestException{
    	if(usuario == null) {
    		throw new InternalServerErrorException(ErrorDto.getErrorDto(HttpStatus.BAD_REQUEST.getReasonPhrase(),
                    ConstUtil.MESSAGE_ERROR_DATA,
                    HttpStatus.INTERNAL_SERVER_ERROR.value()));
    	}
		return usuarioRepository.save(usuario);
	}
	
}