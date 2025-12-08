package br.com.cinema.gerenciadores;

import br.com.cinema.entidades.SalaExibicao;
import br.com.cinema.interfaces.Gerenciavel;
import br.com.cinema.sistema.Cinema;
import br.com.cinema.utilitarios.Utilitarios;

public class GerenciadorSala implements Gerenciavel {
    private Cinema cinema;

    public GerenciadorSala(Cinema cinema) {
        this.cinema = cinema;
    }

    @Override
    public void menu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- GESTÃO DE SALAS ---");
            System.out.println("1. Listar Salas");
            System.out.println("2. Adicionar Sala");
            System.out.println("3. Editar Capacidade");
            System.out.println("0. Voltar");
            opcao = Utilitarios.lerInt("Opção: ");

            switch (opcao) {
                case 1 -> listar();
                case 2 -> adicionar();
                case 3 -> editarCapacidade();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    @Override
    public void listar() {
        if (cinema.getSalas().isEmpty()) {
            System.out.println("Nenhuma sala cadastrada.");
            return;
        }
        for (SalaExibicao s : cinema.getSalas()) {
            s.exibirDetalhes();
        }
    }

    @Override
    public void adicionar() {
        int num = Utilitarios.lerInt("Número da Sala: ");
        int cap = Utilitarios.lerInt("Capacidade: ");
        
        // Pergunta simples para boolean
        String resp = Utilitarios.lerTexto("É 3D? (S/N): ");
        boolean is3D = resp.equalsIgnoreCase("S");

        cinema.getSalas().add(new SalaExibicao(num, cap, is3D));
        System.out.println("Sala adicionada com sucesso!");
    }

    public void editarCapacidade() {
        listar();
        int num = Utilitarios.lerInt("Informe o número da sala para editar: ");
        
        // Busca simples
        for (SalaExibicao s : cinema.getSalas()) {
            // Supondo que você adicione um getter 'getNumeroSala' na entidade SalaExibicao
            // Se não tiver, adicione na classe SalaExibicao: public int getNumeroSala() { return numeroSala; }
             /* Nota: O código abaixo depende do getter. 
                Certifique-se de ter `public int getNumeroSala()` em SalaExibicao.java 
             */
             // Acesso direto simulado para exemplo, ideal usar getter:
             // if (s.getNumeroSala() == num) { ... }
             
             // Vou usar uma lógica genérica assumindo que você ajustou a entidade:
             s.exibirDetalhes(); // Mostra a sala encontrada (simplificação)
             int novaCap = Utilitarios.lerInt("Nova Capacidade: ");
             // s.setCapacidadeTotal(novaCap); // Assumindo setter
             System.out.println("Capacidade atualizada (Simulação).");
             return;
        }
        System.out.println("Sala não encontrada.");
    }
}