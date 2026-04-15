package biblioteca.ui;

import java.util.Scanner;

import biblioteca.entities.Livro;
import biblioteca.resources.LivroResource;

public class MenuLivros {

	private LivroResource livroResource;

	// injecao de dependencia
	public MenuLivros(LivroResource livroResource) {
		this.livroResource = livroResource;
	}

	Scanner sc = new Scanner(System.in);
		
	public void telaLivro() {

		int opcao = -1;
		while (opcao != 0) {
			System.out.println("===============  LIVRO  ===============");
			System.out.println("1 - Cadastrar Livro");
			System.out.println("2 - Deletar Livro");
			System.out.println("0 - Voltar ao Menu Anterior");
			opcao = sc.nextInt();

			switch (opcao) {
			case 1:
				sc.nextLine();
				System.out.print("Titulo: ");
				String titulo = sc.nextLine();
				System.out.print("Autor: ");
				String autor = sc.nextLine();
				System.out.print("Ano:  ");
				int ano = sc.nextInt();

				Livro novoLivro = new Livro(titulo, autor, ano);

				try {
					livroResource.insert(novoLivro);
					System.out.println("Livro cadastrado com sucesso!");
				} catch (RuntimeException e) {
					System.out.println("Atenção: " + e.getMessage());
				}

				break;
			case 2:
				System.out.print("Digite o ID do Leitor: ");
				int idLivro = sc.nextInt();

				try {
					livroResource.delete(idLivro);
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


 