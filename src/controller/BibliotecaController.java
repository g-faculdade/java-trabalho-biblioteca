package controller;

import java.time.LocalDate;
import java.util.ArrayList;

import model.Emprestimo;
import model.Livro;
import model.Pessoa;
import model.Usuario;
import view.MenuView;

public class BibliotecaController {

    private ArrayList<Livro> livros;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Emprestimo> emprestimos;
    private MenuView view;

    public BibliotecaController(MenuView view) {

        this.view = view;
        livros = new ArrayList<>();
        usuarios = new ArrayList<>();
        emprestimos = new ArrayList<>();

        carregarDadosTeste();
    }

    public void cadastrarLivro(Livro livro) {
        livros.add(livro);
        view.exibirMensagem("Livro cadastrado com sucesso.");
    }

    public void listarLivros() {

        if (livros.isEmpty()) {
            view.exibirMensagem("Nenhum livro cadastrado.");
            return;
        }

        for (Livro livro : livros) {
            view.exibirMensagem(livro.toString());
            view.exibirSeparador();
        }
    }

    public Livro buscarLivro(String termo) {

        for (Livro livro : livros) {
            if (livro.pesquisar(termo)) {
                return livro;
            }
        }

        return null;
    }

    public void cadastrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
        view.exibirMensagem("Usuário cadastrado com sucesso.");
    }

    public void listarUsuarios() {

        if (usuarios.isEmpty()) {
            view.exibirMensagem("Nenhum usuário cadastrado.");
            return;
        }

        for (Pessoa pessoa : usuarios) {
            view.exibirMensagem(pessoa.exibirInformacoes());
            view.exibirSeparador();
        }
    }

    public Usuario buscarUsuarioPorNome(String nome) {

        for (Usuario usuario : usuarios) {
            if (usuario.getNome().equalsIgnoreCase(nome)) {
                return usuario;
            }
        }

        return null;
    }

    public Emprestimo buscarEmprestimoAtivoPorUsuario(String nome) {

        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getUsuario().getNome().equalsIgnoreCase(nome)
                    && emprestimo.getDataDevolucao() == null) {
                return emprestimo;
            }
        }

        return null;
    }

    public void realizarEmprestimo(Livro livro, Usuario usuario) {

        if (livro.getQuantidade() <= 0) {
            view.exibirMensagem("Livro indisponível.");
            return;
        }

        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getUsuario().equals(usuario) && emprestimo.getDataDevolucao() == null) {
                view.exibirMensagem("Usuário já possui empréstimo.");
                return;
            }
        }

        Emprestimo emprestimo = new Emprestimo(livro, usuario, LocalDate.now(), LocalDate.now().plusDays(7));
        emprestimos.add(emprestimo);
        livro.setQuantidade(livro.getQuantidade() - 1);
        view.exibirMensagem("Empréstimo realizado com sucesso.");
    }

    public void realizarDevolucao(Emprestimo emprestimo) {

        if (emprestimo.getDataDevolucao() != null) {
            view.exibirMensagem("Livro já devolvido.");
            return;
        }

        emprestimo.realizarDevolucao();
        emprestimo.getLivro().setQuantidade(emprestimo.getLivro().getQuantidade() + 1);
        view.exibirMensagem("Devolução realizada com sucesso.");

        if (emprestimo.estaAtrasado()) {
            view.exibirMensagem("Livro devolvido com atraso de " + emprestimo.calcularDiasAtraso() + " dias.");
        }
    }

    public void listarEmprestimos() {

        if (emprestimos.isEmpty()) {
            view.exibirMensagem("Nenhum empréstimo encontrado.");
            return;
        }

        for (Emprestimo emprestimo : emprestimos) {
            view.exibirMensagem(emprestimo.toString());
            view.exibirSeparador();
        }
    }

    public void listarAtrasados() {

        ArrayList<Emprestimo> atrasados = new ArrayList<>();

        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.estaAtrasado()) {
                atrasados.add(emprestimo);
            }
        }

        if (atrasados.isEmpty()) {
            view.exibirMensagem("Nenhum empréstimo atrasado.");
            return;
        }

        atrasados.sort((a, b) -> b.calcularDiasAtraso() - a.calcularDiasAtraso());

        for (Emprestimo emprestimo : atrasados) {
            view.exibirMensagem(emprestimo.toString());
            view.exibirSeparador();
        }
    }

    public void relatorioLivrosEmprestados() {

        view.exibirMensagem("\n===== LIVROS EMPRESTADOS =====");
        boolean encontrou = false;

        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getDataDevolucao() == null) {
                view.exibirLivroEmprestado(
                        emprestimo.getLivro().getTitulo(),
                        emprestimo.getUsuario().getNome(),
                        emprestimo.getDataPrevista().toString()
                );
                view.exibirSeparador();
                encontrou = true;
            }
        }

        if (!encontrou) {
            view.exibirMensagem("Nenhum livro emprestado no momento.");
        }
    }

    public void relatorioUsuariosAtrasados() {

        view.exibirMensagem("\n===== USUÁRIOS COM ATRASO =====");
        boolean encontrou = false;

        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.estaAtrasado()) {
                view.exibirUsuarioAtrasado(
                        emprestimo.getUsuario().getNome(),
                        emprestimo.getLivro().getTitulo(),
                        emprestimo.calcularDiasAtraso()
                );
                view.exibirSeparador();
                encontrou = true;
            }
        }

        if (!encontrou) {
            view.exibirMensagem("Nenhum usuário com atraso.");
        }
    }

    public void relatorioLivrosPopulares() {

        view.exibirMensagem("\n===== LIVROS MAIS POPULARES =====");

        ArrayList<String> titulos = new ArrayList<>();
        ArrayList<Integer> contagens = new ArrayList<>();

        for (Emprestimo emprestimo : emprestimos) {
            String titulo = emprestimo.getLivro().getTitulo();
            int index = titulos.indexOf(titulo);

            if (index == -1) {
                titulos.add(titulo);
                contagens.add(1);
            } else {
                contagens.set(index, contagens.get(index) + 1);
            }
        }

        for (int i = 0; i < titulos.size() - 1; i++) {
            for (int j = i + 1; j < titulos.size(); j++) {
                if (contagens.get(j) > contagens.get(i)) {
                    String t = titulos.get(i);
                    int c = contagens.get(i);
                    titulos.set(i, titulos.get(j));
                    contagens.set(i, contagens.get(j));
                    titulos.set(j, t);
                    contagens.set(j, c);
                }
            }
        }

        for (int i = 0; i < titulos.size(); i++) {
            view.exibirLivroPopular(i + 1, titulos.get(i), contagens.get(i));
        }
    }

    public void carregarDadosTeste() {

        Livro l1 = new Livro(1, "Clean Code", "Robert Martin", "Programação", 2008, 5);
        Livro l2 = new Livro(2, "Dom Casmurro", "Machado de Assis", "Romance", 1899, 3);
        Livro l3 = new Livro(3, "Harry Potter", "J.K Rowling", "Fantasia", 1997, 4);

        livros.add(l1);
        livros.add(l2);
        livros.add(l3);

        Usuario u1 = new Usuario("Gustavo", "41999999999", "gustavo@email.com", "Rua A, 123");
        Usuario u2 = new Usuario("Maria", "41988888888", "maria@email.com", "Rua B, 456");

        usuarios.add(u1);
        usuarios.add(u2);

        Emprestimo emprestimo = new Emprestimo(l1, u1, LocalDate.now().minusDays(10), LocalDate.now().minusDays(3));
        emprestimos.add(emprestimo);
        l1.setQuantidade(l1.getQuantidade() - 1);
    }

    public ArrayList<Livro> getLivros() { return livros; }
    public void setLivros(ArrayList<Livro> livros) { this.livros = livros; }

    public ArrayList<Usuario> getUsuarios() { return usuarios; }
    public void setUsuarios(ArrayList<Usuario> usuarios) { this.usuarios = usuarios; }

    public ArrayList<Emprestimo> getEmprestimos() { return emprestimos; }
    public void setEmprestimos(ArrayList<Emprestimo> emprestimos) { this.emprestimos = emprestimos; }
}