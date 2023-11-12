import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

    private static HashMap<String, Integer> arestasImpares = new HashMap<>();

    public static void main(String[] args) {
        HashMap<String, Integer> arestaValorada = new HashMap<>();
        HashMap<String, Integer> agrupandoVertices = new HashMap<>();

        /*
        *
         A      B
         |      |
         |      |
         |      |
         C      D
         |  \   |
         |   \  |
         |    \ |
         E ---  F
         |  /   |
         | /    |
         |/     |
         G --- H
        *
        * */

        arestaValorada.put("a-c", 4);

        arestaValorada.put("c-a", 5);
        arestaValorada.put("c-d", 9);
        arestaValorada.put("c-e", 4);

        arestaValorada.put("e-c", 6);
        arestaValorada.put("e-f", 8);
        arestaValorada.put("e-g", 7);

        arestaValorada.put("g-e", 3);
        arestaValorada.put("g-h", 2);
        arestaValorada.put("g-f", 10);

        arestaValorada.put("h-g", 12);
        arestaValorada.put("h-f", 6);

        arestaValorada.put("f-h", 12);
        arestaValorada.put("f-e", 4);
        arestaValorada.put("f-d", 6);
        arestaValorada.put("f-c", 1);



        for (Map.Entry<String, Integer> entry : arestaValorada.entrySet()) {
            String[] chave = entry.getKey().split("-");
            if (!agrupandoVertices.isEmpty()) {
                agrupandoVertices.put(chave[0], agrupandoVertices.get(chave[0]) != null ? agrupandoVertices.get(chave[0]) + 1 : 1);
            } else {
                agrupandoVertices.put(chave[0], 1);
            }
        }
        //IDENTIFICANDO VERTICES IMPARES
        for (Map.Entry<String, Integer> entry : agrupandoVertices.entrySet()) {
            String chave = entry.getKey();
            Integer valor = entry.getValue();
            if (valor % 2 != 0) {
                addVerticesImpares(chave, valor);
            }
        }

    }

    public static void addVerticesImpares(String chave, Integer valor) {
        arestasImpares.put(chave, valor);
    }

}
