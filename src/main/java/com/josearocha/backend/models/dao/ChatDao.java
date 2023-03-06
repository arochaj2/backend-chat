package com.josearocha.backend.models.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.josearocha.backend.models.documents.Mensaje;

public interface ChatDao extends MongoRepository<Mensaje, String> {

	public List<Mensaje> findFirst10ByOrderByFechaDesc();
}
