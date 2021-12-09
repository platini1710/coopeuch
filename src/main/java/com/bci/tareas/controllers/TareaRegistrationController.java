package com.bci.tareas.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

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
import com.bci.tareas.helper.Validacion;
import com.bci.tareas.model.Phones;
import com.bci.tareas.model.Usuario;
import com.bci.tareas.security.TokenAuthenticationService;
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
	@ApiOperation(value = "inserta un registro  se valida email es validao,si existe ya email anteriormente  y  si existe el usuario", notes = "Return clase Respuesta ")
	@PostMapping(path = "/saveUsuario", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> saveUsuario(@RequestBody Usuario usuario,HttpServletRequest  request) {
        String token = request.getHeader("Authorization");
 
      

		UsuarioDTO usuarioDTO = new UsuarioDTO();
		Respuesta response = new Respuesta();
		if (!Validacion.validaEmail(usuario.getEmail())) {
			return new ResponseEntity<>("Email invalido ",  HttpStatus.INTERNAL_SERVER_ERROR);
		}
		try {
			Usuario user = consultaUsuarioService.findUsuario(usuario.getName());

		Usuario email = consultaUsuarioService.findUsuarioEmail(usuario.getName(),usuario.getEmail());

		Date fecha=new Date();
		if (email != null) {
			logger.info("email de  usuario ya existe == {}" , email);	
			return new ResponseEntity<>("Email ya existe ",  HttpStatus.INTERNAL_SERVER_ERROR);
		}
		else if (user != null) {

			logger.info("id " +user.getId());
			user.setEmail(usuario.getEmail());

			user.setFecha_actualizacion(fecha);
		//	user.setFecha_creacion(user.getFecha_creacion().toString());//
			user.setPassword(usuario.getPassword());
			user.setToken( token);
			Phones phones =user.getPhone();
			phones.setCitycode(usuario.getPhone().getCitycode());
			phones.setContrycode(usuario.getPhone().getContrycode());
			phones.setNumero(usuario.getPhone().getNumero());
			user.setPhone(phones);
			registraUsuarioServices.update(user);
			logger.info("update usuario == 1 ");

		} else {
			 user =new Usuario();
			 int id=consultaUsuarioService.findMaxid() +1;
			 user.setId(id);
			logger.info("id ===  " + id );
			user.setFecha_creacion(fecha);

			user.setPassword(usuario.getPassword());
			user.setName(usuario.getName());
			user.setFecha_creacion(fecha);
			user.setFecha_actualizacion(fecha);
			user.setEmail(usuario.getEmail());
	
			user.setToken( token);
			Phones phones =new Phones();
			phones.setCitycode(usuario.getPhone().getCitycode());
			phones.setContrycode(usuario.getPhone().getContrycode());
			phones.setNumero(usuario.getPhone().getNumero());
			phones.setId(id);
			user.setPhone(phones);

			registraUsuarioServices.save(user);

		}

		Usuario userIngresado =registraUsuarioServices.getUsuario(user.getId());
		usuarioDTO.setEmail(userIngresado.getEmail());
		usuarioDTO.setName(userIngresado.getName());			
		usuarioDTO.setFechaActualizacion(userIngresado.getFecha_actualizacion().toString());
		usuarioDTO.setFechaCreacion(userIngresado.getFecha_creacion().toString());
		usuarioDTO.setToken(token);
		usuarioDTO.setPassword(userIngresado.getPassword());
	
		if (userIngresado.getPhone()!=null) {
			PhonesDTO phone=new PhonesDTO();
			logger.info("entre 1 " +userIngresado.getPhone().getCitycode() );
			phone.setCitycode(userIngresado.getPhone().getCitycode()   );
			phone.setNumero(userIngresado.getPhone().getNumero()   );
			phone.setContrycode(userIngresado.getPhone().getContrycode());

			usuarioDTO.setPhonesDTO(phone);
		}
		logger.info("8" );
		response.setMensaje(Constantes.insert);
		}catch (Exception nre){
			logger.info("8"  +nre.getMessage());
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