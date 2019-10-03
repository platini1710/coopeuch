package com.tienda.retail.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.tienda.retail.model.Productos;
import com.tienda.retail.model.ProductosDetalle;

@Component
public class ConsultaProductoDetalleServiceImpl implements ConsultaProductoDetalleService{
	@PersistenceContext
	EntityManager entityManager; 
	@Override
	public List<ProductosDetalle> allDetalle(Long id) {
		// TODO Auto-generated method stub
    	return  entityManager.createQuery(
                "SELECT p FROM ProductosDetalle p where p.id.id=:id")  .setParameter("id", id).getResultList();
	}

}
