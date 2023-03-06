package com.josearocha.backend.models.service;

import java.util.List;

import com.josearocha.backend.models.documents.Mensaje;

public interface ChatService {
	
	public List<Mensaje> obtenerUltimos10Mensajes();
	public Mensaje guardar (Mensaje mensaje);

}
