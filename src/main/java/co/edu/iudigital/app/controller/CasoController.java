package co.edu.iudigital.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.edu.iudigital.app.dto.CasoDto;
import co.edu.iudigital.app.exception.BadRequestException;
import co.edu.iudigital.app.exception.ErrorDto;
import co.edu.iudigital.app.exception.InternalServerErrorException;
import co.edu.iudigital.app.exception.RestException;
import co.edu.iudigital.app.model.Caso;
import co.edu.iudigital.app.service.iface.ICasoService;
import co.edu.iudigital.app.util.ConstUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/casos")
@Api(value = "/casos", tags = {"Casos"})
@SwaggerDefinition(tags = {
		@Tag(name = "Casos", description = "Gestion API Casos")
})
@Slf4j
public class CasoController {
	
	private static final Logger log = LoggerFactory.getLogger(CasoController.class);
	
	@Autowired
	private ICasoService casoService;
	
	
	@ApiOperation(value = "Obtiene todos casos",
			response = Caso.class,
			responseContainer = "List",
			produces = "application/json",
			httpMethod = "GET")
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<CasoDto> index() throws RestException{
		return casoService.findAll();
	}
	
    @ApiOperation(value = "Realiza la creación de un caso",
            produces = "application/json",
            httpMethod = "POST")
    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public Caso create(@RequestBody Caso caso) throws RestException{
        try {
            return casoService.save(caso);
        }catch (BadRequestException ex){
            throw ex;
        }catch (Exception ex){
            log.error("Error Creando...", ex);
            throw new InternalServerErrorException(ErrorDto.getErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                    ConstUtil.MESSAGE_GENERAL,
                    HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
    }
}
