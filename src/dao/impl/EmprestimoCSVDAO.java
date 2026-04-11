package dao.impl;

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
import biblioteca.entities.Leitor;
import biblioteca.entities.Livro;
import dao.EmprestimoDAO;
import dao.LeitorDAO;
import dao.LivroDAO;

public class EmprestimoCSVDAO implements EmprestimoDAO {

	private String caminhoArquivo;
	private LeitorDAO leitorDao;
	private LivroDAO livroDao;


	
	public EmprestimoCSVDAO(String caminhoArquivo, LeitorDAO leitorDao, LivroDAO livroDao) {
		this.caminhoArquivo = caminhoArquivo;
		this.leitorDao = leitorDao;
		this.livroDao = livroDao;
	}

	// INSERE EMPRESTIMO
	@Override
	public void insert(Emprestimo emprestimo) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo, true))) {
			bw.write(emprestimo.toCSV()); // escreve os dados do livro no arquivo
			bw.newLine();// pula para a proxima linha
		} catch (IOException e) {
			throw new DadosException("Erro ao salvar emprestimo. " + e.getMessage());
		}
	}

	// BUSCA POR ID
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

	// BUSCA TODOS EMPRESTIMOS NO ARQUIVO E CONVERTE EM OBJETO EMPRESTIMO
	@Override
	public List<Emprestimo> findAll() {
		List<Emprestimo> emprestimos = new ArrayList<Emprestimo>();// o arquivo pode ter varios livros entao criamos uma lista
		try (BufferedReader br = new BufferedReader(new FileReader(this.caminhoArquivo))) {
			String line;
			while ((line = br.readLine()) != null) {
				
				String[] fields = line.split(";");
				
				int id = Integer.parseInt(fields[0]);
				
				int leitorId = Integer.parseInt(fields[1]);
				
				int livroId = Integer.parseInt(fields[2]);
				
				LocalDate dataEmprestimo = LocalDate.parse(fields[3]);
				
				LocalDate dataDevolucao;
				if(!fields[4].isEmpty()) {
					dataDevolucao = LocalDate.parse(fields[4]);
				}
				else { 
					dataDevolucao = null;
				}
				
				boolean status = Boolean.parseBoolean(fields[5]);
				
				// instancia leitor e livro a partir do id
				try {
					Leitor leitor = leitorDao.find(leitorId);
					
					Livro livro = livroDao.find(livroId);
					
					emprestimos.add(new Emprestimo(id, leitor, livro, dataEmprestimo, dataDevolucao,  status));
				}
				catch(Exception e) {
					throw new DadosException("Erro ao processar linha de empréstimo!");
				}
			}
		} catch (IOException e) {
			throw new DadosException("Erro ao carregar leitores do arquivo");
		}
		return emprestimos;
	}
	
	// FILTRA EMPRESTIMOS POR ID DO LEITOR 
	public List<Emprestimo> findByIdLeitor(int id){
		List<Emprestimo> emprestimos = findAll();
		List<Emprestimo> emprestimosById = new ArrayList<Emprestimo>();
		for(int i = 0;i < emprestimos.size();i++) {
			if(emprestimos.get(i).getLeitor().getId() == id) {
				emprestimosById.add(emprestimos.get(i));
			}
		}
		return emprestimosById;
	}

	// ATUALIZA EMPRESTIMO
	@Override
	public void update(Emprestimo emprestimo) {
		List<Emprestimo> emprestimos = this.findAll();
		for(int i = 0; i < emprestimos.size(); i++) {
			if(emprestimos.get(i).getId() == emprestimo.getId()) {
				emprestimos.set(i, emprestimo);// Substitui o emprestimo antigo pelo novo na posição 'i'
				break;
			}
		}
		rewriteFiles(emprestimos);
	}
	
	// REESCREVE DADOS NO ARQUIVO
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
