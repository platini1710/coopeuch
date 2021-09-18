package com.nissum.tareas.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.nissum.tareas.model.Usuario;


@Component 
@Transactional() 
public class RegistraUsuarioServicesImpl implements  RegistraUsuarioServices{
	  @PersistenceContext
	  EntityManager entityManager; 
	  

	@Override
	public void save(Usuario usuario) {
		// TODO Auto-generated method stub
	int res=	entityManager.createNativeQuery("INSERT INTO usuario ( name, password,email,fecha_creacion) VALUES (?,?,?,?)")
	      .setParameter(1, usuario.getName())
	      .setParameter(2, usuario.getPassword())
	      .setParameter(3, usuario.getEmail())
	      .setParameter(4, usuario.getFecha_creacion())
	      .executeUpdate();
		Long query =(Long) entityManager.createQuery("select max(p.id) from Usuario p where p.name=:name").setParameter("name", usuario.getName()).getSingleResult();

		entityManager.createNativeQuery("INSERT INTO Phones ( id, numero,citycode,contrycode) VALUES (?,?,?,?)")
	      .setParameter(1, query)
	      .setParameter(2,usuario.getPhone().getNumero())
	      .setParameter(3, usuario.getPhone().getCitycode())
	      .setParameter(4, usuario.getPhone().getContrycode())
	      .executeUpdate();
	}

	@Override
	public void update(Usuario usuario) {
		// TODO Auto-generated method stub
		int res=	entityManager.createNativeQuery("update  usuario set  password=?, email=?, FECHA_ACTUALIZACION=? where name=?")

			      .setParameter(1, usuario.getPassword())
			      .setParameter(2, usuario.getEmail())
			      .setParameter(3, usuario.getFecha_actualizacion())
			      .setParameter(4, usuario.getName())
			      .executeUpdate();

				entityManager.createNativeQuery("update  PHONES set  numero=?,citycode=?,contrycode=? WHERE ID=?")
			  
			      .setParameter(1,usuario.getPhone().getNumero())
			      .setParameter(2, usuario.getPhone().getCitycode())
			      .setParameter(3, usuario.getPhone().getContrycode())
			      .setParameter(4, usuario.getId())
			      .executeUpdate();	
	}


	

}
