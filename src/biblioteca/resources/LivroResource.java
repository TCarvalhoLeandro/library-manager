package biblioteca.resources;

import java.util.List;
import java.util.Scanner;

import biblioteca.entities.Livro;
import biblioteca.service.LivroService;

public class LivroResource {

	private LivroService livroService;
	Scanner sc = new Scanner(System.in);
	
	// injecao de depencia
	public LivroResource(LivroService livroService) {
		this.livroService = livroService;
	}

	public void insert(Livro entity) {
		livroService.insert(entity);
	}
	
	public Livro findById(int livro_id) {
		return livroService.findById(livro_id);
	}
	
	public List<Livro> findAll(){
		return livroService.findAll();
	}
	
	public void update(int id, Livro livro) {
		livroService.update(id, livro);
	}
	
	public void delete(int id) {
		livroService.delete(id);
	}
}
