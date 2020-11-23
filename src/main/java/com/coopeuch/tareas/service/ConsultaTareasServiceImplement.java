package com.coopeuch.tareas.service;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.coopeuch.tareas.model.Tareas;

@Component
public class ConsultaTareasServiceImplement implements ConsultaTareasService {
	  @PersistenceContext
	  EntityManager entityManager; 
	  
	  
		@Override
		public ArrayList<Tareas> allTareas() {
			// TODO Auto-generated method stub
	    	List<Tareas>  listTareas=entityManager.createQuery(
	                "SELECT p FROM Tareas p").getResultList();
	    	return (ArrayList<Tareas>) listTareas;
		}  
		
		@Override
		public Tareas findTareas(Long id) {
			// TODO Auto-generated method stub
		    	return  entityManager.find(Tareas.class,id);
		}

}
