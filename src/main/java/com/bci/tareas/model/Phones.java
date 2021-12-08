package com.bci.tareas.model;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "Phones")
@EntityListeners(AuditingEntityListener.class)
public class Phones {
	@Id

	private long id;
	private int numero;
	private int citycode;

private int contrycode;

@JoinColumn(name="id", nullable=false)
@OneToOne
private Usuario usuario;

	public long getId() {
	return id;
}
public void setId(long id) {

	this.id = id;
}



public int getNumero() {
	return numero;
}
public void setNumero(int numero) {

	this.numero = numero;
}
public int getCitycode() {
	return citycode;
}
public void setCitycode(int citycode) {
	this.citycode = citycode;
}
public int getContrycode() {
	return contrycode;
}
public void setContrycode(int contrycode) {
	this.contrycode = contrycode;
}
public Usuario getUsuario() {
	System.out.println("get id usuario " + usuario.getId());
	return usuario;
}
public void setUsuario(Usuario usuario) {
	System.out.println("set id usuario " + usuario.getId());
	this.usuario = usuario;
}


}
