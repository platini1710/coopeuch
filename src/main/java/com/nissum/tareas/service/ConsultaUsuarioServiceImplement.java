package com.nissum.tareas.service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.nissum.tareas.controllers.TareaRegistrationController;
import com.nissum.tareas.model.Usuario;



@Component
public class ConsultaUsuarioServiceImplement implements ConsultaUsuarioService {
	private static final Logger logger = LoggerFactory.getLogger(ConsultaUsuarioServiceImplement.class);
	  @PersistenceContext
	  EntityManager em; 
	  
	
		@Override
		public Usuario findUsuario(String name) {
			// TODO Auto-generated method stub
			Usuario usuario =null;
			try{
				usuario =(Usuario) em.createQuery("select p from Usuario p  where p.name=:name").setParameter("name", name).getSingleResult();
			
		
			}catch (Exception nre){
				//Ignore this because as per your logic this is ok!
				logger.error(nre.getMessage());
				}

			return	usuario;
		}


}
