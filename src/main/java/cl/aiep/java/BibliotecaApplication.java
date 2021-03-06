package cl.aiep.java;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import cl.aiep.java.model.Autor;
import cl.aiep.java.repository.AutorRepository;

@SpringBootApplication
public class BibliotecaApplication implements WebMvcConfigurer{
	
	@Autowired
	private AutorRepository autorRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(BibliotecaApplication.class, args);
	}
	
	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new Converter<String, Autor>() {

			@Override
			public Autor convert(String source) {
				Long id = Long.parseLong(source);
				Autor autor = autorRepository.findById(id);
				return autor;
			}

			
		});
		
	}

}
