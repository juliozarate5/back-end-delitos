package co.edu.iudigital.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.edu.iudigital.app.dto.DelitoDto;
import co.edu.iudigital.app.exception.BadRequestException;
import co.edu.iudigital.app.exception.ErrorDto;
import co.edu.iudigital.app.exception.InternalServerErrorException;
import co.edu.iudigital.app.exception.NotFoundException;
import co.edu.iudigital.app.exception.RestException;
import co.edu.iudigital.app.model.Delito;
import co.edu.iudigital.app.service.iface.IDelitoService;
import co.edu.iudigital.app.util.ConstUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@RestController
@RequestMapping("/delitos")
@Api(value = "/delitos", tags = { "Delitos" })
@SwaggerDefinition(tags = { @Tag(name = "Delitos", description = "Gestion API Delitos") })
public class DelitoController {

	private static final Logger log = LoggerFactory.getLogger(DelitoController.class);

	@Autowired
	private IDelitoService delitoService;

	@ApiOperation(value = "Obtiene todos delitos", response = Delito.class, responseContainer = "List", produces = "application/json", httpMethod = "GET")
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<DelitoDto> index() throws RestException {
		return delitoService.findAll();
	}

	@ApiOperation(value = "Obtiene delito por id", response = Delito.class, produces = "application/json", httpMethod = "GET")
	@GetMapping("/delito/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Delito show(@PathVariable Long id) throws RestException {
		return delitoService.findById(id);
	}

	@ApiOperation(value = "Realiza la creación de un delito", produces = "application/json", httpMethod = "POST")
	// @Secured({"ROLE_ADMIN"}) TODO: revisar creación por el admin
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Delito create(@Valid @RequestBody Delito delito) throws RestException {
		try {
			return delitoService.save(delito);
		} catch (BadRequestException ex) {
			throw ex;
		} catch (Exception ex) {
			log.error("Error Creando...", ex);
			throw new InternalServerErrorException(
					ErrorDto.getErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), ConstUtil.MESSAGE_GENERAL,
							HttpStatus.INTERNAL_SERVER_ERROR.value()));
		}
	}

	@ApiOperation(value = "Elimina delito por id", response = Delito.class, produces = "application/json", httpMethod = "DELETE")
	@DeleteMapping("/delito/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public void delete(@PathVariable Long id) throws RestException {
		Delito delito = delitoService.findById(id);
		if (delito == null) {
			throw new NotFoundException(ErrorDto.getErrorDto(HttpStatus.NOT_FOUND.getReasonPhrase(),
					ConstUtil.MESSAGE_NOT_FOUND, HttpStatus.NOT_FOUND.value()));
		} else {
			delitoService.delete(id);
		}

	}
}
