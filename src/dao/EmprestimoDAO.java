package dao;

import java.util.List;

import biblioteca.entities.Emprestimo;

public interface EmprestimoDAO {
	
	void insert(Emprestimo emprestimo);
	Emprestimo findById(int id);
	List<Emprestimo> findAll();
	void update(Emprestimo emprestimo);
	List<Emprestimo> findByIdLeitor(int id);
	List<Emprestimo> findByIdLivro(int id);
	
	
}
