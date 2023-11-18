// Alunas: Karoline Custodio dos Santos e Maria Eduarda Redes

package model;

import java.util.*;
import java.util.stream.Collectors;

public class CarteiroChines {

    private Map<String, List<Aresta>> grafo = new HashMap<>();
    private List<String> verticesImpares = new ArrayList<>();

    public List<String> getVerticesImpares() {
        return verticesImpares;
    }

    public void addVerticesImpares() {
        for (String  aresta1 : this.getGrafo().keySet()) {
            for(Aresta aresta : this.getGrafo().get(aresta1)) {
                List<Aresta> filtroOrigem =  this.getGrafo().get(aresta1).stream().filter(item -> item.getOrigem().equals(aresta.getOrigem())).collect(Collectors.toList());
                if (!this.getVerticesImpares().contains(aresta.getOrigem())) {
                    if (filtroOrigem.size() % 2 != 0) {
                        this.verticesImpares.add(filtroOrigem.get(0).getOrigem());
                    }
                }
            }
        }
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

    public int [][] dijkstra() {
        Map<String, Integer> distancia = new HashMap<>();
        List<String> filaPrioridade = new ArrayList<>(); // Inicializa com todos os vértices

        Map<String, Integer> distanciaMinima = new HashMap<>();
        List<String> verticesVisitados = new ArrayList<>();

        String verticeAtual = "";
        String verticeDijkstra = "";
        int[][] matriz = new int[4][4];
        int i = 0;
        int j = 0;

        for (String vertice : grafo.keySet()) {
            distancia.put(vertice, Integer.MAX_VALUE);
            distanciaMinima.put(vertice, Integer.MAX_VALUE);
        }

        for (String verticeGrauImpar : verticesImpares) {
            distancia.put(verticeGrauImpar, 0);
            filaPrioridade.add(verticeGrauImpar);
        }
        //criação dos dijkstras de cada vertice impar
        while (!filaPrioridade.isEmpty()) {
            verticeAtual = filaPrioridade.remove(0); // Remove o primeiro elemento da lista
            verticeDijkstra = verticeAtual;
            while (verticesVisitados.size() < 9) {
                if (distancia.containsKey(verticeAtual)) {
                    for (Aresta aresta : grafo.getOrDefault(verticeAtual, Collections.emptyList())) {
                        if (!verticesVisitados.contains(aresta.getDestino())) {
                            int novaDistancia = distancia.get(verticeAtual) + aresta.getPeso();

                            if (novaDistancia < distanciaMinima.get(aresta.getDestino())) {
                                distancia.put(aresta.getDestino(), novaDistancia);
                                distanciaMinima.put(aresta.getDestino(), novaDistancia);
                            }
                        }
                    }
                    verticesVisitados.add(verticeAtual);
                    verticeAtual = encontraMenorValor(distanciaMinima, verticesVisitados);
                }
            }

            System.out.println();
            System.out.println("Dijkstra Vértice Ímpar: " + verticeDijkstra.toUpperCase());
            for (Map.Entry<String, Integer> entry : distanciaMinima.entrySet()) {
                if (entry.getValue() == Integer.MAX_VALUE) {
                    entry.setValue(0);
                }

                System.out.println("Adjacente: " + entry.getKey() + ", Peso: " + entry.getValue());

                if (verticesImpares.contains(entry.getKey())) {
                    matriz[i][j] = entry.getValue();
                    j++;
                }
            }

            for (String vertice : grafo.keySet()) {
                distancia.put(vertice, 0);
                distanciaMinima.put(vertice, Integer.MAX_VALUE);
            }

            verticesVisitados.clear();
            i++;
            j = 0;
        }

        return matriz;
    }

    public void imprimeMatrizDistancia(int[][] matriz) {
        System.out.println();
        System.out.println("Matriz D:");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print((matriz[i][j]) + " ");
            }
            System.out.println();
        }
    }

    public void imprimeCicloEulerianoCustoTotal(int[][] matriz) {
        // ciclo euleriano
        int menorValor = Integer.MAX_VALUE;
        int valorTotal = 0;
        HashMap<String, Integer> verticesMenoresCustos = new HashMap<>();

        System.out.println();
        System.out.println("Ciclo Euleriano:");
        for (int i = 0; i < matriz.length; i++) {
            for (int z = 0; z < matriz.length; z++) {
                if (matriz[i][z] < menorValor && menorValor != matriz[i][z]
                        && matriz[i][z] != 0) {
                    verticesMenoresCustos.put(verticesImpares.get(i) + "-" + verticesImpares.get(z), matriz[i][z]);
                    matriz = removerLinhaColuna(matriz, i, i, z, z);
                    if (matriz.length > 0) {
                        verticesImpares.remove(i);
                        verticesImpares.remove(i);
                    }
                    break;
                }
                menorValor = matriz[i][z] == 0 ? Integer.MAX_VALUE : matriz[i][z];
            }
            i = i - 1;
            menorValor = Integer.MAX_VALUE;
        }
        for (Map.Entry<String, Integer> entry : verticesMenoresCustos.entrySet()) {
            System.out.println("caminho: " + entry.getKey() + ", Custo: " + entry.getValue());
            valorTotal = valorTotal + entry.getValue();
        }
        System.out.println("Custo total: " + valorTotal);
    }


    public static int[][] removerLinhaColuna(int[][] matriz, int linha, int coluna, int linha1, int coluna2) {
        int novaMatriz[][] = new int[matriz.length - 2][matriz[0].length - 2];

        for (int i = 0, novaLinha = 0; i < matriz.length; i++) {
            if (i == linha || i == linha1) {
                continue; // Pular a linha que queremos remover
            }

            for (int j = 0, novaColuna = 0; j < matriz[i].length; j++) {
                if (j == coluna || j == coluna2) {
                    continue; // Pular a coluna que queremos remover
                }

                novaMatriz[novaLinha][novaColuna] = matriz[i][j];
                novaColuna++;
            }

            novaLinha++;
        }

        return novaMatriz;
    }
}