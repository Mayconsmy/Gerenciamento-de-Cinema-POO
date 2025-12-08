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
            System.out.println("\n--- GESTÃO DE PESSOAS ---");
            System.out.println("1. Listar Pessoas");
            System.out.println("2. Adicionar Funcionario");
            System.out.println("3. Adicionar Gerente");
            System.out.println("0. Voltar");
            opcao = Utilitarios.lerInt("Opção: ");

            switch (opcao) {
                case 1 -> listar();
                case 2 -> adicionarFuncionario();
                case 3 -> adicionarGerente();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        }
    }