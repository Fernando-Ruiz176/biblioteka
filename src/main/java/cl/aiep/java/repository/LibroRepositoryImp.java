package cl.aiep.java.repository;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cl.aiep.java.model.Autor;
import cl.aiep.java.model.Libro;


@Repository
public class LibroRepositoryImp implements LibroRepository{
	
	private static final String TABLA = "libros";
	
	@Autowired
	JdbcTemplate jdbcTemplate; 
	
	@Autowired
	AutorRepository autorRepository;
	
	public Libro makeObject(ResultSet rs, int filaNum) throws SQLException {
		Long autorId	 = rs.getLong("autor_id");
		Autor autor 	 = autorRepository.findById(autorId);
		return new Libro (rs.getInt("id"), rs.getString("isbn"), rs.getString("nombre"), rs.getObject("fecha_edicion", LocalDate.class), autor);
		
	}
	
	@Override
	public List<Libro> findAll() {
		final String sql = String.format("SELECT * FROM %s", TABLA);
		return jdbcTemplate.query(sql, this::makeObject);
	}

	@Override
	public Libro findById(int id) {
		final String sql = String.format("SELECT * FROM %s WHERE id = ?", TABLA);
		return jdbcTemplate.queryForObject(sql, this::makeObject, id);
		}

	@Override
	public void crear(Libro libro) {
		String sql = String.format("INSERT INTO %S(isbn, nombre, fecha_edicion, autor_id) VALUE (?,?,?,?) ", TABLA);
		jdbcTemplate.update(sql,
				libro.getIsbn(),
				libro.getNombre(),
				libro.getFechaEdicion(),
				libro.getAutor().getId());
	}

	@Override
	public void editar(Libro libro) {
		String sql = String.format("UPDATE %s SET isbn = ?, nombre = ?, fecha_edicion = ?, autor_id = ? WHERE id = ?", TABLA);
		jdbcTemplate.update(sql,
				libro.getIsbn(),
				libro.getNombre(),
				libro.getFechaEdicion(),
				libro.getAutor().getId(),
				libro.getId());
	}

	@Override
	public void eliminar(int id) {
		String sql = String.format("DELETE FROM %s WHERE id = ? ", TABLA);
		jdbcTemplate.update(sql,id);
	}

	@Override
	public List<Libro> findByNombre(String nombre) {
		return jdbcTemplate.query("SELECT * FROM libros WHERE nombre LIKE ?", this::makeObject, "%" + nombre + "%");
	}

}
