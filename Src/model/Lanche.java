package model;

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
            System.out.println("Erro: Item indisponivél no estoque. Estoque mantido em " + this.emEstoque);
        }
    }

    public boolean darBaixaEstoque(int quantidade) {
        if (this.emEstoque >= quantidade) {
            this.emEstoque -= quantidade;
            return true;
        }
        return false;
    }

    @Override
    public String exibirDetalhes() {
        String status = this.emEstoque > 0 ? "Em Estoque" : "ESGOTADO";
        return " Pedido: " + super.getDetalhesBasicos() + 
               " | Tamanho: " + this.tamanho +
               " | Estoque: " + this.emEstoque + " (" + status + ")";
    }
}