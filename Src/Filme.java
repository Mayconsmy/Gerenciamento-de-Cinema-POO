public class Filme {

    private String titulo;
    private int duracaoMinutos;
    private String classificacaoIndicativa;
    
    public Filme(String titulo, int duracaoMinutos, String classificacaoIndicativa) {
        this.titulo = titulo;
        setDuracaoMinutos(duracaoMinutos);
        this.classificacaoIndicativa = classificacaoIndicativa;
    }
    
    public String getTitulo() {
        return titulo;
    }
    
    public int getDuracaoMinutos() {
        return duracaoMinutos;
    }
    
    public String getClassificacaoIndicativa() {
        return classificacaoIndicativa;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public void setDuracaoMinutos(int duracaoMinutos) {
        if (duracaoMinutos <= 0) {
            System.out.println("[ALERTA DE VALIDAÇÃO] ERRO: A duração do filme (" + duracaoMinutos + " min) deve ser um valor positivo.");
        } else {
            this.duracaoMinutos = duracaoMinutos;
        }
    }
    
    public void setClassificacaoIndicativa(String classificacaoIndicativa) {
        this.classificacaoIndicativa = classificacaoIndicativa;
    }

    public void exibirDetalhes() {
        System.out.println("   Detalhes do Filme   ");
        System.out.println("Título: " + titulo);
        System.out.println("Duração: " + duracaoMinutos + " minutos");
        System.out.println("Classificação Indicativa: " + classificacaoIndicativa);
    }
}