package cl.aiep.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import cl.aiep.java.model.Libro;
import cl.aiep.java.repository.LibroRepository;


@Controller
public class IndexController {
	
	@Autowired
	LibroRepository libroRepository;

	@GetMapping ("/")
	public String inicio() {  
		return "inicio";
	}
	
	@GetMapping("/listadoInicio")
	public String listarLibroInicio(Model modelo) {
		List<Libro> libros = libroRepository.findAll();
		modelo.addAttribute("libros", libros);
		return "/listadoInicio";
	}
	

}
