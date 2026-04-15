package biblioteca.entities;

public class Leitor{
	
	private int id;
	private String nome;
	private String cpf;
	private String email;
	
	public Leitor() {
	}
	
	public Leitor(String nome, String cpf, String email) {
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
	}
	
	public Leitor(int id, String nome, String cpf, String email) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String toCSV() {
		StringBuilder sb = new StringBuilder();
		sb.append(id).append(";")
		.append(nome).append(";")
		.append(cpf).append(";")
		.append(email);
		
		return sb.toString();
	}
	
	@Override
	public String toString() {
		return id + " - " + nome + " - (" + cpf + ") - " + email;
	}
}
