package model;

import java.time.LocalDate;

public class Emprestimo {

    private Livro livro;
    private Usuario usuario;

    private LocalDate dataEmprestimo;
    private LocalDate dataPrevista;
    private LocalDate dataDevolucao;

    public Emprestimo(Livro livro, Usuario usuario, LocalDate dataEmprestimo, LocalDate dataPrevista) {

        this.livro = livro;
        this.usuario = usuario;
        this.dataEmprestimo = dataEmprestimo;
        this.dataPrevista = dataPrevista;
    }

    public boolean estaAtrasado() {

        if (dataDevolucao == null) {
            return LocalDate.now().isAfter(dataPrevista);
        }

        return dataDevolucao.isAfter(dataPrevista);
    }

    public int calcularDiasAtraso() {

        if (!estaAtrasado()) {
            return 0;
        }

        LocalDate dataFinal;

        if (dataDevolucao == null) {
            dataFinal = LocalDate.now();
        } else {
            dataFinal = dataDevolucao;
        }

        return (int) (dataFinal.toEpochDay() - dataPrevista.toEpochDay());
    }

    public void realizarDevolucao() {
        this.dataDevolucao = LocalDate.now();
    }

    public Livro getLivro() {
        return livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataPrevista() {
        return dataPrevista;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    @Override
    public String toString() {
        return "Livro: " + livro.getTitulo() + "\nUsuário: " + usuario.getNome() + "\nData empréstimo: " + dataEmprestimo + "\nData prevista: " + dataPrevista + "\nData devolução: " + dataDevolucao + "\nAtrasado: " + (estaAtrasado() ? "Sim" : "Não") + "\nDias atraso: " + calcularDiasAtraso();
    }
}