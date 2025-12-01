package model;

public abstract class Pessoa {
    private String nome;
    private String cpf;
    private String senha;

    public Pessoa(String nome, String cpf, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
    }


    public abstract String exibirDetalhes();

    public boolean autenticar(String senhaInput) {
        return this.senha.equals(senhaInput);
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
}