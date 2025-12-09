package br.com.cinema.entidades;

import br.com.cinema.exceptions.EstoqueException;

public class Lanche extends Produto {
    private String tamanho;
    private int emEstoque;

    public Lanche(String nome, double preco, String tamanho, int emEstoque) {
        super(nome, preco);
        this.tamanho = tamanho;
        setEmEstoque(emEstoque);
    }

    public String getTamanho() {
        return tamanho;
    }

    public int getEmEstoque() {
        return emEstoque;
    }
    
    public void setEmEstoque(int emEstoque) {
        if (emEstoque >= 0) {
            this.emEstoque = emEstoque;
        } else {
            System.out.println("Erro: Item indisponível no estoque. Estoque mantido em " + this.emEstoque);
        }
    }

    public void darBaixaEstoque(int quantidade) throws EstoqueException {
        if (quantidade > this.emEstoque) {
            throw new EstoqueException("Estoque insuficiente. Solicitado: " + quantidade + ", Disponível: " + this.emEstoque);
        }
        this.emEstoque -= quantidade;
    }

    @Override
    public String exibirDetalhes() {
        String status = this.emEstoque > 0 ? "Em Estoque" : "ESGOTADO";
        return " Pedido: " + super.getDetalhesBasicos() + 
               " | Tamanho: " + this.tamanho +
               " | Estoque: " + this.emEstoque + " (" + status + ")";
    }
}