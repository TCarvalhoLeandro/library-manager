package biblioteca.service;

import java.util.List;

import biblioteca.domainException.DadosException;
import biblioteca.entities.Emprestimo;
import biblioteca.entities.Leitor;
import biblioteca.repository.EmprestimoDAO;
import biblioteca.repository.LeitorDAO;

public class LeitorService {

	private LeitorDAO leitorDao;
	private EmprestimoDAO emprestimoDao;
	
	// injecao de dependencia
	public LeitorService(LeitorDAO leitorDao, EmprestimoDAO emprestimoDao) {
		this.leitorDao = leitorDao;
		this.emprestimoDao = emprestimoDao;
	}

	// insere Leitor
	public void insert(Leitor novoLeitor) {
		List<Leitor> leitores = leitorDao.findAll();
		// validacao de regra de negocio, verifica se o cpf já existe
		for (Leitor leitor : leitores) {
			if (novoLeitor.getCpf().equals(leitor.getCpf())) {
				throw new RuntimeException("Atenção, você já possui uma conta no nosso sistema!");
			}
		}
		// atribui o id automaticamente, autoincremento
		if (leitores.size() == 0) {
			novoLeitor.setId(1);
		} else {
			Leitor ultimoLeitor = leitores.get(leitores.size() - 1);
			novoLeitor.setId(ultimoLeitor.getId() + 1);
		}
		// persistencia
		leitorDao.insert(novoLeitor);
	}
	
	// busca Leitor por ID
	public Leitor findById(int leitor_id) {
		return leitorDao.find(leitor_id);
	}
	
	// busca todos os leitores no arquivo
	public List<Leitor> findAll(){
		List<Leitor> leitores = leitorDao.findAll();
		if(leitores.isEmpty()) {
			throw new DadosException("Arquivo vazio!");
		}
		return leitores;
	}
	
	// atualiza Leitor
	public void update(int leitor_id, Leitor entity) {
		leitorDao.update(leitor_id, entity);
	}
	
	// remove Leitor
	public void delete(int idLeitor) {
		// pega todo historico de emprestimos do leitor
		List<Emprestimo> historicoEmprestimo = emprestimoDao.findByIdLeitor(idLeitor);
		for (Emprestimo emprestimo : historicoEmprestimo) {
			if (!emprestimo.isDevolvido()) {
				throw new RuntimeException("Leitor com pendências!");
			}
		}
		leitorDao.remove(idLeitor);
	}
}
