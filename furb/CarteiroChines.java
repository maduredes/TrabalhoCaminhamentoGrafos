package furb;

import javax.swing.*;
import java.util.*;

public class CarteiroChines {

    private Map<String, List<Aresta>> grafo = new HashMap<>();
    private List<String> verticesImpares = new ArrayList<>();

    public List<String> getVerticesImpares() {
        return verticesImpares;
    }

    public void addVerticesImpares(String verticeImpar) {
        this.verticesImpares.add(verticeImpar);
    }

    public Map<String, List<Aresta>> getGrafo() {
        return grafo;
    }

    public void addAresta(Aresta aresta) {
        grafo.computeIfAbsent(aresta.getOrigem(), k -> new ArrayList<>()).add(aresta);
    }

    private String encontraMenorValor(Map<String, Integer> distanciasMinimas, List<String> verticesVisitados) {
        int menorValor = Integer.MAX_VALUE;
        String vertice = null;
        // Percorrer os valores do mapa
        for (Map.Entry<String, Integer> valor : distanciasMinimas.entrySet()) {
            if (valor.getValue() < menorValor && !verticesVisitados.contains(valor.getKey())) {
                vertice = valor.getKey();
                menorValor = valor.getValue();
            }
        }
        return vertice;
    }

    public void dijkstra() {
        Map<String, Integer> distancia = new HashMap<>();
        PriorityQueue<String> filaPrioridade = new PriorityQueue<>(Comparator.comparingInt(distancia::get));
        Map<String, Integer> distanciaMinima = new HashMap<>();
        List<String> verticesVisitados = new ArrayList<>();
        String[][] matriz = new String[4][4];

        for (String vertice : grafo.keySet()) {
            distancia.put(vertice, Integer.MAX_VALUE);
            distanciaMinima.put(vertice, Integer.MAX_VALUE);
        }

        for (String verticeGrauImpar : verticesImpares) {
            distancia.put(verticeGrauImpar, 0);
            filaPrioridade.add(verticeGrauImpar);
        }
        String u = filaPrioridade.poll();
        String verticeAtual="";
        while (!filaPrioridade.isEmpty()) {
            verticeAtual=u;
            while (verticesVisitados.size() < 9) {
                for (Aresta aresta : grafo.getOrDefault(u, Collections.emptyList())) {
                    if (!verticesVisitados.contains(aresta.getDestino())) {
                        int novaDistancia = distancia.get(u) + aresta.getPeso();

                        if (novaDistancia < distanciaMinima.get(aresta.getDestino())) {
                            distancia.put(aresta.getDestino(), novaDistancia);
                            distanciaMinima.put(aresta.getDestino(), novaDistancia);
                            // filaPrioridade.add(aresta.getDestino());
                        }
                    }
                }
                verticesVisitados.add(u);
                u = encontraMenorValor(distanciaMinima, verticesVisitados);
            }
            System.out.println("Dijkstra Vértice ímpar :" + verticeAtual.toUpperCase());
            for (Map.Entry<String, Integer> entry : distanciaMinima.entrySet()) {
                System.out.println("Chave: " + entry.getKey() + ", Valor: " + entry.getValue());
            }
            verticesVisitados.clear();
            distancia.clear();
            u = filaPrioridade.poll();
        }

    }
}