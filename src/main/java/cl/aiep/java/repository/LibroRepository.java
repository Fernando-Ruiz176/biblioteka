package cl.aiep.java.repository;

import java.util.List;

import cl.aiep.java.model.Libro;

public interface LibroRepository {
	
	public List<Libro> findAll();
	public Libro findById(int id);
	public void crear(Libro libro);
	public void editar(Libro libro);
	public void eliminar(int id);
	public List<Libro> findByNombre(String nombre);

}
