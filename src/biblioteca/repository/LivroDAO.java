package biblioteca.repository;

import java.util.List;

import biblioteca.entities.Livro;

public interface LivroDAO {
	
	void insert(Livro livro);
	Livro find(int id);
	List<Livro> findAll();
	void update(int id, Livro livro);
	void remove(int id);
	
}
