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
import cl.aiep.java.repository.AutorRepository;




@Controller
@RequestMapping("/autor")
public class AutorController {
	
	@Autowired
	AutorRepository autorRepository;
	
	@GetMapping ("/index")
	public String index() {
		return "index";
	}
	
	@GetMapping("/nuevo")
	public String nuevoAutor(Autor autor) {
		return "autor/form";
	}
	
	@PostMapping("/procesar")
	public String procesarAutor(@Valid Autor autor, BindingResult validacion) {
		
		if(validacion.hasErrors()) {
			return "autor/form";
		}
		if(autor.getId() == null || autor.getId() == 0) {
			autorRepository.crear(autor);
			
		}else {
			
			autorRepository.editar(autor);
		}
		return"redirect:/autor/listado";
	}
	
	@GetMapping("/listado")
	public String listarAutor(Model modelo) {
		List<Autor> autores = autorRepository.findAll();
		modelo.addAttribute("autores", autores);
		return "autor/listado";
	}
	
	@GetMapping("/editar/{id}")
	public String editarAutor(@PathVariable Long id , Model modelo) {
		Autor autor = autorRepository.findById(id);
		modelo.addAttribute("autor", autor);
		return "autor/form";
	}
	
	@GetMapping("/eliminar")
	public String eliminarAutor(@RequestParam(name="id", required = true)Long id) {
		autorRepository.eliminar(id);
		return "redirect:/autor/listado";
	}

}
