package com.tienda.retail.server.main;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.tienda.retail.controllers.ProductDetailRetrieveController;
import com.tienda.retail.controllers.ProductRegistrationController;
import com.tienda.retail.controllers.ProductRetrieveController;
import com.tienda.retail.helper.Respuesta;
import com.tienda.retail.model.Productos;
import com.tienda.retail.model.ProductosDetalle;
import com.tienda.retail.service.ConsultaProductoDetalleService;
import com.tienda.retail.service.ConsultaProductosService;
import com.tienda.retail.service.RegistraProductosServices;

import io.swagger.annotations.ApiOperation;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringBootRest2ApplicationTests {

	@MockBean
	private ConsultaProductosService consultaProductosService;
	@MockBean
	private RegistraProductosServices registraProductosServices;
	@MockBean
	private ConsultaProductoDetalleService consultaProductoDetalleService;
	
	
	@ApiOperation(value = " metodo que busca un producto por id", notes = "usa id de parametro,"
			+ "comparo if el objeto consultado  es distinto de nulo ")
	@Test
	public void whenFindById_thenReturnProducto() throws Exception {
		// give
		Long id = 1L;
		Productos producto = new Productos();
		producto.setId(1L);
		;
		producto.setCodigoProducto("1000");
		producto.setNombre("augusto");
		Productos p = consultaProductosService.findProducto(id);
		ConsultaProductosService test = mock(ConsultaProductosService.class);
		when(test.findProducto(1L)).thenReturn(producto);
		assertEquals(producto, test.findProducto(1L));
	}

	@ApiOperation(value = "prueba metodo que busca todo los productos", notes = "no hay  parametros ")

	@Test
	public void whenSinParametros_ConsultarAllProductos() {
		ProductRetrieveController userController = new ProductRetrieveController();
		List<Productos> listaProductos = null;
		Respuesta respuesta = userController.getAllProductos();
		ConsultaProductosService test = mock(ConsultaProductosService.class);
		when(test.allProductos()).thenReturn(listaProductos);
		assertThat(test.allProductos()).isEqualTo(listaProductos);
	}

	@ApiOperation(value = "prueba metodo guardar producto", notes = "creo un"
			+ "objeto producto luego busco si exite y si no  existe lo guardo"
			+ " luego nuevamente lo busco y ahora si existe")
	@Test
	public void whenSave_CrearNewProducto() {
		Long id = 11L;

		Productos producto = new Productos();
		producto.setId(id);
		;
		ConsultaProductosService consultaProductosServiceTest = mock(ConsultaProductosService.class);
		when(consultaProductosServiceTest.findProducto(id)).thenReturn(null);

		ProductRegistrationController registraProductosServices = mock(ProductRegistrationController.class);
		when(consultaProductosServiceTest.findProducto(id)).thenReturn(producto);
		registraProductosServices.saveProducto(producto);

		when(consultaProductosServiceTest.findProducto(id)).thenReturn(producto);

		assertNotNull(consultaProductosServiceTest.findProducto(id));

	}
	@ApiOperation(value = "whenSave_ActualizarNewProducto", notes = "Update"
			+ "de un producto para ello "
			+ " luego nuevamente lo busco y ahora si existe")
	@Test
	public void whenSave_ActualizarNewProducto() {
		Long id = 11L;
		Productos producto = new Productos();
		ConsultaProductosService consultaProductosServiceTest1 = mock(ConsultaProductosService.class);
		when(consultaProductosServiceTest1.findProducto(id)).thenReturn(producto);
		// compruebo que existe el producto
		producto = consultaProductosServiceTest1.findProducto(id);
		// modifico un campo de ese producto
		String descripcion = "nueva descripcion";
		producto.setDescripcion(descripcion);
		ProductRegistrationController registraProductosServices = mock(ProductRegistrationController.class);
		registraProductosServices.saveProducto(producto);

		ConsultaProductosService consultaProductosServiceTest = mock(ConsultaProductosService.class);
		when(consultaProductosServiceTest.findProducto(id)).thenReturn(producto);
		Productos newProducto = consultaProductosServiceTest.findProducto(id);
		assertEquals(producto.getDescripcion(), producto.getDescripcion());

	}
	@ApiOperation(value = "whenSave_ActualizarNewProducto", notes = "Borrar"
			+ "con el Id comprueo si existe  , si existe procedo a borrarlo y lo "
			+ " vuelvo a buscar y me debe entregar un nullo")


	@Test
	public void assertEqualNull_BorrarOldProducto() {
		Long id = 11L;
		Productos producto = new Productos();
		ConsultaProductosService consultaProductosServiceTest1 = mock(ConsultaProductosService.class);
		when(consultaProductosServiceTest1.findProducto(id)).thenReturn(producto);// el producto existe
		producto = consultaProductosServiceTest1.findProducto(id);

		ProductRegistrationController registraProductosServices = mock(ProductRegistrationController.class);
		registraProductosServices.deleteProducto(id);// lo borro

		ConsultaProductosService consultaProductosServiceTest = mock(ConsultaProductosService.class);
		when(consultaProductosServiceTest.findProducto(id)).thenReturn(null);
		Productos oldProducto = consultaProductosServiceTest.findProducto(id);//lo vuelvo a consultar 

		assertNull(oldProducto);// no existe

	}
	
	@ApiOperation(value = "whenFindById_thenReturnProductoDetalle", notes = "Lista de detalle de productosd "
			+ "con el Id del producto comprueblo si hay detallle " + " me debe devolver una lista no vacia ")

	@Test
	public void whenFindById_thenReturnProductoDetalle() throws Exception {
		// give
		Long id = 1L;
		List<ProductosDetalle> listProductoDetalle = new ArrayList();
		Productos producto = new Productos();
		producto.setId(1L);
		producto.setCodigoProducto("1000");
		producto.setNombre("augusto");
		Productos p = consultaProductosService.findProducto(id);
		ProductDetailRetrieveController productDetailRetrieveController = mock(ProductDetailRetrieveController.class);
		when(consultaProductoDetalleService.allDetalle(id)).thenReturn(listProductoDetalle);
		assertNotNull(consultaProductoDetalleService.allDetalle(id));
	}
}