package com.nissum.tareas.DTO;

public class PhonesDTO {
	private long id;
	private int numero;
	private int citycode;
	public PhonesDTO(long id,int numero,int citycode) {
		// TODO Auto-generated constructor stub
		this.id=id;
		this.numero=numero;
		this.citycode=citycode;
	}
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

}
