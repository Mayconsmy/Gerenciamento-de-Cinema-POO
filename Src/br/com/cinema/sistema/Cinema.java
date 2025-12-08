package br.com.cinema.sistema;

import br.com.cinema.entidades.Filme;
import br.com.cinema.entidades.Pessoa;
import br.com.cinema.entidades.Produto;
import br.com.cinema.entidades.SalaExibicao;
import br.com.cinema.entidades.Avaliacao;

import java.util.ArrayList;
import java.util.List;

public class Cinema {
    private List<Pessoa> pessoas;
    private List<Filme> filmes;
    private List<Produto> produtos;
    private List<SalaExibicao> salas;
    private List<Avaliacao> avaliacoes;

    public Cinema() {
        this.pessoas = new ArrayList<>();
        this.filmes = new ArrayList<>();
        this.produtos = new ArrayList<>();
        this.salas = new ArrayList<>();
        this.avaliacoes = new ArrayList<>();
    }

    // Getters
    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public List<Filme> getFilmes() {
        return filmes;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public List<SalaExibicao> getSalas() {
        return salas;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }
}
