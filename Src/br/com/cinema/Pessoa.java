package br.com.cinema;
public abstract class Pessoa {
    protected String nome;
    protected String cpf;
    protected String senha;

    public Pessoa(String nome, String cpf, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getSenha() {
        return senha;
    }
    
    public abstract String getCargo(); 
    
    public String getDetalhesIdentificacao() {
        return "Nome: " + this.nome + " | CPF: " + this.cpf;
    }
}