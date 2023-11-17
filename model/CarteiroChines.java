// Alunas: Karoline Custodio dos Santos e Maria Eduarda Redes

package model;

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
        List<String> filaPrioridade = new ArrayList<>(); // Inicializa com todos os vértices
        Map<String, Integer> distanciaMinima = new HashMap<>();
        List<String> verticesVisitados = new ArrayList<>();
        String verticeAtual = "";
        String verticeDijkstra = "";
        Integer[][] matriz = new Integer[4][4];
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
                
            	System.out.println("Chave: " + entry.getKey() + ", Valor: " + entry.getValue());    

            	if(verticesImpares.contains(entry.getKey())) {
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
            j=0;
       }   
        
        System.out.println();
        System.out.println("Matriz D:");
        for (i = 0; i < 4; i++) {
            for ( j = 0; j < 4; j++) {
                System.out.print((matriz[i][j] ) + " ");
            }
            System.out.println();
        }   
            
        // ciclo euleriano
        int menorValor = Integer.MAX_VALUE;
        int linhaMenorValor = -1;
        int colunaMenorValor = -1;
        
        System.out.println("Ciclo Euleriano:");
        for (i = 0; i < matriz.length; i++) {
            for (j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j] < menorValor && menorValor!= 0) {
                    menorValor = matriz[i][j];
                    linhaMenorValor = i;
                    colunaMenorValor = j;
                    System.out.print((menorValor ) + " ");
                }
            }
        }
    }
}