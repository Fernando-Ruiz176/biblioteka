package cl.aiep.java.model;



import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Libro {
	
	@Min(0)
	private int id;
	@Size(min = 5, max = 30, message="Debe tener entre 5 y 30 caracteres")
	private String isbn;
	@Size(min = 3, max = 50, message="Debe tener entre 3 y 50 caracteres")
	private String nombre;
	private LocalDate fechaEdicion;
	@NotNull
	private Autor autor;
	
	

}
