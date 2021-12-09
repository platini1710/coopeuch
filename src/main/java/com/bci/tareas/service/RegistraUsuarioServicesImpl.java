package com.bci.tareas.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bci.tareas.model.Usuario;

import io.swagger.annotations.ApiOperation;


@Component 
@Transactional() 

public class RegistraUsuarioServicesImpl implements  RegistraUsuarioServices{
	  @PersistenceContext
	  EntityManager entityManager; 
	
	  
	  @ApiOperation(value = "ejecuta  guardar y actualizar registro ", notes = "Return clase Usuario ")
	  

	@Override
	public Usuario save(Usuario usuario) {
		// TODO Auto-generated method stub


	entityManager.persist(usuario);
	return usuario;
	}

	@Override
	public void update(Usuario usuario) {
		// TODO Auto-generated method stub
		entityManager.persist(usuario);

	}
	 @ApiOperation(value = "obtiene el usuario segun  id" , notes = "Return clase Usuario ")
	  

	@Override
	public Usuario getUsuario(Long id  ) {
		// TODO Auto-generated method stub
		System.out.println(" antes " );
		Usuario u = entityManager.find(Usuario.class, id);
		System.out.println(" ombre " + u.getName());
		return u;
	}


	

}
