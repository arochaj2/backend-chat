package com.josearocha.backend.controller;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.josearocha.backend.models.documents.Mensaje;
import com.josearocha.backend.models.service.ChatService;

@Controller
public class ChatController {
	
	private String[] colores= {"red","green","blue", "magenta", "purple", "orange"};
	
	@Autowired
	private ChatService chatSevice;
	
	@Autowired
	private SimpMessagingTemplate webSocket;
	
	@MessageMapping("/mensaje")
	@SendTo("/chat/mensaje")// <--- con esta anotacion todos los clientes que esten suscritos recibiran el mensaje
	public Mensaje recibeMensaje(Mensaje mensaje) {
		
		mensaje.setFecha(new Date().getTime());
		
		if(mensaje.getTipo().equals("NUEVO_USUARIO")) {
			mensaje.setTexto("nuevo usuario");
			mensaje.setColor(colores[new Random().nextInt(colores.length)]);
		}else {
			chatSevice.guardar(mensaje);
		}
		
		
		return mensaje;
		
	}
	
	@MessageMapping("/escribiendo")
	@SendTo("/chat/escribiendo")
	public String estaEscribiendo(String username) {
		
		return username.concat(" está escribiendo ...");
	}
	
	@MessageMapping("/historial")
	public void historial(String clienteId){
		
		webSocket.convertAndSend("/chat/historial/"+ clienteId, chatSevice.obtenerUltimos10Mensajes());
		
	}

}
