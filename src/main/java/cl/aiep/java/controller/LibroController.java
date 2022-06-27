package cl.aiep.java.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cl.aiep.java.model.Autor;
import cl.aiep.java.model.Libro;
import cl.aiep.java.repository.AutorRepository;
import cl.aiep.java.repository.LibroRepository;

@Controller
@RequestMapping("/libro")
public class LibroController {
	
	@Autowired
	LibroRepository libroRepository;
	
	@Autowired
	AutorRepository autorRepository;
	
	@GetMapping ("/index") 
	public String index() {
		return "index"; 
	}
	
	@GetMapping("/nuevo")
	public String nuevoLibro(Libro libro, Model modelo) {
		List<Autor> autores = autorRepository.findAll();
		modelo.addAttribute("autores",autores);
		return "libro/form";
	}
	
	@PostMapping("/procesar")
	public String procesarLibro(@Valid Libro libro, BindingResult validacion) {
		
		if(validacion.hasErrors()) {
			return "libro/form";
		}
		
		if(libro.getId() > 0) {
			libroRepository.editar(libro);
			
		}else {
			libroRepository.crear(libro);
			
		}
			return"redirect:/libro/listado";
	}
	
	@GetMapping("/listado")
	public String listarLibro(Model modelo) {
		List<Libro> libros = libroRepository.findAll();
		modelo.addAttribute("libros", libros);
		return "libro/listado";
	}
	
	@GetMapping("/editar/{libroId}")
	public String editarLibro(@PathVariable int libroId , Model modelo) {
		Libro libro = libroRepository.findById(libroId);
		modelo.addAttribute("libro", libro);
		List<Autor> autores = autorRepository.findAll();
		modelo.addAttribute("autores", autores);
		return "libro/form";
	}
	
	@GetMapping("/eliminar")
	public String eliminarLibro(@RequestParam(name="id", required = true)int id) {
		libroRepository.eliminar(id);
		return "redirect:/libro/listado";
	}
	
	@GetMapping("/search")
	public String buscarLibro(@RequestParam(name="q", required = true)String nombre, Model modelo) {
		List<Libro> libro = libroRepository.findByNombre(nombre);
		modelo.addAttribute("libro", libro);
		return "/listadoInicio";
	}

}
