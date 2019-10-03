package com.tienda.retail.model;

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
@Table(name = "productos")
@EntityListeners(AuditingEntityListener.class)
@ApiModel("Model Productos")
public class Productos {
	@ApiModelProperty(value = "id que es el Pk de la tabla", required = true)
    @Id
    private Long id;
	@ApiModelProperty(value = "codigo del producto", required = true)
    @Column(name = "codigoproducto", nullable = false)
    private String codigoProducto;
	@ApiModelProperty(value = "nombre del producto , no es null", required = true)
    @Column(name = "nombre", nullable = false)
    private String nombre;
	@ApiModelProperty(value = "descripcion del producto , no es null", required = true)
    @Column(name = "descripcion", nullable = false)
    private String descripcion;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCodigoProducto() {
		return codigoProducto;
	}
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
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
	@Override
	public String toString() {
		return "Productos [id=" + id + ", codigoProducto=" + codigoProducto + ", nombre=" + nombre + ", descripcion="
				+ descripcion + "]";
	}

    
}
