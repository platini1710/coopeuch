package com.bci.tareas.controllers;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bci.tareas.DTO.PhonesDTO;
import com.bci.tareas.DTO.UsuarioDTO;
import com.bci.tareas.exception.ResourceNotFoundException;
import com.bci.tareas.helper.Constantes;
import com.bci.tareas.helper.Respuesta;
import com.bci.tareas.model.Usuario;
import com.bci.tareas.service.ConsultaUsuarioService;
import com.bci.tareas.service.RegistraUsuarioServices;

import io.swagger.annotations.ApiOperation;

@RequestMapping("/registro/tareas")
@RestController
public class TareaRegistrationController {

	@Autowired
	private RegistraUsuarioServices registraUsuarioServices;
	@Autowired
	private ConsultaUsuarioService consultaUsuarioService;
	private static final Logger logger = LoggerFactory.getLogger(TareaRegistrationController.class);

	/**
	 * Insert user response entity.
	 *
	 * @param producto the user id
	 * @return the response entity
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@ApiOperation(value = "inserta un registro r", notes = "Return clase Respuesta ")
	@PostMapping(path = "/saveUsuario", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> saveUsuario(@RequestBody Usuario usuario) {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		Respuesta response = new Respuesta();
		try {
		Usuario user = consultaUsuarioService.findUsuario(usuario.getName());
		Date fecha=new Date();
		if (user != null) {
			usuario.setFecha_actualizacion(fecha);
			registraUsuarioServices.update(usuario);
			usuarioDTO.setFechaActualizacion(fecha.toString());
			usuarioDTO.setFechaCreacion(user.getFecha_creacion().toString());
			System.out.println("pwd2 ==" + usuario.getPassword());
		} else {
			usuario.setFecha_creacion(fecha);
			registraUsuarioServices.save(usuario);
			usuarioDTO.setFechaCreacion(fecha.toString());
			user = consultaUsuarioService.findUsuario(usuario.getName());
		}

		
		user = consultaUsuarioService.findUsuario(usuario.getName());		
		usuarioDTO.setPassword(usuario.getPassword());
		usuarioDTO.setEmail(usuario.getEmail());
		usuarioDTO.setName(usuario.getName());
		PhonesDTO phonesDTO = new PhonesDTO(user.getId(), user.getPhone().getNumero(), user.getPhone().getContrycode());
		usuarioDTO.setPhonesDTO(phonesDTO);
		usuarioDTO.setId(user.getId());
		response.setMensaje(Constantes.insert);
		}catch (Exception nre){
			response.setMensaje(Constantes.msgError);
			return new ResponseEntity<>(response,  HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(usuarioDTO,  HttpStatus.OK);
	}

	@RequestMapping("/users")
	public @ResponseBody String getUsers() {
		return "{\"users\":[{\"firstname\":\"Richard\", \"lastname\":\"Feynman\"},"
				+ "{\"firstname\":\"Marie\",\"lastname\":\"Curie\"}]}";
	}
}