package co.edu.iudigital.app.util;

import java.util.List;
import java.util.stream.Collectors;

import co.edu.iudigital.app.dto.UsuarioDto;
import co.edu.iudigital.app.model.Usuario;


/**
 * Utilidad o Helper para métodos transversales en la aplicación
 * @author Julio Martínez
 */
public interface Helper {

    /**
     * Recibe por referencia una instancia de List para mapear su resultado al UsuarioDto
     */
    public static void setMapValuesClient(List<Usuario> usuarios, List<UsuarioDto> usuariosDto){
    	usuarios.stream().map(usuario -> {
          UsuarioDto cDto = getMapValuesClient(usuario);
          return cDto;
        }).forEach(cDto -> {
        	usuariosDto.add(cDto);
        });
    }

    public static UsuarioDto getMapValuesClient(Usuario usuario){
    	UsuarioDto uDto = new UsuarioDto();
    	uDto.setId(usuario.getId());
    	uDto.setNombre(usuario.getNombre());
    	uDto.setApellido(usuario.getApellido());
    	uDto.setFechaNacimiento(usuario.getFechaNacimiento());
    	uDto.setImage(usuario.getImage());
    	uDto.setRoles(usuario.getRoles()
    			.stream().map(r -> r.getNombre())
    			.collect(Collectors.toList())
    			);
    	uDto.setUsername(usuario.getUsername());

    	return uDto;
    }
    
    /*public static UsuarioDto toUsuarioDto(Usuario usuario) {
    	UsuarioDto uDto = new UsuarioDto();
    	uDto.setId(usuario.getId());
    	uDto.setNombre(usuario.getNombre());
    	uDto.setApellido(usuario.getApellido());
    	uDto.setFechaNacimiento(usuario.getFechaNacimiento());
    	uDto.setImage(usuario.getImage());
    	uDto.setRoles(usuario.getRoles()
    			.stream().map(r -> r.getNombre())
    			.collect(Collectors.toList())
    			);
    	uDto.setUsername(usuario.getUsername());

    	return uDto;
    }*/
}
