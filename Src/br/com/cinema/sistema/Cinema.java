package br.com.cinema.sistema;

import br.com.cinema.entidades.*;
import java.util.ArrayList;
import java.util.List;

public class Cinema {
    private List<Pessoa> pessoas;
    private List<Filme> filmes;
    private List<Produto> produtos;
    private List<SalaExibicao> salas;

    public Cinema() {
        this.pessoas = new ArrayList<>();
        this.filmes = new ArrayList<>();
        this.produtos = new ArrayList<>();
        this.salas = new ArrayList<>();
        inicializarDados(); // Carga inicial
    }

    private void inicializarDados() {
        pessoas.add(new Gerente("Admin", "000", "admin", "Geral"));
        filmes.add(new Filme("O Grande Lebowski", 117, "14 anos"));
        produtos.add(new Lanche("Pipoca Grande", 25.00, "Grande", 50));
        salas.add(new SalaExibicao(1, 150, true));
    }

    // Getters para as listas
    public List<Pessoa> getPessoas() { return pessoas; }
    public List<Filme> getFilmes() { return filmes; }
    public List<Produto> getProdutos() { return produtos; }
    public List<SalaExibicao> getSalas() { return salas; }
}