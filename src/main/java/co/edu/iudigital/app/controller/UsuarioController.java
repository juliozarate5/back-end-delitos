package co.edu.iudigital.app.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import co.edu.iudigital.app.exception.BadRequestException;
import co.edu.iudigital.app.exception.ErrorDto;
import co.edu.iudigital.app.exception.InternalServerErrorException;
import co.edu.iudigital.app.exception.RestException;
import co.edu.iudigital.app.model.Usuario;
import co.edu.iudigital.app.service.iface.IEmailService;
import co.edu.iudigital.app.service.iface.IUsuarioService;
import co.edu.iudigital.app.util.ConstUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/usuarios")
@Api(value = "/usuarios", tags = {"Usuarios"})
@SwaggerDefinition(tags = {
		@Tag(name = "Usuarios", description = "Gestion API Usuarios")
})
@Slf4j
public class UsuarioController {
	
	private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private IEmailService emailService;
	
    @ApiOperation(value = "Realiza la creación de un nuevo usuario en el sistema",
            produces = "application/json",
            httpMethod = "POST")
    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario create(@Valid @RequestBody Usuario usuario) throws RestException{
        try {
        	Usuario usuarioFind = usuarioService.findByUsername(usuario.getUsername());
        	if(usuarioFind != null) {
                throw new BadRequestException(ErrorDto.getErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                        ConstUtil.MESSAGE_ALREADY,
                        HttpStatus.INTERNAL_SERVER_ERROR.value()));
        	}
        	String mensaje = "Su usuario: "+usuario.getUsername()+"; password: "+usuario.getPassword();
        	String asunto = "Registro en HelmeIUD";
        	if(usuario.getPassword() != null) {
        		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        	}
        	emailService.sendEmail(mensaje, usuario.getUsername(), asunto);
            return usuarioService.save(usuario);
        }catch (BadRequestException ex){
            throw ex;
        }catch (Exception ex){
            log.error("Error Creando", ex);
            throw new InternalServerErrorException(ErrorDto.getErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                    ConstUtil.MESSAGE_GENERAL,
                    HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
    }
    
	/*@ApiOperation(value = "Obtiene usuario por username",
			response = UsuarioDto.class,
			produces = "application/json",
			httpMethod = "GET")
	@GetMapping("/usuario/{username}")
	@ResponseStatus(code = HttpStatus.OK)
	public Usuario show(@PathVariable String username) throws RestException {
		return usuarioService.findByUsername(username);
	}*/
    
	@PostMapping("/upload")
	public ResponseEntity<?> upload(@RequestParam("image") MultipartFile image, @RequestParam Long id) 
			throws RestException{
		Map<String, Object> response = new HashMap<>();
		Usuario usuario = usuarioService.findById(id);
		if(!image.isEmpty()) {
			String nombreImage = image.getOriginalFilename();
			Path path = Paths.get("uploads").resolve(nombreImage).toAbsolutePath();
			try {
				Files.copy(image.getInputStream(), path);
			}catch (IOException e) {
				response.put("Error", e.getMessage().concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			usuario.setImage(nombreImage);
			usuarioService.save(usuario);
			response.put("usuario", usuario);
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
}
