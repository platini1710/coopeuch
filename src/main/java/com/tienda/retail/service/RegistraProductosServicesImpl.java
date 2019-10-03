package com.tienda.retail.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.tienda.retail.model.Productos;
import com.tienda.retail.model.ProductosDetalle;


@Component 
public class RegistraProductosServicesImpl implements  RegistraProductosServices{
	  @PersistenceContext
	  EntityManager entityManager; 
	  
	@Transactional  
	@Override
	public void save(Productos producto) {
		// TODO Auto-generated method stub
		  entityManager.persist(producto);
	}
	@Transactional
	@Override
	public void update(Productos producto) {
		// TODO Auto-generated method stub
		  entityManager.persist(producto);
	}
    @Transactional
	@Override
	public void delete(Productos producto) {
		// TODO Auto-generated method stub
		  entityManager.remove(producto);
	}


}
