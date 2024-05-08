package com.coopeuch.tareas.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coopeuch.tareas.exception.ResourceFoundException;
import com.coopeuch.tareas.exception.ResourceNotFoundException;
import com.coopeuch.tareas.helper.Constantes;
import com.coopeuch.tareas.helper.Respuesta;
import com.coopeuch.tareas.model.Tareas;
import com.coopeuch.tareas.service.ConsultaTareasService;
import com.coopeuch.tareas.service.RegistraTareasServices;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@RestController

@RequestMapping("/registro/tareas")
@Api(value = "Producto microservice", description = "registra borra y modifica la tabla Producto")

@CrossOrigin(origins = "*") //this line
public class TareaRegistrationController {
	@Autowired
	private RegistraTareasServices registraProductosServices;
	@Autowired
	private ConsultaTareasService consultaTareasService;
	   
	private static final Logger logger = LoggerFactory.getLogger(TareaRegistrationController.class);
	   
	/**
	 * Update user response entity.
	 *
	 * @param userId      the user id
	 * @param userDetails the user details
	 * @return the response entity
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	
	 @ApiOperation(value = "actualiza  un producto de acuerdo  a su  id del producto", notes = "Return clase Respuesta "
		 		+ " retorna el resultado en campo Msg, primero busca si existe el registro , si no existe manda el respectivo msg " ) 
	 @RequestMapping(value = "/update", method = RequestMethod.PUT,
     produces = MediaType.APPLICATION_JSON_VALUE)
	
	public Respuesta updateProducto(@Valid @RequestBody Tareas tarea) {
		 Respuesta response=new Respuesta();
		Tareas tareas = consultaTareasService.findTareas(tarea.getId());

		logger.info("update ");
		logger.info("producto " + tareas);
		try {
			if (tareas == null) {
				logger.debug("producto nulo");
				throw new ResourceNotFoundException(Constantes.msgNotFound);
			}


			tareas.setNombre(tarea.getNombre());
			tareas.setDescripcion(tarea.getDescripcion());

			registraProductosServices.update(tareas);

			response.setMsg(Constantes.update);
		} catch (ResourceNotFoundException e) {
			logger.error(e.getMessage());
			response.setMsg(Constantes.msgNotFound);
		} catch (Exception e) {
			response.setMsg(e.getMessage());
		}
		return response;
	}

	/**
	 * Update user response entity.
	 *
	 * @param producto the user id
	 * @return the response entity
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	 @ApiOperation(value = "guarda un registro de la tarea  en caso de que exista mandara el respectivo msg ", notes = "Return clase Respuesta "
		 		+ " retorna el resultado en campo Msg " ) 
		@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "Requestor-Type", exposedHeaders = "X-Get-Header")

		@RequestMapping(value = "/save", method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
	public Respuesta saveTarea(@Valid @RequestBody Tareas tarea) {
			logger.info("grabar tareas" );

		Tareas tareas = consultaTareasService.findTareas(tarea.getId());
		Respuesta response = new Respuesta();
		logger.info("In prod" + tareas);

		try {
			if (tareas != null) {
				logger.debug("producto existe");
				throw new ResourceFoundException(Constantes.msgFound);
			}
			registraProductosServices.update(tarea);

			response.setMsg(Constantes.insert);
		} catch (ResourceFoundException e) {
			logger.error(e.getMessage());
			response.setMsg(Constantes.msgFound);
		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setMsg(e.getMessage());
		}
		return response;
	}

	
	

	/**
	 * Delete user map.
	 *
	 * @param Id the producto id
	 * @return object  Respuesta
	 * @throws Exception the exception
	 */
	 @ApiOperation(value = "borra un producto de acuerdo  a su  id del producto", notes = "Return clase Respuesta "
		 		+ " retorna el resultado en campo Msg " ) 

		@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "Requestor-Type", exposedHeaders = "X-Get-Header")

	 @DeleteMapping("/delete/{id}")
	public Respuesta deleteProducto(@PathVariable(value = "id") Long id)  {
		 
			logger.info("deleteProducto" + id);

		Respuesta response = new Respuesta();
		Tareas tarea = consultaTareasService.findTareas(id);
		logger.info("In tarea" + tarea);

		try {
			if (tarea == null) {
				throw new ResourceFoundException(Constantes.msgFound);
			}
			registraProductosServices.delete(tarea);

			response.setMsg(Constantes.delete);
		} catch (ResourceFoundException e) {
			logger.error(e.getMessage());
			response.setMsg(Constantes.msgNotFound);
		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setMsg(e.getMessage());
		}
		return response;

	}
	
	  

}