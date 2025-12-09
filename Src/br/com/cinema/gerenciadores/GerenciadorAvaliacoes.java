package br.com.cinema.gerenciadores;

import br.com.cinema.entidades.Avaliacao;
import br.com.cinema.entidades.Filme;
import br.com.cinema.sistema.Cinema;
import br.com.cinema.utilitarios.Utilitarios;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GerenciadorAvaliacoes {
    private Cinema cinema;

    public GerenciadorAvaliacoes(Cinema cinema) {
        this.cinema = cinema;
    }

    public void menu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- GESTÃO DE AVALIAÇÕES ---");
            System.out.println("1. Criar Avaliação");
            System.out.println("2. Remover Avaliação (por ID)");
            System.out.println("3. Mostrar Todas as Avaliações");
            System.out.println("4. Buscar Avaliação Específica (por ID)");
            System.out.println("5. Mostrar Avaliação(ões) com Maior Número de Estrelas");
            System.out.println("6. Mostrar Contagem de Estrelas");
            System.out.println("0. Voltar");
            opcao = Utilitarios.lerInt("Opção: ");

            switch (opcao) {
                case 1 -> criarAvaliacao();
                case 2 -> removerAvaliacao();
                case 3 -> mostrarTodasAvaliacoes();
                case 4 -> buscarAvaliacao();
                case 5 -> mostrarMelhoresAvaliacoes();
                case 6 -> mostrarContagemEstrelas();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private void criarAvaliacao() {
        String nomeUsuario = Utilitarios.lerTexto("Nome do Usuário: ");
        String tituloFilme = Utilitarios.lerTexto("Título do Filme a ser avaliado: ");

        Filme filme = cinema.getFilmes().stream()
                .filter(f -> f.getTitulo().equalsIgnoreCase(tituloFilme))
                .findFirst()
                .orElse(null);

        if (filme == null) {
            System.out.println("ERRO: Filme não encontrado no catálogo. Avaliação não criada.");
            return;
        }

        String estrelas;
        do {
            // Adicionado trim() para evitar erros com espaços em branco
            estrelas = Utilitarios.lerTexto("Nota (1 a 5 estrelas, ex: ***): ").trim();
            if (!Avaliacao.validarEstrelas(estrelas)) {
                System.out.println("ERRO: Entrada de estrelas inválida. Use de 1 a 5 asteriscos (*, **, ***, ****, *****).");
            }
        } while (!Avaliacao.validarEstrelas(estrelas));

        String comentario = Utilitarios.lerTexto("Comentário (opcional): ");

        Avaliacao novaAvaliacao = new Avaliacao(nomeUsuario, filme, estrelas, comentario);
        
        if (cinema.getAvaliacoes() == null) {
            cinema.setAvaliacoes(new ArrayList<>());
        }
        cinema.getAvaliacoes().add(novaAvaliacao);
        System.out.println("Avaliação criada com sucesso! ID: " + novaAvaliacao.getId());
    }

    private void removerAvaliacao() {
        int id = Utilitarios.lerInt("ID da Avaliação a ser removida: ");
        if (cinema.getAvaliacoes() == null || cinema.getAvaliacoes().isEmpty()) {
            System.out.println("Nenhuma avaliação cadastrada.");
            return;
        }

        boolean removido = cinema.getAvaliacoes().removeIf(a -> a.getId() == id);

        if (removido) {
            System.out.println("Avaliação ID " + id + " removida com sucesso.");
        } else {
            System.out.println("Avaliação com ID " + id + " não encontrada.");
        }
    }

    private void mostrarTodasAvaliacoes() {
        if (cinema.getAvaliacoes() == null || cinema.getAvaliacoes().isEmpty()) {
            System.out.println("Nenhuma avaliação cadastrada.");
            return;
        }
        System.out.println("\n--- TODAS AS AVALIAÇÕES ---");
        cinema.getAvaliacoes().forEach(a -> System.out.println(a.exibirDetalhes()));
    }

    private void buscarAvaliacao() {
        int id = Utilitarios.lerInt("ID da Avaliação a ser buscada: ");
        if (cinema.getAvaliacoes() == null || cinema.getAvaliacoes().isEmpty()) {
            System.out.println("Nenhuma avaliação cadastrada.");
            return;
        }

        Avaliacao encontrada = cinema.getAvaliacoes().stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null);

        if (encontrada != null) {
            System.out.println("\n--- AVALIAÇÃO ENCONTRADA ---");
            System.out.println(encontrada.exibirDetalhes());
        } else {
            System.out.println("Avaliação com ID " + id + " não encontrada.");
        }
    }

    private void mostrarMelhoresAvaliacoes() {
        if (cinema.getAvaliacoes() == null || cinema.getAvaliacoes().isEmpty()) {
            System.out.println("Nenhuma avaliação cadastrada.");
            return;
        }

        int maxEstrelas = cinema.getAvaliacoes().stream()
                .mapToInt(a -> a.getEstrelas().length())
                .max()
                .orElse(0);

        if (maxEstrelas == 0) {
            System.out.println("Nenhuma avaliação com estrelas válidas.");
            return;
        }

        List<Avaliacao> melhores = cinema.getAvaliacoes().stream()
                .filter(a -> a.getEstrelas().length() == maxEstrelas)
                .collect(Collectors.toList());

        System.out.println("\n AVALIAÇÃO(ÕES) COM " + maxEstrelas + " ESTRELAS ");
        melhores.forEach(a -> System.out.printf("Usuário: %s | Filme: %s | Estrelas: %s\n",
                a.getNomeUsuario(), a.getFilme().getTitulo(), a.getEstrelas()));
    }

    private void mostrarContagemEstrelas() {
        if (cinema.getAvaliacoes() == null || cinema.getAvaliacoes().isEmpty()) {
            System.out.println("Nenhuma avaliação cadastrada.");
            return;
        }

        Map<String, Long> contagem = cinema.getAvaliacoes().stream()
                .collect(Collectors.groupingBy(Avaliacao::getEstrelas, Collectors.counting()));

        System.out.println("\n CONTAGEM DE AVALIAÇÕES POR CATEGORIA DE ESTRELAS ");
        contagem.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                .forEach(entry -> System.out.printf("%s: %d avaliações\n", entry.getKey(), entry.getValue()));
    }
}