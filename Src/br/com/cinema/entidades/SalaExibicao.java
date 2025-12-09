package br.com.cinema.entidades;

import br.com.cinema.exceptions.CinemaException;
import java.util.Arrays;

public class SalaExibicao {

    private int numeroSala;
    private int capacidadeTotal;
    private boolean is3D;
    private Filme filmeHospedado;
    private String[][] assentos;

    private static final int COLUNAS = 10;
    private static final char LINHA_INICIAL = 'A';
    private static final char LINHA_FINAL = 'J';
    private static final int NUM_LINHAS = LINHA_FINAL - LINHA_INICIAL + 1;

    public SalaExibicao(int numeroSala, int capacidadeTotal, boolean is3D) {
        this.numeroSala = numeroSala;
        setCapacidadeTotal(capacidadeTotal);
        this.is3D = is3D;
        this.filmeHospedado = null;
        inicializarAssentos();
    }

    private void inicializarAssentos() {
        this.assentos = new String[NUM_LINHAS][COLUNAS];
        for (int i = 0; i < NUM_LINHAS; i++) {
            char linha = (char) (LINHA_INICIAL + i);
            for (int j = 0; j < COLUNAS; j++) {
                this.assentos[i][j] = String.format("%c%d", linha, j + 1);
            }
        }
    }

    // APLICAÇÃO DE EXCEÇÃO: Validação agora lança erro
    private int[] parseAssento(String codigoAssento) throws CinemaException {
        if (codigoAssento == null || codigoAssento.length() < 2) 
            throw new CinemaException("Formato de assento inválido (Use Letra+Número, ex: A1).");

        char linhaChar = Character.toUpperCase(codigoAssento.charAt(0));
        String colunaStr = codigoAssento.substring(1);

        int linhaIndex = linhaChar - LINHA_INICIAL;
        int colunaIndex;

        try {
            colunaIndex = Integer.parseInt(colunaStr) - 1;
        } catch (NumberFormatException e) {
            throw new CinemaException("Número do assento inválido.");
        }

        if (linhaIndex < 0 || linhaIndex >= NUM_LINHAS || colunaIndex < 0 || colunaIndex >= COLUNAS) {
            throw new CinemaException("Assento fora dos limites da sala.");
        }

        return new int[]{linhaIndex, colunaIndex};
    }

    // APLICAÇÃO DE EXCEÇÃO: Método void com throw
    public void reservarAssento(String codigoAssento) throws CinemaException {
        int[] coords = parseAssento(codigoAssento); 
        int linha = coords[0];
        int coluna = coords[1];

        if (assentos[linha][coluna].equalsIgnoreCase("X")) {
            throw new CinemaException("O assento " + codigoAssento.toUpperCase() + " já está reservado.");
        }

        assentos[linha][coluna] = "X";
    }

    public boolean removerReservaAssento(String codigoAssento) {
        // Mantido com boolean para simplificar, mas idealmente usaria try-catch também
        try {
            int[] coords = parseAssento(codigoAssento);
            int linha = coords[0];
            int coluna = coords[1];

            if (assentos[linha][coluna].equalsIgnoreCase("X")) {
                assentos[linha][coluna] = String.format("%c%d", (char) (LINHA_INICIAL + linha), coluna + 1);
                System.out.println("Reserva do assento " + codigoAssento.toUpperCase() + " removida com sucesso.");
                return true;
            } else {
                System.out.println("ERRO: Assento " + codigoAssento.toUpperCase() + " não estava reservado.");
                return false;
            }
        } catch (CinemaException e) {
            System.out.println("ERRO: " + e.getMessage());
            return false;
        }
    }

    public boolean buscarReserva(String codigoAssento) {
        try {
            int[] coords = parseAssento(codigoAssento);
            int linha = coords[0];
            int coluna = coords[1];
            boolean reservado = assentos[linha][coluna].equalsIgnoreCase("X");
            System.out.println("Assento " + codigoAssento.toUpperCase() + ": " + (reservado ? "RESERVADO" : "LIVRE"));
            return reservado;
        } catch (CinemaException e) {
            System.out.println("ERRO: " + e.getMessage());
            return false;
        }
    }

    public void mostrarMapaAssentos() {
        System.out.println("\n--- MAPA DE ASSENTOS DA SALA " + numeroSala + " ---");
        System.out.print("   ");
        for (int j = 1; j <= COLUNAS; j++) {
            System.out.printf("%-4d", j);
        }
        System.out.println();

        for (int i = 0; i < NUM_LINHAS; i++) {
            char linha = (char) (LINHA_INICIAL + i);
            System.out.printf("%c |", linha);
            for (int j = 0; j < COLUNAS; j++) {
                String assento = assentos[i][j];
                System.out.printf("%-4s", assento.equalsIgnoreCase("X") ? "X" : assento);
            }
            System.out.println();
        }
    }

    // Getters e Setters
    public int getNumeroSala() { return numeroSala; }
    public int getCapacidadeTotal() { return capacidadeTotal; }
    public boolean isIs3D() { return is3D; }
    public Filme getFilmeHospedado() { return filmeHospedado; }
    public void setFilmeHospedado(Filme filmeHospedado) { this.filmeHospedado = filmeHospedado; }
    public void setNumeroSala(int numeroSala) { this.numeroSala = numeroSala; }
    public void setIs3D(boolean is3D) { this.is3D = is3D; }

    public void setCapacidadeTotal(int capacidadeTotal) {
        if (capacidadeTotal <= 0) {
            System.out.println("ERRO: A capacidade da sala deve ser positiva.");
        } else {
            this.capacidadeTotal = capacidadeTotal;
        }
    }

    public void exibirDetalhes() {
        System.out.println("   Informações da Sala   ");
        System.out.println("Número da Sala: " + numeroSala);
        System.out.println("Capacidade Máxima: " + capacidadeTotal + " assentos");
        System.out.println("Suporte 3D: " + (is3D ? "Sim" : "Não"));
        System.out.println("Filme em Exibição: " + (filmeHospedado != null ? filmeHospedado.getTitulo() : "Nenhum"));
    }
}