package com.coopeuch.tareas.service;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.coopeuch.tareas.model.Tareas;


@Component 
public class RegistraTareasServicesImpl implements  RegistraTareasServices{
	  @PersistenceContext
	  EntityManager entityManager; 
		DateTimeFormatter ZDT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a z");
	@Transactional  
	@Override
	public void save(Tareas tarea) {
		// TODO Auto-generated method stub
		  entityManager.persist(tarea);
	}
	@Transactional
	@Override
	public void update(Tareas tarea) {
		// TODO Auto-generated method stub
		String str = ZDT_FORMATTER.format(ZonedDateTime.now());
		tarea.setCreated(str);
		tarea.setActive(true);
		  entityManager.persist(tarea);
	}
    @Transactional
	@Override
	public void delete(Tareas tarea) {
		// TODO Auto-generated method stub
		  entityManager.remove(tarea);
	}


}
