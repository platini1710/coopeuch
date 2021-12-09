package com.bci.tareas.server.main;

import static org.mockito.Mockito.doReturn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.context.WebApplicationContext;

import com.bci.tareas.model.Usuario;
import com.bci.tareas.service.ConsultaUsuarioService;
import com.bci.tareas.service.RegistraUsuarioServices;

import io.swagger.annotations.ApiOperation;

import static org.mockito.ArgumentMatchers.any;
@SpringBootTest
public class ApplicationTests {
	
	@Autowired
	private RegistraUsuarioServices registraUsuario;



	private ConsultaUsuarioService consultaUsuarioService;



    @MockBean
    private RegistraUsuarioServices repository;

	@ApiOperation(value = "testea si el guardad funciona ", notes = "Return Integer ")
	@Test
	@DisplayName("gurdado exitosamente")
	void testFindById() {
		// Setup our mock repository
		Usuario user = new Usuario();
		user.setId(0);
		user.setEmail("carlos@gmail.com");
		user.setName("asas");
		user.setPassword("sss");
		user.setToken("sss");
		RegistraUsuarioServices registraUsuarioServices = Mockito.mock(RegistraUsuarioServices.class);
		doReturn(user).when(registraUsuarioServices).save(any());

		Usuario returnedUsuario = registraUsuarioServices.save(user);

		// Execute the service call
		System.out.println(returnedUsuario.getName());

		// Assert the response

		Assertions.assertNotNull(returnedUsuario);
	}
}
