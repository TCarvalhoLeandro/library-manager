package biblioteca.resources;

import java.util.Scanner;

import biblioteca.service.EmprestimoService;

public class EmprestimoResources {

	private EmprestimoService emprestimoService;
	Scanner sc = new Scanner(System.in);

	public void telaEmprestimo() {

		int opcao = 0;
		while (opcao != 0) {
			System.out.println("===============  EMPRESTIMO  ===============");
			System.out.println("1 - Realizar Empréstimo");
			System.out.println("2 - Realizar devolução");
			System.out.println("0 - Voltar ao Menu Anterior");
			opcao = sc.nextInt();

			switch (opcao) {
			case 1:
				System.out.print("Digite o ID do Leitor: ");
				int idLeitor = sc.nextInt();
				System.out.print("Digite o ID do Livro: ");
				int idLivro = sc.nextInt();

				try {
					int id = emprestimoService.realizarEmprestimo(idLeitor, idLivro);
					System.out.println("Empréstimo realizado com sucesso! ID: " + id);
				} catch (RuntimeException e) {
					System.out.println("Atenção: " + e.getMessage());
				}

				break;
			case 2:
				System.out.print("Digite o ID do Empréstimo: ");
				int idEmprestimo = sc.nextInt();

				try {
					emprestimoService.realizarDevolucao(idEmprestimo);
				} catch (RuntimeException e) {
					System.out.println("Atenção: " + e.getMessage());
				}
				break;
			case 0:
				break;
			default:
				System.out.println("Opção inválida!");
			}
		}
	}
}
