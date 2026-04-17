package biblioteca.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import biblioteca.entities.Emprestimo;
import biblioteca.entities.Leitor;
import biblioteca.entities.Livro;
import biblioteca.repositories.EmprestimoDAO;
import biblioteca.repositories.LeitorDAO;
import biblioteca.repositories.LivroDAO;

public class EmprestimoService {

	private EmprestimoDAO emprestimoDao;
	private LeitorDAO leitorDao;
	private LivroDAO livroDao;

	// injecao de dependencia
	public EmprestimoService(EmprestimoDAO emprestimoDao, LeitorDAO leitor, LivroDAO livro) {
		this.emprestimoDao = emprestimoDao;
		this.leitorDao = leitor;
		this.livroDao = livro;
	}

	// insert Emprestimo
	public void realizarEmprestimo(int idLeitor, int idLivro) {
		// Verifica se o livro esta emprestado
		Livro livro = livroDao.find(idLivro);
		if (!livro.isDisponivel()) {
			throw new RuntimeException("Livro indisponível");
		}
		// Busca os emprestimos filtrado por id do leitor
		List<Emprestimo> historicoEmprestimos = emprestimoDao.findByIdLeitor(idLeitor);

		// verificar se o Leitor tem emprestimo pendente
		long livrosPendentes = 0;
		for (Emprestimo emprestimo : historicoEmprestimos) {
			if (!emprestimo.isDevolvido()) {
				livrosPendentes++;
			}
		}
		if (livrosPendentes >= 2) {
			throw new RuntimeException("Leitor com 2 livros pendentes");
		}

		// instancia um novo Emprestimo
		Emprestimo novoEmprestimo = new Emprestimo(idLeitor, idLivro, false, LocalDate.now());

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

	// busca Emprestimo por ID
	public String findById(int emprestimo_id) {
		// pega o objeto Emprestimo
		Emprestimo emprestimo = emprestimoDao.findById(emprestimo_id);
		// pega Livro e Leitor relacionado com Emprestimo
		Livro livro = livroDao.find(emprestimo.getLivro_id());
		Leitor leitor = leitorDao.find(emprestimo.getLeitor_id());

		return listaEmprestimo(emprestimo, livro, leitor);
	}

	// Lista todos Emprestimos
	public List<String> findAll() {
		// peguei os emprestimos
		List<Emprestimo> emprestimos = emprestimoDao.findAll();
		// criei uma lista de String vazia
		List<String> emprestimoAtivo = new ArrayList<>();
		for (Emprestimo obj : emprestimos) {
			Livro livro = livroDao.find(obj.getLivro_id());
			Leitor leitor = leitorDao.find(obj.getLeitor_id());
			String emprestimoFormat = listaEmprestimo(obj, livro, leitor);
			emprestimoAtivo.add(emprestimoFormat);
		}
		return emprestimoAtivo;
	}

	// Lista Emprestimo Ativo
	public List<String> findAllActive() {
		// peguei os emprestimos
		List<Emprestimo> emprestimos = emprestimoDao.findAll();
		// criei uma lista de String vazia
		List<String> emprestimoAtivo = new ArrayList<>();
		for (Emprestimo obj : emprestimos) {
			if (!obj.isDevolvido()) {
				Livro livro = livroDao.find(obj.getLivro_id());
				Leitor leitor = leitorDao.find(obj.getLeitor_id());
				String emprestimoFormat = listaEmprestimo(obj, livro, leitor);
				emprestimoAtivo.add(emprestimoFormat);
			}
		}
		return emprestimoAtivo;
	}

	// devolucao
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

	public String listaEmprestimo(Emprestimo emprestimo, Livro livro, Leitor leitor) {
		StringBuilder sb = new StringBuilder();
		sb.append(emprestimo.getId()).append(" - ")
			.append(livro.getTitulo()).append(" - ")
			.append(leitor.getNome()).append(" - ")
			.append(emprestimo.getDataEmprestimo()).append(" - ")
			.append(emprestimo.isDevolvido() == false ? "Não devolvido!" : emprestimo.getDataDevolucao());

		return sb.toString();
	}
}
