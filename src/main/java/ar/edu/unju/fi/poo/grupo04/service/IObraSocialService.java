package ar.edu.unju.fi.poo.grupo04.service;

import ar.edu.unju.fi.poo.grupo04.entity.ObraSocial;

public interface IObraSocialService {
	
	public ObraSocial buscarObraSocialPorNombre(String obraSocial);
	public ObraSocial buscarObraSocialPorId(Long id);
}
