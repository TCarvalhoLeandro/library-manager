package biblioteca.resources;

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
	
	public void delete(int id) {
		livroService.delete(id);
	}
}
