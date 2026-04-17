package biblioteca.repositories.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import biblioteca.domainException.DadosException;
import biblioteca.entities.Emprestimo;
import biblioteca.repositories.EmprestimoDAO;

public class EmprestimoCSVDAO implements EmprestimoDAO {

	private String caminhoArquivo;

	public EmprestimoCSVDAO(String caminhoArquivo) {
		this.caminhoArquivo = caminhoArquivo;
	}

	// insere Emprestimo
	@Override
	public void insert(Emprestimo emprestimo) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo, true))) {
			bw.write(emprestimo.toCSV()); // escreve os dados do livro no arquivo
			bw.newLine();// pula para a proxima linha
		} catch (IOException e) {
			throw new DadosException("Erro ao salvar emprestimo. " + e.getMessage());
		}
	}

	// busca por id
	@Override
	public Emprestimo findById(int id) {
		List<Emprestimo> emprestimos = this.findAll(); // carrega todos os emprestimos
		for (int i = 0; i < emprestimos.size(); i++) {
			if (emprestimos.get(i).getId() == id) {
				return emprestimos.get(i);
			}
		}
		throw new DadosException("Emprestimo não encontrado.");
	}

	// busca todos os emprestimos no arquivo
	@Override
	public List<Emprestimo> findAll() {
		List<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
		try (BufferedReader br = new BufferedReader(new FileReader(this.caminhoArquivo))) {
			String line;
			while ((line = br.readLine()) != null) {
				// se o arquivo estiver vazio ignora e continua
				if(line.trim().isEmpty()) {
					continue;
				}
			
				String[] fields = line.split(";", -1);// obriga o Java a ler as colunas vazias

				int id = Integer.parseInt(fields[0]);
				int leitorId = Integer.parseInt(fields[1]);
				int livroId = Integer.parseInt(fields[2]);
				boolean status = Boolean.parseBoolean(fields[3]);
				LocalDate dataEmprestimo = LocalDate.parse(fields[4]);
				LocalDate dataDevolucao;
				if (!fields[5].trim().isEmpty()) {
					dataDevolucao = LocalDate.parse(fields[5]);
				} else {
					dataDevolucao = null;
				}
				try {
					// adiciona na lista
					emprestimos.add(new Emprestimo(id, leitorId, livroId, status, dataEmprestimo, dataDevolucao ));
				} catch (Exception e) {
					throw new DadosException("Erro ao processar linha de empréstimo!");
				}
			}
		} catch (IOException e) {
			throw new DadosException("Erro ao carregar leitores do arquivo");
		}
		return emprestimos;
	}

	//busca Emprestimo por ID Leitor
	public List<Emprestimo> findByIdLeitor(int id) {
		List<Emprestimo> emprestimos = findAll();
		List<Emprestimo> emprestimosById = new ArrayList<Emprestimo>();
		for(Emprestimo obj: emprestimos) {
			if(obj.getLeitor_id() == id) {
				emprestimosById.add(obj);
			}
		}
		return emprestimosById;
	}

	// busca emprestimo por ID Livro
	public List<Emprestimo> findByIdLivro(int id) {
		List<Emprestimo> emprestimos = findAll();
		List<Emprestimo> emprestimosById = new ArrayList<Emprestimo>();
		for(Emprestimo obj: emprestimos) {
			if(obj.getLivro_id() == id) {
				emprestimosById.add(obj);
			}
		}
		return emprestimosById;
	}

	// atualiza emprestimo
	@Override
	public void update(Emprestimo emprestimo) {
		List<Emprestimo> emprestimos = this.findAll();
		for (int i = 0; i < emprestimos.size(); i++) {
			if (emprestimos.get(i).getId() == emprestimo.getId()) {
				emprestimos.set(i, emprestimo);// Substitui o emprestimo antigo pelo novo na posição 'i'
				break;
			}
		}
		rewriteFiles(emprestimos);
	}

	// reescreve dados 
	private void rewriteFiles(List<Emprestimo> emprestimo) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo))) {
			for (Emprestimo emprestimos : emprestimo) {
				bw.write(emprestimos.toCSV());
				bw.newLine();
			}
		} catch (IOException e) {
			throw new DadosException("Erro ao atualizar a lista." + e.getMessage());
		}
	}
}
