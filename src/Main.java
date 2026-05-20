import controller.BibliotecaController;
import model.Emprestimo;
import model.Livro;
import model.Usuario;
import view.MenuView;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        MenuView view = new MenuView();
        BibliotecaController controller = new BibliotecaController(view);
        int opcao;

        do {
            view.exibirMenu();
            try {
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {

                    case 1:
                        view.exibirPrompt("Código");
                        int codigo = Integer.parseInt(scanner.nextLine());

                        view.exibirPrompt("Título");
                        String titulo = scanner.nextLine();

                        view.exibirPrompt("Autor");
                        String autor = scanner.nextLine();

                        view.exibirPrompt("Categoria");
                        String categoria = scanner.nextLine();

                        view.exibirPrompt("Ano");
                        int ano = Integer.parseInt(scanner.nextLine());

                        view.exibirPrompt("Quantidade");
                        int quantidade = Integer.parseInt(scanner.nextLine());

                        controller.cadastrarLivro(new Livro(codigo, titulo, autor, categoria, ano, quantidade));
                        break;

                    case 2:
                        controller.listarLivros();
                        break;

                    case 3:
                        view.exibirPrompt("Termo da busca");
                        String termo = scanner.nextLine();
                        Livro livroEncontrado = controller.buscarLivro(termo);

                        if (livroEncontrado == null) {
                            view.exibirMensagem("Livro não encontrado.");
                        } else {
                            view.exibirMensagem(livroEncontrado.toString());
                        }
                        break;

                    case 4:
                        view.exibirPrompt("Nome");
                        String nome = scanner.nextLine();

                        view.exibirPrompt("Telefone");
                        String telefone = scanner.nextLine();

                        view.exibirPrompt("Email");
                        String email = scanner.nextLine();

                        view.exibirPrompt("Endereço");
                        String endereco = scanner.nextLine();

                        controller.cadastrarUsuario(new Usuario(nome, telefone, email, endereco));
                        break;

                    case 5:
                        controller.listarUsuarios();
                        break;

                    case 6:
                        view.exibirPrompt("Digite o livro");
                        Livro livroEmprestimo = controller.buscarLivro(scanner.nextLine());

                        if (livroEmprestimo == null) {
                            view.exibirMensagem("Livro não encontrado.");
                            break;
                        }

                        view.exibirPrompt("Digite o nome do usuário");
                        String nomeUsuario = scanner.nextLine();
                        Usuario usuarioEncontrado = null;

                        for (Usuario u : controller.getUsuarios()) {
                            if (u.getNome().equalsIgnoreCase(nomeUsuario)) {
                                usuarioEncontrado = u;
                                break;
                            }
                        }

                        if (usuarioEncontrado == null) {
                            view.exibirMensagem("Usuário não encontrado.");
                            break;
                        }

                        controller.realizarEmprestimo(livroEmprestimo, usuarioEncontrado);
                        break;

                    case 7:
                        view.exibirPrompt("Digite o nome do usuário");
                        String usuarioDevolucao = scanner.nextLine();
                        Emprestimo emprestimoEncontrado = null;

                        for (Emprestimo emprestimo : controller.getEmprestimos()) {
                            if (emprestimo.getUsuario().getNome().equalsIgnoreCase(usuarioDevolucao)
                                    && emprestimo.getDataDevolucao() == null) {
                                emprestimoEncontrado = emprestimo;
                                break;
                            }
                        }

                        if (emprestimoEncontrado == null) {
                            view.exibirMensagem("Empréstimo não encontrado.");
                            break;
                        }

                        controller.realizarDevolucao(emprestimoEncontrado);
                        break;

                    case 8:
                        controller.listarEmprestimos();
                        break;

                    case 9:
                        controller.listarAtrasados();
                        break;

                    case 10:
                        controller.relatorioLivrosEmprestados();
                        break;

                    case 11:
                        controller.relatorioUsuariosAtrasados();
                        break;

                    case 12:
                        controller.relatorioLivrosPopulares();
                        break;

                    case 0:
                        view.exibirMensagem("Sistema encerrado.");
                        break;

                    default:
                        view.exibirMensagem("Opção inválida.");
                }

            } catch (NumberFormatException e) {
                view.exibirMensagem("Digite apenas números.");
                opcao = -1;
            }

        } while (opcao != 0);

        scanner.close();
    }
}