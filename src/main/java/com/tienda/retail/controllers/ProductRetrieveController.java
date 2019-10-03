package com.tienda.retail.controllers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tienda.retail.exception.ResourceNotFoundException;
import com.tienda.retail.helper.Respuesta;
import com.tienda.retail.model.Productos;
import com.tienda.retail.service.ConsultaProductosService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

@Api(value = "ProductRetrieveController microservice", description = "This API consulta todo del producto")
@RequestMapping("/consulta")
@RestController

public class ProductRetrieveController {
	@ApiModelProperty(value = "clase controller ", required = true)
	@Autowired
	private ConsultaProductosService consultaProductosService;

	private static final Logger logger = LoggerFactory.getLogger(ProductRetrieveController.class);

	@ApiOperation(value = "Find todos los productosr", notes = "Return clase Respuesta "
			+ "resultado en ListProducto maneja su propias excepcion")
	@RequestMapping(method = RequestMethod.GET, value = "/allProductos")
	@ResponseBody
	public Respuesta getAllProductos() {
		logger.info("todo los productos");
		Respuesta response = new Respuesta();
		try {
			response.setListProducto(consultaProductosService.allProductos());
			response.setMsg("Exitoso");
			;
			return response;
		} catch (Exception e) {
			response.setMsg(e.getMessage());
		}

		return response;
	}

	/**
	 * Gets users by id.
	 *
	 * @param userId the user id
	 * @return the users by id
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@ApiOperation(value = "Find un producto por  id del producto", notes = "Return clase Respuesta "
			+ "resultado en campoProducto maneja su propias excepcion")
	@GetMapping("/producto/{id}")
	public Respuesta getProductoById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
		Productos producto = null;
		Respuesta response = new Respuesta();

		logger.info("id  <:::" + id);
		try {
			producto = consultaProductosService.findProducto(id);
			response.setProducto(producto);
			if (producto != null) {

				response.setMsg("Exitoso");
				;
			} else

				response.setMsg("producto no encontrado");
			;
		} catch (Exception e) {
			response.setMsg(e.getMessage());
		}

		return response;
	}
}