package com.bci.tareas.service;

import com.bci.tareas.model.Usuario;


public interface RegistraUsuarioServices {
	  public Usuario save(Usuario usuario) ;
	  public void update(Usuario usuario) ;
	  public Usuario getUsuario(Long id) ; 
}
