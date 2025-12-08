package br.com.cinema.gerenciadores;

import br.com.cinema.entidades.Filme;
import br.com.cinema.sistema.Cinema;
import br.com.cinema.utilitarios.Utilitarios;

public class GerenciadorFilme {
    private Cinema cinema;

    public GerenciadorFilme(Cinema cinema) {
        this.cinema = cinema;
    }