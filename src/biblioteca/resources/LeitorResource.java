package biblioteca.resources;

import java.util.Scanner;

import biblioteca.entities.Leitor;
import biblioteca.service.LeitorService;

public class LeitorResource {
	
	private LeitorService leitorService;
	Scanner sc = new Scanner(System.in);

	
	public void telaLeitor() {
		
		int opcao = 0;
		while (opcao != 0) {
			System.out.println("===============  LEITOR  ===============");
			System.out.println("1 - Cadastrar Leitor");
			System.out.println("2 - Deletar Leitor");
			System.out.println("0 - Voltar ao Menu Anterior");
			opcao = sc.nextInt();

			switch (opcao) {
			case 1:
				System.out.print("Nome: ");
				String name = sc.nextLine();
				System.out.print("CPF: ");
				String cpf = sc.nextLine();
				System.out.print("Email:  ");
				String email = sc.nextLine();
				
				Leitor novoLeitor = new Leitor(name, cpf, email);

				try {
					int id = leitorService.salvarLeitor(novoLeitor);
					System.out.println("Leitor cadastrado com sucesso! ID: " + id);
				} catch (RuntimeException e) {
					System.out.println("Atenção: " + e.getMessage());
				}

				break;
			case 2:
				System.out.print("Digite o ID do Leitor: ");
				int idLeitor = sc.nextInt();

				try {
					leitorService.deletarLeitor(idLeitor);
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

