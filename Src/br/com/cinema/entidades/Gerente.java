package br.com.cinema.entidades;
public class Gerente extends Pessoa {

    private String departamento; 

    public Gerente(String nome, String cpf, String senha, String departamento) {
        super(nome, cpf, senha);
        this.departamento = departamento;
    }

    public String getDepartamento() {
        return departamento;
    }
    
    @Override
    public String getCargo() {
        return "Gerente (Acesso Total)";
    }
}