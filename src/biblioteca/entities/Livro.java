package biblioteca.entities;

public class Livro {

	private static int contador = 0;

	private int id;
	private String titulo;
	private String autor;
	private int ano;
	private boolean disponivel;

	
	public Livro() {
		
	}
	public Livro(String titulo, String autor, int ano) {
		this.id = 0;
		this.titulo = titulo;
		this.autor = autor;
		this.ano = ano;
		this.disponivel = true;
		
	}
	public Livro(int id, String titulo, String autor, int ano, boolean disponivel) {
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.ano = ano;
		this.disponivel = disponivel;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}

	public static int getContador() {
		return contador;
	}

	public static void setContador(int cont) {
		contador = cont;
	}

	// salvar em csv
	public String toCSV() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(id).append(";")
		.append(titulo).append(";")
		.append(autor).append(";")
		.append(ano).append(";")
		.append(disponivel);
		
		return sb.toString();
	}

	@Override
	public String toString() {
		return id + " - " + titulo + " - " + autor + " - " + ano;
	}
}
