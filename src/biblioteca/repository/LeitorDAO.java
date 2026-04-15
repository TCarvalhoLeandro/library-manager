package biblioteca.repository;

import java.util.List;

import biblioteca.entities.Leitor;

public interface LeitorDAO {
	
	void insert(Leitor leitor);
	Leitor find(int id);
	List<Leitor> findAll();
	void update(Leitor leitor);
	void remove(int id);
	
}
