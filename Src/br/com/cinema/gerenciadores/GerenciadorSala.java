package br.com.cinema.gerenciadores;

import br.com.cinema.entidades.SalaExibicao;
import br.com.cinema.sistema.Cinema;
import br.com.cinema.utilitarios.Utilitarios;
import br.com.cinema.entidades.Filme;

public class GerenciadorSala {
    private Cinema cinema;

    public GerenciadorSala(Cinema cinema) {
        this.cinema = cinema;
    }

    public void menu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n   GESTÃO DE SALAS DE EXIBIÇÃO ");
            System.out.println("1. Listar Todas as Salas");
            System.out.println("2. Adicionar Nova Sala");
            System.out.println("3. Gerenciar Reservas de Salas");
            System.out.println("4. Gerenciar Assentos");
            System.out.println("0. Voltar");
            opcao = Utilitarios.lerInt("Opção: ");

            switch (opcao) {
                case 1 -> listar();
                case 2 -> adicionar();
                case 3 -> menuReservasSalas();
                case 4 -> menuReservasAssentos();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private void listar() {
        if (cinema.getSalas().isEmpty()) {
            System.out.println("Nenhuma sala cadastrada.");
            return;
        }
        System.out.println("\n Lista de Salas");
        for (SalaExibicao s : cinema.getSalas()) {
            s.exibirDetalhes();
            System.out.println();
        }
    }

    private void adicionar() {
        int numero = Utilitarios.lerInt("Número da Sala: ");
        int capacidade = Utilitarios.lerInt("Capacidade Total: ");
        boolean is3D = Utilitarios.lerBoolean("Suporte 3D (true/false): ");

        SalaExibicao s = new SalaExibicao(numero, capacidade, is3D);
        cinema.getSalas().add(s);
        System.out.println("Sala " + numero + " adicionada com sucesso!");
    }

    private SalaExibicao buscarSala(int numero) {
        return cinema.getSalas().stream()
                .filter(s -> s.getNumeroSala() == numero)
                .findFirst()
                .orElse(null);
    }

    private void menuReservasSalas() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n GESTÃO DE RESERVAS DE SALAS ");
            System.out.println("1. Reservar Sala");
            System.out.println("2. Remover Reserva");
            System.out.println("3. Mostrar Todas as Reservas");
            System.out.println("4. Buscar Reserva de Sala");
            System.out.println("0. Voltar");
            opcao = Utilitarios.lerInt("Opção: ");

            switch (opcao) {
                case 1 -> reservarSala();
                case 2 -> removerReservaSala();
                case 3 -> listarReservasSalas();
                case 4 -> buscarReservaSala();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private void reservarSala() {
        int numSala = Utilitarios.lerInt("Número da Sala para reservar: ");
        SalaExibicao sala = buscarSala(numSala);

        if (sala == null) {
            System.out.println("ERRO: Sala não encontrada.");
            return;
        }

        if (sala.getFilmeHospedado() != null) {
            System.out.println("ERRO: Sala já reservada para o filme: " + sala.getFilmeHospedado().getTitulo());
            return;
        }

        String tituloFilme = Utilitarios.lerTexto("Título do Filme para exibição: ");
        Filme filme = cinema.getFilmes().stream()
                .filter(f -> f.getTitulo().equalsIgnoreCase(tituloFilme))
                .findFirst()
                .orElse(null);

        if (filme == null) {
            System.out.println("ERRO: Filme não encontrado no catálogo.");
            return;
        }

        sala.setFilmeHospedado(filme);
        System.out.println("Sala " + numSala + " reservada para o filme: " + filme.getTitulo());
    }

    private void removerReservaSala() {
        int numSala = Utilitarios.lerInt("Número da Sala para remover reserva: ");
        SalaExibicao sala = buscarSala(numSala);

        if (sala == null) {
            System.out.println("ERRO: Sala não encontrada.");
            return;
        }

        if (sala.getFilmeHospedado() == null) {
            System.out.println("A Sala " + numSala + " já está livre.");
            return;
        }

        sala.setFilmeHospedado(null);
        System.out.println("Reserva da Sala " + numSala + " removida. Sala agora está livre.");
    }

    private void listarReservasSalas() {
        System.out.println("\n RESERVAS DE SALAS ");
        cinema.getSalas().stream()
                .filter(s -> s.getFilmeHospedado() != null)
                .forEach(s -> System.out.println("Sala " + s.getNumeroSala() + " -> Filme: " + s.getFilmeHospedado().getTitulo()));
        if (cinema.getSalas().stream().noneMatch(s -> s.getFilmeHospedado() != null)) {
            System.out.println("Nenhuma sala reservada.");
        }
    }

    private void buscarReservaSala() {
        int numSala = Utilitarios.lerInt("Número da Sala para buscar reserva: ");
        SalaExibicao sala = buscarSala(numSala);

        if (sala == null) {
            System.out.println("ERRO: Sala não encontrada.");
            return;
        }

        if (sala.getFilmeHospedado() != null) {
            System.out.println("Sala " + numSala + " está reservada para o filme: " + sala.getFilmeHospedado().getTitulo());
        } else {
            System.out.println("Sala " + numSala + " está livre.");
        }
    }

    private void menuReservasAssentos() {
        int numSala = Utilitarios.lerInt("Número da Sala para gerenciar assentos: ");
        SalaExibicao sala = buscarSala(numSala);

        if (sala == null) {
            System.out.println("ERRO: Sala não encontrada.");
            return;
        }

        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- GESTÃO DE ASSENTOS DA SALA " + numSala + " ---");
            System.out.println("1. Reservar Assento");
            System.out.println("2. Remover Reserva de Assento");
            System.out.println("3. Buscar Reserva de Assento");
            System.out.println("4. Mostrar Mapa Completo de Assentos");
            System.out.println("0. Voltar");
            opcao = Utilitarios.lerInt("Opção: ");

            switch (opcao) {
                case 1 -> reservarAssento(sala);
                case 2 -> removerReservaAssento(sala);
                case 3 -> buscarReservaAssento(sala);
                case 4 -> sala.mostrarMapaAssentos();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private void reservarAssento(SalaExibicao sala) {
        String codigoAssento = Utilitarios.lerTexto("Código do Assento (ex: A1, J10): ");
        sala.reservarAssento(codigoAssento);
    }

    private void removerReservaAssento(SalaExibicao sala) {
        String codigoAssento = Utilitarios.lerTexto("Código do Assento para remover reserva (ex: A1, J10): ");
        sala.removerReservaAssento(codigoAssento);
    }

    private void buscarReservaAssento(SalaExibicao sala) {
        String codigoAssento = Utilitarios.lerTexto("Código do Assento para buscar (ex: A1, J10): ");
        sala.buscarReserva(codigoAssento);
    }
}
