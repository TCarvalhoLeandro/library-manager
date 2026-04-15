package biblioteca.entities;

import java.time.LocalDate;

public class Emprestimo{

	private int id;
	private int leitor_id;
	private int livro_id;
	private boolean devolvido;
	private LocalDate dataEmprestimo;
	private LocalDate dataDevolucao;

	public Emprestimo() {
		
	}
	
	public Emprestimo(int leitor_id, int livro_id, boolean devolvido, LocalDate dataEmprestimo) {
		this.leitor_id = leitor_id;
		this.livro_id = livro_id;
		this.devolvido = devolvido;
		this.dataEmprestimo = dataEmprestimo;
		
	}

	public Emprestimo(int id, int leitor_id, int livro_id, boolean devolvido, LocalDate dataEmprestimo,
			LocalDate dataDevolucao) {
		this.id = id;
		this.leitor_id = leitor_id;
		this.livro_id = livro_id;
		this.devolvido = devolvido;
		this.dataEmprestimo = dataEmprestimo;
		this.dataDevolucao = dataDevolucao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLeitor_id() {
		return leitor_id;
	}

	public void setLeitor_id(int leitor_id) {
		this.leitor_id = leitor_id;
	}

	public int getLivro_id() {
		return livro_id;
	}

	public void setLivro_id(int livro_id) {
		this.livro_id = livro_id;
	}

	public LocalDate getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(LocalDate dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public LocalDate getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(LocalDate dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public boolean isDevolvido() {
		return devolvido;
	}

	public void setDevolvido(boolean devolvido) {
		this.devolvido = devolvido;
	}
	
	public String toCSV() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(id).append(";")
		.append(leitor_id).append(";")
		.append(livro_id).append(";")
		.append(devolvido).append(";")
		.append(dataEmprestimo).append(";");
		if(dataDevolucao != null)
			sb.append(dataDevolucao);
		
		return sb.toString();
	}
}