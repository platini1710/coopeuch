package com.bci.tareas.server.main;

import static org.mockito.Mockito.doReturn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.context.WebApplicationContext;

import com.bci.tareas.model.Usuario;
import com.bci.tareas.service.ConsultaUsuarioService;
import com.bci.tareas.service.RegistraUsuarioServices;
import static org.mockito.ArgumentMatchers.any;
@SpringBootTest
public class ApplicationTests {


	@Mock
	private RegistraUsuarioServices registraUsuarioServices;
	@Mock
	private ConsultaUsuarioService consultaUsuarioService;
    @Mock
    private WebApplicationContext wac;
    @InjectMocks


    private RegistraUsuarioServices repository;

    @Test
    @DisplayName("Test findById Success")
    void testFindById() {
        // Setup our mock repository
    	Usuario user=new Usuario();
    	user.setId(0);
    	user.setEmail("carlos@gmail.com");
    	user.setName("asas");
    	user.setPassword("sss");
    	user.setToken("sss");
    	doReturn(user).when.registraUsuarioServices.save(any());
    	 Usuario returnedUsuario = consultaUsuarioService.findUsuario("asas");
    	 
    	 


        // Execute the service call
  System.out.println(returnedUsuario);

        // Assert the response

        Assertions.assertSame(returnedUsuario.getId(),user.getId());
    }
}
