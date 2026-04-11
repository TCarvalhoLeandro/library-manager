package biblioteca.service;

import java.time.LocalDate;
import java.util.List;

import biblioteca.entities.Emprestimo;
import biblioteca.entities.Leitor;
import biblioteca.entities.Livro;
import dao.EmprestimoDAO;
import dao.LeitorDAO;
import dao.LivroDAO;

public class EmprestimoService {

	private EmprestimoDAO emprestimoDao;
	private LeitorDAO leitorDao;
	private LivroDAO livroDao;
	
	public int realizarEmprestimo(int idLeitor, int idLivro) {
		// Verifica se o livro esta emprestado
		Livro livro = livroDao.find(idLivro);
		if(!livro.isDisponivel()) {
			throw new RuntimeException("Livro indisponível");
		}
		// Busca os emprestimos filtrado por id do leitor
		List<Emprestimo> historicoEmprestimos =  emprestimoDao.findByIdLeitor(idLeitor);
		
		// Percorre a lista para verificar se o Leitor tem emprestimo pendente
		for(Emprestimo emprestimo: historicoEmprestimos) {
			if(!emprestimo.isDevolvido()) {
				throw new RuntimeException("Leitor com emprestimo pendente");
			}	
		}
		Leitor leitor = leitorDao.find(idLeitor);
		Emprestimo emprestimo = new Emprestimo(Emprestimo.getContador(), leitor, livro, LocalDate.now(), false);
		emprestimoDao.insert(emprestimo);
		
		// Atualiza o atributo diponivel do livro
		livro.setDisponivel(false);
		livroDao.update(livro);
		
		return emprestimo.getId();
	}
	
	public void realizarDevolucao(int idEmprestimo) {
		// Busca o Emprestimo
		Emprestimo emprestimo = emprestimoDao.findById(idEmprestimo);
		// Verifica se o Emprestimo ja foi devolvido
		if(emprestimo.isDevolvido()) {
			throw new RuntimeException("Emprestimo já devolvido.");
		}
	
		emprestimo.setDevolvido(true);
		emprestimo.setDataDevolucao(LocalDate.now());
		emprestimo.getLivro().setDisponivel(true);
		
		
		livroDao.update(emprestimo.getLivro());
		emprestimoDao.update(emprestimo);
	}
}






















