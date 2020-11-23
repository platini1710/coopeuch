package com.coopeuch.tareas.service;

import java.util.ArrayList;

import com.coopeuch.tareas.model.Tareas;

public interface ConsultaTareasService {
	public ArrayList<Tareas> allTareas();
	public Tareas findTareas(Long id) ;
}
