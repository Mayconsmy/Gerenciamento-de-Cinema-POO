package model;

public class Funcionario extends Pessoa {

    private String cargo;
    private double salario;
    public Funcionario(String nome, String cpf, String cargo, double salario, String senha) {
        super(nome, cpf, senha);
        this.cargo = cargo;
        this.salario = salario;
        this.senha = senha;
    }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo){
        this.cargo = cargo;
    }

    public double getSalario(){
        return salario;
    }
    public void setSalario(double salario){
        this.salario = salario;
    }

    public String realizarAcao() {
        return "O funcionário " + getNome() + " está trabalhando como " + cargo + ".";
    }
}