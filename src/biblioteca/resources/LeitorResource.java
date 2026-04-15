package biblioteca.resources;

import java.util.Scanner;

import biblioteca.entities.Leitor;
import biblioteca.service.LeitorService;

public class LeitorResource {
	
	private LeitorService leitorService;
	
	// injecao de dependencia
	public LeitorResource(LeitorService leitorService) {
		super();
		this.leitorService = leitorService;
	}

	Scanner sc = new Scanner(System.in);

	public void insert(Leitor entity) {
		leitorService.insert(entity);
	}
	
	public void delete(int id) {
		leitorService.delete(id);
	}

}

