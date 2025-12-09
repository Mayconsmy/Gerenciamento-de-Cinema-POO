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
            System.out.println("3. Remover Filme");
            System.out.println("4. Buscar Filme");
            System.out.println("0. Voltar");
            opcao = Utilitarios.lerInt("Opção: ");

            switch (opcao) {
                case 1 -> listar();
                case 2 -> adicionar();
                case 3 -> remover();
                case 4 -> buscar();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private void listar() {
        if (cinema.getFilmes().isEmpty()) System.out.println("Nenhum filme.");
        for (Filme f : cinema.getFilmes()) f.exibirDetalhes();
    }

    private void adicionar() {
        String tit = Utilitarios.lerTexto("Título: ");
        int dur = Utilitarios.lerInt("Duração (min): ");
        String clas = Utilitarios.lerTexto("Classificação: ");
        cinema.getFilmes().add(new Filme(tit, dur, clas));
        System.out.println("Filme adicionado!");
    }

    private Filme buscarFilme(String titulo) {
        return cinema.getFilmes().stream()
                .filter(f -> f.getTitulo().equalsIgnoreCase(titulo))
                .findFirst()
                .orElse(null);
    }

    private void remover() {
        String titulo = Utilitarios.lerTexto("Título do Filme para remover: ");
        Filme filme = buscarFilme(titulo);

        if (filme == null) {
            System.out.println("ERRO: Filme não encontrado.");
            return;
        }

        cinema.getFilmes().remove(filme);
        System.out.println("Filme '" + titulo + "' removido com sucesso!");
    }

    private void buscar() {
        String titulo = Utilitarios.lerTexto("Título do Filme para buscar: ");
        Filme filme = buscarFilme(titulo);

        if (filme == null) {
            System.out.println("Filme não encontrado.");
        } else {
            System.out.println("Filme encontrado:");
            filme.exibirDetalhes();
        }
    }
}