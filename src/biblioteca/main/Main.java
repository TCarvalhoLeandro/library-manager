package biblioteca.main;

import java.util.Scanner;

import biblioteca.repository.EmprestimoDAO;
import biblioteca.repository.LeitorDAO;
import biblioteca.repository.LivroDAO;
import biblioteca.repository.impl.EmprestimoCSVDAO;
import biblioteca.repository.impl.LeitorCSVDAO;
import biblioteca.repository.impl.LivroCSVDAO;
import biblioteca.resources.EmprestimoResources;
import biblioteca.resources.LeitorResource;
import biblioteca.resources.LivroResource;
import biblioteca.service.EmprestimoService;
import biblioteca.service.LeitorService;
import biblioteca.service.LivroService;
import biblioteca.ui.MenuEmprestimo;
import biblioteca.ui.MenuLeitor;
import biblioteca.ui.MenuLivros;

public class Main {

	public static void main(String[] args) {

		// instanciar os DAOs
		LivroDAO livroDao = new LivroCSVDAO("livros.csv");
		LeitorDAO leitorDao = new LeitorCSVDAO("leitores.csv");
		EmprestimoDAO emprestimoDao = new EmprestimoCSVDAO("emprestimos.csv");

		// instanciar os services, injetando os DAOs
		LivroService livroService = new LivroService(livroDao);
		LeitorService leitorService = new LeitorService(leitorDao, emprestimoDao);
		EmprestimoService emprestimoService = new EmprestimoService(emprestimoDao, leitorDao, livroDao);

		// instanciar os resources, injetando os services
		LivroResource livroResource = new LivroResource(livroService);
		LeitorResource leitorResource = new LeitorResource(leitorService);
		EmprestimoResources emprestimoResource = new EmprestimoResources(emprestimoService);

		// instanciar os menus, injetando os resources
		MenuLivros menuLivros = new MenuLivros(livroResource);
		MenuLeitor menuLeitor = new MenuLeitor(leitorResource);
		MenuEmprestimo menuEmprestimo = new MenuEmprestimo(emprestimoResource);

		Scanner sc = new Scanner(System.in);
		int opcao = -1;
		while (opcao != 0) {
			System.out.println("===============  BIBLIOTECA  ===============");
			System.out.println("1 - Gerenciar Livro");
			System.out.println("2 - Gerenciar Leitor");
			System.out.println("3 - Gerenciar Empréstimo");
			System.out.println("0 - Sair");
			opcao = sc.nextInt();

			switch (opcao) {
			case 1:
				menuLivros.telaLivro();
				break;
			case 2:
				menuLeitor.telaLeitor();
				break;
			case 3:
				menuEmprestimo.telaEmprestimo();
				break;
			case 0:
				System.out.println("Encerrando o sistema...");
				break;
			default:
				System.out.println("Opção inválida!");
			}
		}

		sc.close();

	}
}
