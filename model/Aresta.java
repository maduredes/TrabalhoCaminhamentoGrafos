// Alunas: Karoline Custodio dos Santos e Maria Eduarda Redes

package model;

public class Aresta {

    private String origem;
    private String destino;
    private Integer peso;

    public Aresta(String origem,String destino,Integer peso){
        this.origem=origem;
        this.destino=destino;
        this.peso=peso;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }
}