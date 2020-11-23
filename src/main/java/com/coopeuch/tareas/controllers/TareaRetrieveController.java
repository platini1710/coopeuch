package com.coopeuch.tareas.controllers;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.coopeuch.tareas.exception.ResourceNotFoundException;
import com.coopeuch.tareas.model.Tareas;
import com.coopeuch.tareas.service.ConsultaTareasService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

@Api(value = "encontrar  tarea  guardada ", description = "This API consulta toas las tareas")

@RequestMapping("/consulta")
@RestController

public class TareaRetrieveController {
	@ApiModelProperty(value = "clase controller ", required = true)
	@Autowired
	private ConsultaTareasService consultaTareasService;

	private static final Logger logger = LoggerFactory.getLogger(TareaRetrieveController.class);

	@ApiOperation(value = "Find todos los productosr", notes = "Return clase Respuesta "
			+ "resultado en ListProducto maneja su propias excepcion")
	@RequestMapping(method = RequestMethod.GET, value = "/allProductos")
	@ResponseBody
	public ArrayList<Tareas> getAllProductos() {
		logger.info("todo los productos");
		ArrayList<Tareas> listAlltareas=new ArrayList<>();
		try {
			 listAlltareas=consultaTareasService.allTareas();
			

		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}

		return listAlltareas;
	}

	/**
	 * Gets users by id.
	 *
	 * @param userId the user id
	 * @return the users by id
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@ApiOperation(value = "Find un tarea por  id de la tarea", notes = "Return tarea "
			+ "resultado en campoProducto maneja su propias excepcion")
	@GetMapping("/tarea/{id}")
	public Tareas getProductoById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
		Tareas tarea = null;


		logger.info("id  <:::" + id);
		try {
			tarea = consultaTareasService.findTareas(id);
	
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}

		return tarea;
	}
}