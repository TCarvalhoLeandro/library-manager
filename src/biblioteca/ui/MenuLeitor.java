package biblioteca.ui;

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
			System.out.println("2 - Deletar Leitor");
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
					leitorResource.delete(idLeitor);
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
