package biblioteca.repository.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import biblioteca.domainException.DadosException;
import biblioteca.entities.Leitor;
import biblioteca.repository.LeitorDAO;

public class LeitorCSVDAO implements LeitorDAO {

	private String caminhoArquivo;

	public LeitorCSVDAO(String caminhoArquivo) {
		this.caminhoArquivo = caminhoArquivo;
	}

	// INSERE LEITOR
	@Override
	public void insert(Leitor leitor) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo, true))) {
			bw.write(leitor.toCSV()); // escreve os dados do livro no arquivo
			bw.newLine();// pula para a proxima linha
		} catch (IOException e) {
			throw new DadosException("Erro ao salvar leitor. " + e.getMessage());
		}

	}

	// BUSCA POR ID
	@Override
	public Leitor find(int id) {
		List<Leitor> leitores = this.findAll(); // carrega todos os leitores
		for (int i = 0; i < leitores.size(); i++) {
			if (leitores.get(i).getId() == id) {
				return leitores.get(i);
			}
		}
		throw new DadosException("Leitor não encontrado.");
	}

	// BUSCA TODOS LEITORES NO ARQUIVO E CONVERTE EM OBJETO LEITOR
	@Override
	public List<Leitor> findAll() {
		List<Leitor> leitores = new ArrayList<Leitor>();// o arquivo pode ter varios livros entao criamos uma lista
		try (BufferedReader br = new BufferedReader(new FileReader(this.caminhoArquivo))) {
			String line;
			while ((line = br.readLine()) != null) {
				if(line.trim().isEmpty()) {
					continue;
				}
				String[] fields = line.split(";");
				int id = Integer.parseInt(fields[0]);
				String nome = fields[1];
				String cpf = fields[2];
				String email = fields[3];

				leitores.add(new Leitor(id, nome, cpf, email));
			}
		} catch (IOException e) {
			throw new DadosException("Erro ao carregar leitores do arquivo");
		}
		return leitores;

	}

	// ATUALIZA LEITOR
	@Override
	public void update(int leitor_id, Leitor leitor) {
		List<Leitor> leitores = this.findAll(); // carrega todos os livros
		for (int i = 0; i < leitores.size(); i++) {
			if (leitores.get(i).getId() == leitor_id) {
				leitor.setId(leitor_id);
				leitores.set(i, leitor); // Substitui o livro antigo pelo novo na posição 'i'
				break; // Já atualizamos, podemos parar de procurar!
			}
		}
		rewriteFiles(leitores);

	}

	// REMOVE LEITOR
	@Override
	public void remove(int id) {
		List<Leitor> leitor = this.findAll(); // carrega todos os livros
		for (int i = 0; i < leitor.size(); i++) {
			if (leitor.get(i).getId() == id) {
				leitor.remove(i);
				break;
			}
		}
		rewriteFiles(leitor);
	}

	// REESCREVE DADOS NO ARQUIVO
	private void rewriteFiles(List<Leitor> leitor) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo))) {
			for (Leitor leitores : leitor) {
				bw.write(leitores.toCSV());
				bw.newLine();
			}
		} catch (IOException e) {
			throw new DadosException("Erro ao atualizar a lista." + e.getMessage());
		}
	}

}
