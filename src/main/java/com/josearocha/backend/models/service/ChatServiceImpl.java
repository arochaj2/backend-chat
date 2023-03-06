package com.josearocha.backend.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.josearocha.backend.models.dao.ChatDao;
import com.josearocha.backend.models.documents.Mensaje;

@Service
public class ChatServiceImpl implements ChatService{

	@Autowired
	private ChatDao chatDao;
	
	@Override
	public List<Mensaje> obtenerUltimos10Mensajes() {
		
		return chatDao.findFirst10ByOrderByFechaDesc();
	}

	@Override
	public Mensaje guardar(Mensaje mensaje) {
		
		return chatDao.save(mensaje);
	}

}
