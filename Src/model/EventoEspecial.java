package model;

public class EventoEspecial extends Filme {

    public String titulo;
    public String tipoEvento;
    public double precoBase;
    public int DuracaoMinutos;
    public String classificacaoIndicativa;

    public EventoEspecial(String titulo, int duracaoMinutos, String classificacaoIndicativa, String tipoEvento, double precoBase) {
        super(titulo, duracaoMinutos, classificacaoIndicativa);
        this.tipoEvento = tipoEvento;
        this.precoBase = precoBase;
    }
    @Override
    public void exibirDetalhes() {
         String.format("Evento Especial: %s\n  Tipo: %s\n  Duração: %d min\n  Classificação: %s\n  Preço Base: R$ %.2f",
                titulo, tipoEvento, DuracaoMinutos, classificacaoIndicativa, precoBase);
    }
}