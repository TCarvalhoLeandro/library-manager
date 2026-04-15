package biblioteca.repository.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import biblioteca.domainException.DadosException;
import biblioteca.entities.Livro;
import biblioteca.repository.LivroDAO;

public class LivroCSVDAO implements LivroDAO {

	private String caminhoArquivo;

	public LivroCSVDAO(String caminhoArquivo) {
		this.caminhoArquivo = caminhoArquivo;
	}

	// insere Livro
	@Override
	public void insert(Livro livro) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo, true))) {
			bw.write(livro.toCSV()); // escreve os dados do livro no arquivo
			bw.newLine();// pula para a proxima linha
		} 
		catch (IOException e) {
			throw new DadosException("Erro ao salvar o livro. " + e.getMessage());
		}
	}

	// busca por ID
	@Override
	public Livro find(int id) {
		List<Livro> livros = this.findAll(); // carrega todos os livros
		for (int i = 0; i < livros.size(); i++) {
			if (livros.get(i).getId() == id) {
				return livros.get(i);
			}
		}
		throw new DadosException("Livro não encontrado.");
	}

	// busca todos livros retorna lista Livro
	@Override
	public List<Livro> findAll() {
		List<Livro> livros = new ArrayList<Livro>();// o arquivo pode ter varios livros entao criamos uma lista
		try (BufferedReader br = new BufferedReader(new FileReader(this.caminhoArquivo))) {
			
			String line;
			while ((line = br.readLine()) != null) {
				// garante salvar livro se o arquivo estiver vazio
				if(line.trim().isEmpty()) {
					continue;
				}
				String[] fields = line.split(";");
				
				int id = Integer.parseInt(fields[0]);
				String nome = fields[1];
				String autor = fields[2];
				int ano = Integer.parseInt(fields[3]);
				boolean disponivel = Boolean.parseBoolean(fields[4]);

				livros.add(new Livro(id, nome, autor, ano, disponivel));
			}
		} 
		catch (IOException e) {
			throw new DadosException("Erro ao carregar livros do arquivo.");
		}
		return livros;
	}

	// atualiza Livro
	@Override
	public void update(int livro_id, Livro livro) {
		List<Livro> livros = this.findAll(); // carrega todos os livros
		for (int i = 0; i < livros.size(); i++) {
			if (livros.get(i).getId() == livro_id) {
				livro.setId(livro_id);// transfere o ID correto para o objeto novo
				livros.set(i, livro); // Substitui o livro antigo pelo novo na posição 'i'
				break;// para de procurar
			}
		}
		rewriteFiles(livros);
	}

	// remove Livro
	@Override
	public void remove(int id) {
		List<Livro> livros = this.findAll(); // carrega todos os livros
		for (int i = 0; i < livros.size(); i++) {
			if (livros.get(i).getId() == id) {
				livros.remove(i);
				break;
			}
		}
		rewriteFiles(livros);
	}

	// REESCREVE DADOS NO ARQUIVO
	private void rewriteFiles(List<Livro> livros) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo))) {
			for (Livro livro : livros) {
				bw.write(livro.toCSV());
				bw.newLine();
			}
		} 
		catch (IOException e) {
			throw new DadosException("Erro ao atualizar a lista." + e.getMessage());
		}
	}

}
