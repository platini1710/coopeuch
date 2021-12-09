package com.bci.tareas.service;

import com.bci.tareas.model.Usuario;

public interface ConsultaUsuarioService {

	public Usuario findUsuario(String  name) ;
	public Usuario findUsuarioEmail(String  name,String  email) ;
	public int findMaxid() ;
}
