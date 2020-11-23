package com.coopeuch.tareas.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.coopeuch.tareas.model.Tareas;


@Component 
public class RegistraTareasServicesImpl implements  RegistraTareasServices{
	  @PersistenceContext
	  EntityManager entityManager; 
	  
	@Transactional  
	@Override
	public void save(Tareas producto) {
		// TODO Auto-generated method stub
		  entityManager.persist(producto);
	}
	@Transactional
	@Override
	public void update(Tareas producto) {
		// TODO Auto-generated method stub
		  entityManager.persist(producto);
	}
    @Transactional
	@Override
	public void delete(Tareas producto) {
		// TODO Auto-generated method stub
		  entityManager.remove(producto);
	}


}
