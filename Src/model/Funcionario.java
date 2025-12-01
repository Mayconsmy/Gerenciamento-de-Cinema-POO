package model;

public class Funcionario extends Pessoa {
    //Atributos de Funcionario
    private String matricula;
    private double salario;

    public Funcionario(String nome, String cpf, String senha, String matricula, double salario) {
        super(nome, cpf, senha);
        this.matricula = matricula;
        // Usamos o setter aqui para garante a validação na criação
        //setSalario(salario);
    }

    //Sobrescreve o método abstrato da mãe
    @Override
    public String exibirDetalhes() {
        return "Funcionário: " + getNome() + " | Matrícula: " + matricula + " | Salário: R$ " + salario;
    }

//    // Setter com validação lógica
//    public void setSalario(double salario) {
//        if (salario < 0) {
//            throw new IllegalArgumentException("Erro: O salário não pode ser negativo!");
//        }
//        this.salario = salario;
//    }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    public double getSalario() { return salario; }
}