package furb;

import javax.swing.event.ListDataEvent;
import java.util.*;
import java.util.stream.Collectors;

public class CarteiroChines {

    private List<Aresta> arestas = new ArrayList<>();

    private List<String> verticesImpares = new ArrayList<>();

    private List<String> verticesVisitados = new ArrayList<>();


    public List<String> getVerticesVisitados() {
        return verticesVisitados;
    }

    public void setVerticesVisitados(List<String> verticesVisitados) {
        this.verticesVisitados = verticesVisitados;
    }

    public List<String> getVerticesImpares() {
        return verticesImpares;
    }

    public void addVerticesImpares(String verticeImpar) {
        this.verticesImpares.add(verticeImpar);
    }

    public List<Aresta> getArestas() {
        return arestas;
    }

    public void addAresta(Aresta aresta) {
        this.arestas.add(aresta);
    }

    public String encontraValorMinimo(List<Aresta> menorCaminho) {
        List<Aresta> retornaVerticesNaoVisitados= menorCaminho.stream().filter(item-> (verticesVisitados.size() > 0
                && !verticesVisitados.contains(item.getDestino()))).collect(Collectors.toList());
        return !retornaVerticesNaoVisitados.isEmpty() ?  retornaVerticesNaoVisitados.stream().min(Comparator.comparingInt(Aresta::getPeso)).get().getDestino() :
                menorCaminho.stream().min(Comparator.comparingInt(Aresta::getPeso)).get().getDestino();
    }

    public List<Aresta> relax(String vertice) {
        Integer pivo = 0;
        Integer valorInicial = 0;
        List<Aresta> menorCaminho = new ArrayList<>();

        List<Aresta> filtroOrigem = getArestas().stream().filter(item -> item.getOrigem().equals(vertice)).collect(Collectors.toList());
        for (Aresta aresta : filtroOrigem) {
            valorInicial = pivo + aresta.getPeso();
            menorCaminho.add(new Aresta(aresta.getOrigem(), aresta.getDestino(), valorInicial));
        }
        verticesVisitados.add(vertice);
        return menorCaminho;
    }

    public void dijkstra() {
        Integer pivo = 0;
        String u;
        Integer valorInicial = 0;
        List<Aresta> menorCaminho = new ArrayList<>();
        int aux = arestas.size();

        for (String verticeGrauImpar : verticesImpares) {
            u = encontraValorMinimo(relax(verticeGrauImpar));
            do {
                if (!verticesVisitados.contains(u)) {
                   u= encontraValorMinimo(relax(u));
                }
                aux = aux - 1;
            } while (aux != 0);
        }
    }


}
