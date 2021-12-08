package com.bci.tareas.model;

import java.util.Date;

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

@Entity
@Table(name = "usuario")
@EntityListeners(AuditingEntityListener.class)
public class Usuario {

	@Id

	private long id;

	private String name;
	private String email;
	private String password;
	private Date fecha_creacion;
	private Date fecha_actualizacion;
	  @OneToOne(mappedBy="usuario",cascade = CascadeType.ALL)
	  @JoinTable(name = "Phones")
	  @JoinColumn(name = "id", referencedColumnName = "id")
	private Phones phone;
	  
	  

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
	public Phones getPhone() {
		return phone;
	}
	public void setPhone(Phones phone) {
		System.out.println(" phone xxx ::" + phone.getId());
		
		this.phone = phone;

	}
	public Date getFecha_creacion() {
		return fecha_creacion;
	}
	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}
	public Date getFecha_actualizacion() {
		return fecha_actualizacion;
	}
	public void setFecha_actualizacion(Date fecha_actualizacion) {
		this.fecha_actualizacion = fecha_actualizacion;
	}


}
