package co.edu.iudigital.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.edu.iudigital.app.exception.RestException;
import co.edu.iudigital.app.model.Delito;
import co.edu.iudigital.app.service.iface.IDelitoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/delitos")
@CrossOrigin(origins = "*", 
	methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@Api(value = "/delitos", tags = {"Delitos"})
@SwaggerDefinition(tags = {
		@Tag(name = "Delitos", description = "Gestion API Delitos")
})
@Slf4j
public class DelitoController {
	
	@Autowired
	private IDelitoService delitoService;
	
	@ApiOperation(value = "Obtiene todos delitos",
				response = Delito.class,
				responseContainer = "List",
				produces = "application/json",
				httpMethod = "GET")
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<Delito> index() throws RestException{
		return delitoService.findAll();
	}
	
	@ApiOperation(value = "Obtiene delito por id",
			response = Delito.class,
			produces = "application/json",
			httpMethod = "GET")
	@GetMapping("/delito/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Delito show(@PathVariable Long id) throws RestException {
		return delitoService.findById(id);
	}
}
