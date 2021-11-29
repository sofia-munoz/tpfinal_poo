package ar.edu.unju.fi.poo.grupo04.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.poo.grupo04.entity.ObraSocial;
import ar.edu.unju.fi.poo.grupo04.repository.ObraSocialRepository;
import ar.edu.unju.fi.poo.grupo04.service.IObraSocialService;

@Service("obraSocialServiceImp")
public class ObraSocialServiceImp implements IObraSocialService {
	@Autowired
	ObraSocialRepository obraSocialRepository;
	
	@Autowired
	ObraSocial obraSocial = new ObraSocial();

	@Override
	public ObraSocial buscarObraSocialPorNombre(String nombre) {
		obraSocial = obraSocialRepository.findByNombre(nombre);
		return obraSocial;
	}

	@Override
	public ObraSocial buscarObraSocialPorId(Long id) {
		obraSocial = obraSocialRepository.findById(id).get();
		return obraSocial;
	}

}
