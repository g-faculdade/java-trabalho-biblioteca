package view;

public class MenuView {

    public void exibirMenu() {
        System.out.println("\n===== BIBLIOTECA =====");
        System.out.println("1 - Cadastrar livro");
        System.out.println("2 - Listar livros");
        System.out.println("3 - Buscar livro");
        System.out.println("4 - Cadastrar usuário");
        System.out.println("5 - Listar usuários");
        System.out.println("6 - Realizar empréstimo");
        System.out.println("7 - Realizar devolução");
        System.out.println("8 - Listar empréstimos");
        System.out.println("9 - Listar atrasados");
        System.out.println("10 - Relatório: livros emprestados");
        System.out.println("11 - Relatório: usuários com atraso");
        System.out.println("12 - Relatório: livros mais populares");
        System.out.println("0 - Sair");
        System.out.print("Escolha: ");
    }

    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    public void exibirSeparador() {
        System.out.println("-------------------");
    }

    public void exibirPrompt(String label) {
        System.out.print(label + ": ");
    }

    public void exibirLivroEmprestado(String titulo, String usuario, String dataPrevista) {
        System.out.println("Livro: " + titulo);
        System.out.println("Usuário: " + usuario);
        System.out.println("Devolução prevista: " + dataPrevista);
    }

    public void exibirUsuarioAtrasado(String usuario, String titulo, int diasAtraso) {
        System.out.println("Usuário: " + usuario);
        System.out.println("Livro: " + titulo);
        System.out.println("Dias de atraso: " + diasAtraso);
    }

    public void exibirLivroPopular(int posicao, String titulo, int contagem) {
        System.out.println(posicao + "º " + titulo + " — " + contagem + " empréstimo(s)");
    }
}