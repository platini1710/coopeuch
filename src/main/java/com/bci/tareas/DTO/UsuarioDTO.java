package com.bci.tareas.DTO;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;


public class UsuarioDTO {


	private long id;

	private String name;
	private String email;
	private String password;
	private PhonesDTO phonesDTO;

	private String fechaCreacion;
	  
	private String fechaActualizacion; 

	public long getId() {
		return id;
	}
	public void setId(long id) {

		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public PhonesDTO getPhonesDTO() {
		return phonesDTO;
	}
	public void setPhonesDTO(PhonesDTO phonesDTO) {
		this.phonesDTO = phonesDTO;
	}
	public String getFechaActualizacion() {
		return fechaActualizacion;
	}
	public void setFechaActualizacion(String fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}


}
