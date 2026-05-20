package model;

public class Livro implements Pesquisavel {

    private int codigo;
    private String titulo;
    private String autor;
    private String categoria;
    private int ano;
    private int quantidade;

    public Livro(int codigo, String titulo, String autor, String categoria, int ano, int quantidade) {

        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.ano = ano;
        this.quantidade = quantidade;
    }

    @Override
    public boolean pesquisar(String termo) {

        termo = termo.toLowerCase();

        return titulo.toLowerCase().contains(termo) || autor.toLowerCase().contains(termo) || categoria.toLowerCase().contains(termo) || String.valueOf(codigo).equals(termo);
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "Código: " + codigo + "\nTítulo: " + titulo + "\nAutor: " + autor + "\nCategoria: " + categoria + "\nAno: " + ano + "\nQuantidade: " + quantidade;
    }
}