package biblioteca.ui;

import java.util.List;
import java.util.Scanner;

import biblioteca.entities.Leitor;
import biblioteca.resources.LeitorResource;

public class MenuLeitor {

	private LeitorResource leitorResource;

	// injecao de dependencia
	public MenuLeitor(LeitorResource leitorResource) {
		this.leitorResource = leitorResource;
	}

	Scanner sc = new Scanner(System.in);

	public void telaLeitor() {

		int opcao = -1;
		while (opcao != 0) {
			System.out.println("===============  LEITOR  ===============");
			System.out.println("1 - Cadastrar Leitor");
			System.out.println("2 - Pesquisar Leitor");
			System.out.println("3 - Listar Leitores");
			System.out.println("4 - Atualizar Leitor");
			System.out.println("5 - Deletar Leitor");
			System.out.println("0 - Voltar ao Menu Anterior");
			opcao = sc.nextInt();

			switch (opcao) {
			case 1:
				sc.nextLine();
				System.out.print("Nome: ");
				String name = sc.nextLine();
				System.out.print("CPF: ");
				String cpf = sc.nextLine();
				System.out.print("Email:  ");
				String email = sc.nextLine();

				Leitor novoLeitor = new Leitor(name, cpf, email);

				try {
					leitorResource.insert(novoLeitor);
					System.out.println("Leitor cadastrado com sucesso!");
				} catch (RuntimeException e) {
					System.out.println("Atenção: " + e.getMessage());
				}

				break;
			case 2:
				System.out.print("Digite o ID do Leitor: ");
				int idLeitor = sc.nextInt();
				try {
					Leitor leitor = leitorResource.findById(idLeitor);
					System.out.println(leitor);
				} catch (RuntimeException e) {
					System.out.println("Atenção: " + e.getMessage());
				}
				break;
			case 3:
				System.out.println();
				List<Leitor> leitores = leitorResource.findAll();
				for (Leitor obj : leitores) {
					System.out.println(obj);
				}
				System.out.println();
				break;
			case 4:
				// pega o id do Leitor a ser atualizado
				System.out.println("Digite o ID do Leitor: ");
				idLeitor = sc.nextInt();
				System.out.println(leitorResource.findById(idLeitor));
				System.out.println();
				Leitor updateLeitor = telaCadastro();
				// chama o metodo para atualizar
				leitorResource.update(idLeitor, updateLeitor);
				System.out.println("Leitor atualizado com sucesso!");
				break;
			case 5:
				System.out.print("Digite o ID do Leitor: ");
				idLeitor = sc.nextInt();
				try {
					leitorResource.delete(idLeitor);
					System.out.println("Leitor excluído com sucesso!");
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

	public Leitor telaCadastro() {
		sc.nextLine();
		System.out.print("Nome: ");
		String nome = sc.nextLine();
		System.out.print("CPF: ");
		String cpf = sc.nextLine();
		System.out.print("Email:  ");
		String email = sc.nextLine();

		Leitor novoLeitor = new Leitor(nome, cpf, email);
		return novoLeitor;
	}
}
