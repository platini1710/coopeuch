package com.coopeuch.tareas.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "tareas")
@EntityListeners(AuditingEntityListener.class)
@ApiModel("Model Tarea")
public class Tareas {
	@ApiModelProperty(value = "id que es el Pk de la tabla", required = true)
    @Id
    private Long id;
	@ApiModelProperty(value = "codigo de la Tarea", required = true)


    @Column(name = "nombre", nullable = false)
    private String nombre;
	@ApiModelProperty(value = "descripcion de la tarea , no es null", required = true)
    @Column(name = "descripcion", nullable = false)
    private String descripcion;
	
    @Column(name = "fcreated", nullable = true)
    private String created;
    @Column(name = "isactive",columnDefinition = "boolean  default true", nullable = true )
    private boolean  isActive;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public boolean getActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	@Override
	public String toString() {
		return "Productos [id=" + id +  ", nombre=" + nombre + ", descripcion="
				+ descripcion + "]";
	}

    
}
