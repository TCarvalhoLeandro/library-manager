package biblioteca.resources;

import java.util.List;
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
	
	public Leitor findById(int leitor_id) {
		return leitorService.findById(leitor_id);
	}
	
	public List<Leitor> findAll(){
		return leitorService.findAll();
	}
	
	public void update(int leitor_id, Leitor leitor) {
		leitorService.update(leitor_id, leitor);
	}
	
	public void delete(int id) {
		leitorService.delete(id);
	}

}

