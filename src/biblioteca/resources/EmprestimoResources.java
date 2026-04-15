package biblioteca.resources;

import java.util.Scanner;

import biblioteca.service.EmprestimoService;

public class EmprestimoResources {

	private EmprestimoService emprestimoService;

	// injecao de dependencia
	public EmprestimoResources(EmprestimoService emprestimoService) {
		this.emprestimoService = emprestimoService;
	}

	Scanner sc = new Scanner(System.in);

	public void realizarEmprestimo(int leitor_id, int livro_id) {
		emprestimoService.realizarEmprestimo(leitor_id, livro_id);
	}

	public void realizarDevolucao(int emprestimo_id) {
		emprestimoService.realizarDevolucao(emprestimo_id);
	}
}
