package biblioteca.service;

import java.util.ArrayList;
import java.util.List;

import biblioteca.domainException.DadosException;
import biblioteca.entities.Livro;
import biblioteca.repository.LivroDAO;

public class LivroService {

	private LivroDAO livroDao;
	
	// injecao de dependencia
	public LivroService(LivroDAO livroDao) {
		this.livroDao = livroDao;
		
	}

	public void insert(Livro novoLivro) {
		List<Livro> livros = new ArrayList<Livro>();
		livros = livroDao.findAll();
		for (Livro livro : livros) {
			if (livro.getTitulo().equals(novoLivro.getTitulo())) {
				throw new RuntimeException("Livro já existente no catálogo!");
			}
		}
		// atribui o id automaticamente, autoincremento
		if (livros.size() == 0) {
			novoLivro.setId(1);
		} else {
			Livro ultimoLivro = livros.get(livros.size() - 1);
			novoLivro.setId(ultimoLivro.getId() + 1);
		}
		livroDao.insert(novoLivro);
	}

	
	public Livro findById(int livro_id) {
		return livroDao.find(livro_id);
	}
	
	public List<Livro> findAll(){
		List<Livro> livros = livroDao.findAll();
		if(livros.isEmpty()) {
			throw new DadosException("Arquivo vazio!");
		}
		return livros;
	}
	
	
	public void update(int id, Livro livro) {
		livroDao.update(id, livro);
	}
	
	
	public void delete(int idLivro) {
		//busca apenas o livro que queremos apagar
		//Se o ID não existir, o DAO já vai lançar exceção
		Livro livro = livroDao.find(idLivro);
		// Verifica se este livro específico está emprestado
		if(!livro.isDisponivel()) {
			throw new DadosException("Livro emprestado!");
		}
		// Se o livro existe e está na prateleira, pode apagar
		livroDao.remove(idLivro);
	}
}
