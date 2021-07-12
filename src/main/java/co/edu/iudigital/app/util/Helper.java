package co.edu.iudigital.app.util;

import java.util.List;

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
        //uDto.setName(usuario.getName());
        return uDto;
    }
}
