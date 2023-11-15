import furb.Aresta;
import furb.CarteiroChines;

import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        CarteiroChines carteiroChines = new CarteiroChines();

        carteiroChines.addAresta(new Aresta("a", "b", 12));
        carteiroChines.addAresta(new Aresta("a", "d", 2));
        carteiroChines.addAresta(new Aresta("a", "h", 8));

        carteiroChines.addAresta(new Aresta("b", "a", 12));
        carteiroChines.addAresta(new Aresta("b", "c", 3));
        carteiroChines.addAresta(new Aresta("b", "i", 10));

        carteiroChines.addAresta(new Aresta("c", "b", 3));
        carteiroChines.addAresta(new Aresta("c", "d", 2));
        carteiroChines.addAresta(new Aresta("c", "e", 2));
        carteiroChines.addAresta(new Aresta("c", "f", 1));

        carteiroChines.addAresta(new Aresta("d", "a", 2));
        carteiroChines.addAresta(new Aresta("d", "c", 2));
        carteiroChines.addAresta(new Aresta("d", "e", 1));
        carteiroChines.addAresta(new Aresta("d", "g", 1));

        carteiroChines.addAresta(new Aresta("e", "c", 2));
        carteiroChines.addAresta(new Aresta("e", "d", 1));
        carteiroChines.addAresta(new Aresta("e", "f", 3));
        carteiroChines.addAresta(new Aresta("e", "g", 2));

        carteiroChines.addAresta(new Aresta("f", "c", 1));
        carteiroChines.addAresta(new Aresta("f", "e", 3));
        carteiroChines.addAresta(new Aresta("f", "i", 4));
        carteiroChines.addAresta(new Aresta("f", "g", 2));

        carteiroChines.addAresta(new Aresta("g", "d", 1));
        carteiroChines.addAresta(new Aresta("g", "e", 2));
        carteiroChines.addAresta(new Aresta("g", "f", 2));
        carteiroChines.addAresta(new Aresta("g", "h", 5));


        carteiroChines.addAresta(new Aresta("h", "a", 8));
        carteiroChines.addAresta(new Aresta("h", "g", 5));
        carteiroChines.addAresta(new Aresta("h", "i", 12));

        carteiroChines.addAresta(new Aresta("i", "h", 12));
        carteiroChines.addAresta(new Aresta("i", "f", 4));
        carteiroChines.addAresta(new Aresta("i", "b", 10));

        for (String  aresta1 : carteiroChines.getGrafo().keySet()) {
            for(Aresta aresta : carteiroChines.getGrafo().get(aresta1)) {
                List<Aresta> filtroOrigem =  carteiroChines.getGrafo().get(aresta1).stream().filter(item -> item.getOrigem().equals(aresta.getOrigem())).collect(Collectors.toList());
                if (!carteiroChines.getVerticesImpares().contains(aresta.getOrigem())) {
                    if (filtroOrigem.size() % 2 != 0) {
                        carteiroChines.addVerticesImpares(filtroOrigem.getFirst().getOrigem());
                    }
                }
            }
        }
       carteiroChines.dijkstra();
    }

}
