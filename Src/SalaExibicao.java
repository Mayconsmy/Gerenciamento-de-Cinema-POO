public class SalaExibicao {
    
    private int numeroSala;
    private int capacidadeTotal;
    private boolean is3D;
    
    public SalaExibicao(int numeroSala, int capacidadeTotal, boolean is3D) {
        this.numeroSala = numeroSala;
        
        setCapacidadeTotal(capacidadeTotal); 
        this.is3D = is3D;
    }
    
    public int getNumeroSala() {
        return numeroSala;
    }
    
    public int getCapacidadeTotal() {
        return capacidadeTotal;
    }
    
    public boolean isIs3D() { 
        return is3D;
    }
    
    public void setNumeroSala(int numeroSala) {
        this.numeroSala = numeroSala;
    }
    
    public void setCapacidadeTotal(int capacidadeTotal) {
        if (capacidadeTotal <= 0) {
            System.out.println("ERRO: A capacidade da sala (" + capacidadeTotal + ") deve ser um número positivo de assentos. Capacidade não alterada.");
        } else {
            this.capacidadeTotal = capacidadeTotal;
        }
    }
    
    public void setIs3D(boolean is3D) {
        this.is3D = is3D;
    }
    
    public void exibirDetalhes() {
        System.out.println("--- informações da Sala ---");
        System.out.println("Número da Sala: " + numeroSala);
        System.out.println("Capacidade Máxima: " + capacidadeTotal + " assentos");
        System.out.println("Suporte 3D: " + (is3D ? "Sim" : "Não"));
    }
}