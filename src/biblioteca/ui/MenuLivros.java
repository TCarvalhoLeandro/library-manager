package biblioteca.ui;

import java.util.List;
import java.util.Scanner;

import biblioteca.domainException.DadosException;
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
			System.out.println("2 - Pesquisar Livro");
			System.out.println("3 - Listar Livros");
			System.out.println("4 - Atualizar Livro");
			System.out.println("5 - Excluir Livro");
			System.out.println("0 - Voltar ao Menu Anterior");
			opcao = sc.nextInt();

			switch (opcao) {
			case 1:
				try {
					livroResource.insert(telaCadastro());
					System.out.println("Livro cadastrado com sucesso!");
				} catch (RuntimeException e) {
					System.out.println("Atenção: " + e.getMessage());
				}

				break;
			case 2: 
				System.out.print("Digite o ID do Livro: ");
				int idLivro = sc.nextInt();
				try {
					Livro livro = livroResource.findById(idLivro);
					System.out.println(livro);
				} catch (RuntimeException e) {
					System.out.println("Atenção: " + e.getMessage());
				}
				break;
			case 3:
				try {
					List<Livro> livros = livroResource.findAll();
					System.out.println();
					for(Livro obj: livros) {
						System.out.println(obj);
					}
					System.out.println();
				}
				catch(DadosException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 4:
				// pega o id do livro a ser atualizado
				System.out.println("Digite o ID do Livro: ");
				idLivro = sc.nextInt();
				System.out.println(livroResource.findById(idLivro));
				
				System.out.println();
				Livro updateLivro = telaCadastro();
				// chama o metodo para atualizar
				livroResource.update(idLivro, updateLivro);
				System.out.println("Livro atualizado com sucesso!");
				break;
			case 5:
				System.out.print("Digite o ID do Livro: ");
				idLivro = sc.nextInt();

				try {
					livroResource.delete(idLivro);
					System.out.println("Livro excluído com sucesso!");
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
	
	public Livro telaCadastro() {
		
		sc.nextLine();
		System.out.print("Titulo: ");
		String titulo = sc.nextLine();
		System.out.print("Autor: ");
		String autor = sc.nextLine();
		System.out.print("Ano:  ");
		int ano = sc.nextInt();

		Livro novoLivro = new Livro(titulo, autor, ano);
		return novoLivro;
	}
}


 