package br.com.cinema.gerenciadores;

import br.com.cinema.entidades.Filme;
import br.com.cinema.sistema.Cinema;
import br.com.cinema.utilitarios.Utilitarios;

public class GerenciadorFilme {
    private Cinema cinema;

    public GerenciadorFilme(Cinema cinema) {
        this.cinema = cinema;
    }

    public void menu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- GESTÃO DE FILMES ---");
            System.out.println("1. Listar Filmes");
            System.out.println("2. Adicionar Filme");
            System.out.println("0. Voltar");
            opcao = Utilitarios.lerInt("Opção: ");

            switch (opcao) {
                case 1 -> listar();
                case 2 -> adicionar();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        }
    }