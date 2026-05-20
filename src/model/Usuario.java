package model;

public class Usuario extends Pessoa {

    private String email;
    private String endereco;

    public Usuario(String nome, String telefone, String email, String endereco) {
        super(nome, telefone);
        this.email = email;
        this.endereco = endereco;
    }

    @Override
    public String exibirInformacoes() {
        return toString();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return super.toString() + "\nEmail: " + email + "\nEndereço: " + endereco;
    }
}