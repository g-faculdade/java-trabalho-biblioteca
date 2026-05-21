package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class PreCarga {

    public static void carregar(ArrayList<Livro> livros, ArrayList<Usuario> usuarios, ArrayList<Emprestimo> emprestimos) {

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
}