package br.com.cinema.gerenciadores;

import br.com.cinema.entidades.*;
import br.com.cinema.sistema.Cinema;
import br.com.cinema.utilitarios.Utilitarios;

public class GerenciadorPessoa {
    private Cinema cinema;

    public GerenciadorPessoa(Cinema cinema) {
        this.cinema = cinema;
    }

    public void menu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n GESTÃO DE PESSOAS ");
            System.out.println("1. Listar Pessoas");
            System.out.println("2. Adicionar Funcionario");
            System.out.println("3. Adicionar Gerente");
            System.out.println("4. Remover Pessoa (Funcionario/Gerente)");
            System.out.println("5. Buscar Pessoa (Funcionario/Gerente)");
            System.out.println("0. Voltar");
            opcao = Utilitarios.lerInt("Opção: ");

            switch (opcao) {
                case 1 -> listar();
                case 2 -> adicionarFuncionario();
                case 3 -> adicionarGerente();
                case 4 -> removerPessoa();
                case 5 -> buscarPessoa();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        }
    }


    public void listar() {
        if (cinema.getPessoas().isEmpty()) {
            System.out.println("Ninguém cadastrado.");
            return;
        }
        for (Pessoa p : cinema.getPessoas()) {
            System.out.println(p.getDetalhesIdentificacao() + " | " + p.getCargo());
        }
    }

    private void adicionarFuncionario() {
        String nome = Utilitarios.lerTexto("Nome: ");
        String cpf = Utilitarios.lerTexto("CPF: ");
        String cargo = Utilitarios.lerTexto("Cargo: ");
        double sal = Utilitarios.lerDouble("Salário: ");
        String senha = Utilitarios.lerTexto("Senha: ");

        cinema.getPessoas().add(new Funcionario(nome, cpf, cargo, sal, senha));
        System.out.println("Funcionário cadastrado!");
    }

    private void adicionarGerente() {
        String nome = Utilitarios.lerTexto("Nome: ");
        String cpf = Utilitarios.lerTexto("CPF: ");
        String dept = Utilitarios.lerTexto("Departamento: ");
        String senha = Utilitarios.lerTexto("Senha: ");

        cinema.getPessoas().add(new Gerente(nome, cpf, senha, dept));
        System.out.println("Gerente cadastrado!");
    }

    private Pessoa buscarPessoa(String cpf) {
        return cinema.getPessoas().stream()
                .filter(p -> p.getCpf().equals(cpf))
                .findFirst()
                .orElse(null);
    }

    private void removerPessoa() {
        String cpf = Utilitarios.lerTexto("CPF da Pessoa para remover: ");
        Pessoa pessoa = buscarPessoa(cpf);

        if (pessoa == null) {
            System.out.println("ERRO: Pessoa não encontrada.");
            return;
        }

        cinema.getPessoas().remove(pessoa);
        System.out.println("Pessoa com CPF " + cpf + " removida com sucesso!");
    }

    private void buscarPessoa() {
        String cpf = Utilitarios.lerTexto("CPF da Pessoa para buscar: ");
        Pessoa pessoa = buscarPessoa(cpf);

        if (pessoa == null) {
            System.out.println("Pessoa não encontrada.");
        } else {
            System.out.println("Pessoa encontrada:");
            System.out.println(pessoa.getDetalhesIdentificacao() + " | " + pessoa.getCargo());
        }
    }
}
