package biblioteca.service;

import java.time.LocalDate;
import java.util.List;

import biblioteca.entities.Emprestimo;
import biblioteca.entities.Livro;
import biblioteca.repository.EmprestimoDAO;
import biblioteca.repository.LeitorDAO;
import biblioteca.repository.LivroDAO;

public class EmprestimoService {

	private EmprestimoDAO emprestimoDao;
	private LeitorDAO  leitorDao;
	private LivroDAO  livroDao;

	// injecao de dependencia
	public EmprestimoService(EmprestimoDAO emprestimoDao, LeitorDAO leitor, LivroDAO livro) {
		this.emprestimoDao = emprestimoDao;
		this.leitorDao = leitor;
		this.livroDao = livro;
	}
	
	public void realizarEmprestimo(int idLeitor, int idLivro) {
		// Verifica se o livro esta emprestado
		Livro livro = livroDao.find(idLivro);
		if (!livro.isDisponivel()) {
			throw new RuntimeException("Livro indisponível");
		}
		// Busca os emprestimos filtrado por id do leitor
		List<Emprestimo> historicoEmprestimos = emprestimoDao.findByIdLeitor(idLeitor);

		// Percorre a lista para verificar se o Leitor tem emprestimo pendente
		long livrosPendentes = 0;
		for (Emprestimo emprestimo : historicoEmprestimos) {
			if (!emprestimo.isDevolvido()) {
				livrosPendentes++;
			}
		}
		if(livrosPendentes >= 2) {
			throw new RuntimeException("Leitor com 2 livros pendentes");
		}
	
		// instancia um novo Emprestimo
		Emprestimo novoEmprestimo = new Emprestimo(idLeitor, idLivro,  false, LocalDate.now());
		
		// busca todos os Emprestimos
		List<Emprestimo> emprestimos = emprestimoDao.findAll();
		
		// atribui o id automaticamente, autoincremento
		if (emprestimos.size() == 0) {
			novoEmprestimo.setId(1);
		} else {
			Emprestimo ultimoEmprestimo = emprestimos.get(emprestimos.size() - 1);
			novoEmprestimo.setId(ultimoEmprestimo.getId() + 1);
		}
		
		emprestimoDao.insert(novoEmprestimo);

		// seta o campo 'disponivel' do Livro
		livro.setDisponivel(false);
		// atualiza o arquivo Livro
		livroDao.update(idLivro, livro);

	}

	public void realizarDevolucao(int idEmprestimo) {
		// Busca o Emprestimo pelo ID
		Emprestimo emprestimo = emprestimoDao.findById(idEmprestimo);
		// Verifica se o Emprestimo ja foi devolvido
		if (emprestimo.isDevolvido()) {
			throw new RuntimeException("Emprestimo já devolvido.");
		}
		// seta o campo 'devolvido' como true
		emprestimo.setDevolvido(true);
		// seta o campo 'dataDevolução' com a data do momento
		emprestimo.setDataDevolucao(LocalDate.now());
		
		// pega o ID do Livro do emprestimo
		int livro_id = emprestimo.getLivro_id();
		// pega o Livro relacionado com o ID 
		Livro livro = livroDao.find(livro_id);
		// seta o campo 'disponivel' do Livro
		livro.setDisponivel(true);
		
		// atualiza o arquivo do Livro
		livroDao.update(livro_id, livro);
		// atualiza o arquivo do Emprestimo
		emprestimoDao.update(emprestimo);
	}
}
