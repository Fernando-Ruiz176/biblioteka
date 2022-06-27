package cl.aiep.java.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cl.aiep.java.model.Autor;


@Repository
public class AutorRepositoryImp implements AutorRepository{
	
	private static final String TABLA = "autores";
	
	@Autowired
	JdbcTemplate jdbcTemplate; 
	
	private Autor makeObject(ResultSet rs, int filaNum) throws SQLException{
		return new Autor (rs.getLong("id"),rs.getString("nombre"));
	}
	
	public List<Autor> findAll() {
		final String sql = String.format("SELECT * FROM %s", TABLA);
		return jdbcTemplate.query(sql, this::makeObject);
	}

	@Override
	public Autor findById(Long id) {
		final String sql = String.format("SELECT * FROM %s WHERE id = ?", TABLA);
		return jdbcTemplate.queryForObject(sql, this::makeObject, id);
	}

	@Override
	public void crear(Autor autor) {
		String sql = String.format("INSERT INTO %s(nombre) VALUES (?) ", TABLA);
		jdbcTemplate.update(sql, autor.getNombre() );
	}

	@Override
	public void editar(Autor autor) {
		String sql = String.format("UPDATE %s SET nombre = ? WHERE id = ?", TABLA);
		jdbcTemplate.update(sql, autor.getNombre(), autor.getId());
	}

	@Override
	public void eliminar(Long id) {
		String sql = String.format("DELETE FROM %s WHERE id = ?", TABLA);
		jdbcTemplate.update(sql,id);
		
	}
	
}
