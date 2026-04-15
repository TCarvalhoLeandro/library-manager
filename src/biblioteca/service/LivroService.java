package biblioteca.service;

import java.util.List;

import biblioteca.entities.Emprestimo;
import biblioteca.entities.Livro;
import biblioteca.repository.EmprestimoDAO;
import biblioteca.repository.LivroDAO;

public class LivroService {

	private LivroDAO livroDao;
	private EmprestimoDAO emprestimoDao;
	
	// injecao de dependencia
	public LivroService(LivroDAO livroDao, EmprestimoDAO emprestimoDao) {
		this.livroDao = livroDao;
		this.emprestimoDao = emprestimoDao;
	}


	public int insert(Livro novoLivro) {
		List<Livro> livros = livroDao.findAll();
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
		return novoLivro.getId();
	}

	
	public void delete(int idLivro) {
		// verifica se tem emprestimo penedente
		List<Emprestimo> historicoEmprestimo = emprestimoDao.findByIdLivro(idLivro);
		for (Emprestimo emprestimo : historicoEmprestimo) {
			if (!emprestimo.isDevolvido()) {
				throw new RuntimeException("Livro com pendências!");
			}
		}
		livroDao.remove(idLivro);
	}
}
