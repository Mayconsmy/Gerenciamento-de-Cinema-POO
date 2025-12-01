package model;

public abstract class Produto {

    protected String nome;
    protected double preco;

    public Produto(String nome, double preco) {
        this.nome = nome;
        setPreco(preco);
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        if (preco >= 0) {
            this.preco = preco;
        } else {
            System.out.println("Erro: Preço não pode ser negativo. Preço mantido em " + this.preco);
        }
    }

    public abstract String exibirDetalhes();

    public String getDetalhesBasicos() {
        return "Nome: " + this.nome + " | Preço: R$" + String.format("%.2f", this.preco);
    }
}