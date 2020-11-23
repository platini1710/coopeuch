package com.coopeuch.tareas.server.main;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.coopeuch.tareas.controllers.TareaRegistrationController;
import com.coopeuch.tareas.controllers.TareaRetrieveController;
import com.coopeuch.tareas.model.Tareas;
import com.coopeuch.tareas.service.ConsultaTareasService;
import com.coopeuch.tareas.service.RegistraTareasServices;

import io.swagger.annotations.ApiOperation;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringBootRest2ApplicationTests {

	@MockBean
	private ConsultaTareasService consultaTareasService;
	@MockBean
	private RegistraTareasServices registraTareasServices;

	
	
	@ApiOperation(value = " metodo que busca una tarea  por id", notes = "usa id de parametro,"
			+ "comparo if el objeto consultado  es distinto de nulo ")
	@Test
	public void whenFindById_thenReturnTarea() throws Exception {
		// give
		Long id = 1L;
		Tareas tarea = new Tareas();
		tarea.setId(1L);
		

		tarea.setNombre("augusto");
		Tareas t = consultaTareasService.findTareas(id);
		ConsultaTareasService test = mock(ConsultaTareasService.class);
		when(test.findTareas(1L)).thenReturn(tarea);
		assertEquals(tarea, test.findTareas(1L));
	}

	@ApiOperation(value = "prueba metodo que busca todo los productos", notes = "no hay  parametros ")

	@Test
	public void whenSinParametros_ConsultarAllTareas() {
		TareaRetrieveController userController = new TareaRetrieveController();
		ArrayList<Tareas> listarTareas= null;

		ConsultaTareasService test = mock(ConsultaTareasService.class);
		when(test.allTareas()).thenReturn(listarTareas);
		assertThat(test.allTareas()).isEqualTo(listarTareas);
	}

	@ApiOperation(value = "prueba metodo guardar tarea", notes = "creo un"
			+ "objeto tarea luego busco si exite y si no  existe lo guardo"
			+ " luego nuevamente lo busco y ahora si existe")
	@Test
	public void whenSave_CrearNewTarea() {
		Long id = 11L;

		Tareas tarea = new Tareas();
		tarea.setId(id);
	
		ConsultaTareasService consultaTareasServiceTest = mock(ConsultaTareasService.class);
		when(consultaTareasServiceTest.findTareas(id)).thenReturn(null);

		TareaRegistrationController registraProductosServices = mock(TareaRegistrationController.class);
		when(consultaTareasServiceTest.findTareas(id)).thenReturn(tarea);
		registraProductosServices.saveTarea(tarea);

		when(consultaTareasServiceTest.findTareas(id)).thenReturn(tarea);

		assertNotNull(consultaTareasServiceTest.findTareas(id));

	}
	@ApiOperation(value = "whenSave_ActualizarNewTarea", notes = "Update"
			+ "de una tarea para ello "
			+ " luego nuevamente lo busco y ahora si existe")
	@Test
	public void whenSave_ActualizarNewTarea() {
		Long id = 11L;
		Tareas tarea = new Tareas();
		ConsultaTareasService consultaTareasServiceTest1 = mock(ConsultaTareasService.class);
		when(consultaTareasServiceTest1.findTareas(id)).thenReturn(tarea);
		// compruebo que existe la tarea
		tarea = consultaTareasServiceTest1.findTareas(id);
		// modifico un campo de ese producto
		String descripcion = "nueva descripcion";
		tarea.setDescripcion(descripcion);
		TareaRegistrationController registraProductosServices = mock(TareaRegistrationController.class);
		registraProductosServices.saveTarea(tarea);

		ConsultaTareasService consultaTareasServiceTest = mock(ConsultaTareasService.class);
		when(consultaTareasServiceTest.findTareas(id)).thenReturn(tarea);
		Tareas  newTtarea = consultaTareasServiceTest.findTareas(id);
		assertEquals(tarea.getDescripcion(), newTtarea.getDescripcion());

	}
	@ApiOperation(value = "whenSave_ActualizarTarea", notes = "Borrar"
			+ "con el Id comprueo si existe  , si existe procedo a borrarlo y lo "
			+ " vuelvo a buscar y me debe entregar un nullo")


	@Test
	public void assertEqualNull_BorrarOldTarea() {
		Long id = 11L;
		Tareas tarea = new Tareas();
		ConsultaTareasService consultaTareasServiceTest1 = mock(ConsultaTareasService.class);
		when(consultaTareasServiceTest1.findTareas(id)).thenReturn(tarea);// el producto existe
		tarea = consultaTareasServiceTest1.findTareas(id);

		TareaRegistrationController registraTareasServices = mock(TareaRegistrationController.class);
		registraTareasServices.deleteProducto(id);// lo borro

		ConsultaTareasService consultaTareasServiceTest = mock(ConsultaTareasService.class);
		when(consultaTareasServiceTest.findTareas(id)).thenReturn(null);
		Tareas oldProducto = consultaTareasServiceTest.findTareas(id);//lo vuelvo a consultar 

		assertNull(oldProducto);// no existe

	}
	


}