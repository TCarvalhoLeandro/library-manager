package biblioteca.ui;

import java.util.List;
import java.util.Scanner;

import biblioteca.domainException.DadosException;
import biblioteca.resources.EmprestimoResources;

public class MenuEmprestimo {

	private EmprestimoResources emprestimoResource;
	
	// injecao de dependencia
	public MenuEmprestimo(EmprestimoResources emprestimoResource) {
		this.emprestimoResource = emprestimoResource;
	}

	Scanner sc = new Scanner(System.in);

	public void telaEmprestimo() {

		int opcao = -1;
		while (opcao != 0) {
			System.out.println("===============  EMPRESTIMO  ===============");
			System.out.println("1 - Realizar Empréstimo");
			System.out.println("2 - Realizar Devolução");
			System.out.println("3 - Pesquisar Empréstimo ");
			System.out.println("4 - Listar Empréstimos Ativos");
			System.out.println("5 - Listar Empréstimos");
			System.out.println("0 - Voltar ao Menu Anterior");
			opcao = sc.nextInt();

			switch (opcao) {
			case 1:
				// insere emprestimo
				System.out.print("Digite o ID do Leitor: ");
				int idLeitor = sc.nextInt();
				System.out.print("Digite o ID do Livro: ");
				int idLivro = sc.nextInt();

				try {
					emprestimoResource.realizarEmprestimo(idLeitor, idLivro);
					System.out.println("Empréstimo realizado com sucesso!");
				} catch (RuntimeException e) {
					System.out.println("Atenção: " + e.getMessage());
				}

				break;
			case 2:
				// devolucao
				System.out.print("Digite o ID do Empréstimo: ");
				int idEmprestimo = sc.nextInt();

				try {
					emprestimoResource.realizarDevolucao(idEmprestimo);
				} catch (RuntimeException e) {
					System.out.println("Atenção: " + e.getMessage());
				}
				break;
			case 3:
				// busca ID
				System.out.println("Digite o ID do Emprestimo: ");
				int emprestimo_id = sc.nextInt();
				try {
					System.out.println(emprestimoResource.findById(emprestimo_id));
					System.out.println();
				}
				catch(RuntimeException e) {
					throw new DadosException(e.getMessage());
				}
				break;
			case 4:
				// lista emprestimos ativos
				System.out.println();
				System.out.println("---------- Emprestimos Ativos ----------");
				List<String> emprestimosAtivos = emprestimoResource.findAllActive();
				for(String obj: emprestimosAtivos) {
					System.out.println(obj);
				}
				System.out.println();
				break;
			case 5:
				// lista todos os emprestimos
				System.out.println();
				System.out.println("---------- Emprestimos ----------");
				emprestimosAtivos = emprestimoResource.findAll();
				for(String obj: emprestimosAtivos) {
					System.out.println(obj);
				}
				System.out.println();
				break;
			case 0:
				break;
			default:
				System.out.println("Opção inválida!");
			}
		}
	}
}
