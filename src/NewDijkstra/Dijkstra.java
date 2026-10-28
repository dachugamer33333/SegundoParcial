package NewDijkstra;

import mx.unam.fes.estatico.Arreglo;
import mx.unam.fes.exepciones.IndicieFueraExeption;
import mx.unam.fes.dinamico.ListaSimple.ListaEnlazada; 

public class Dijkstra {

    public static void calcularRutas(Arreglo<ListaEnlazada<Arista>> grafo, int origen, int vertices) throws IndicieFueraExeption {
        
        Arreglo<Integer> distancias = new Arreglo<>(vertices);
        Arreglo<Boolean> nodosVisitados = new Arreglo<>(vertices);

        for (int i = 0; i < vertices; i++) {
            distancias.asignar(Integer.MAX_VALUE, i);
            nodosVisitados.asignar(false, i);
        }

        distancias.asignar(0, origen);

        for (int i = 0; i < vertices - 1; i++) {
            int u = obtenerVerticeMinimo(distancias, nodosVisitados, vertices);
            nodosVisitados.asignar(true, u);

            ListaEnlazada<Arista> vecinos = grafo.recuperar(u);
            
            for (int j = 0; j < vecinos.getLongitud(); j++) {
                Arista arista = vecinos.obtenerNodo(j);
                int v = arista.destino;
                int peso = arista.peso;

                if (!nodosVisitados.recuperar(v) &&
                    distancias.recuperar(u) != Integer.MAX_VALUE &&
                    distancias.recuperar(u) + peso < distancias.recuperar(v)) {
                    
                    distancias.asignar(distancias.recuperar(u) + peso, v);
                }
               
            }
        }
        imprimirResultados(distancias, origen, vertices);
    }

    private static int obtenerVerticeMinimo(Arreglo<Integer> distancias, Arreglo<Boolean> nodosVisitados, int vertices) throws IndicieFueraExeption {
        int minimo = Integer.MAX_VALUE;
        int indiceMinimo = -1;

        for (int v = 0; v < vertices; v++) {
            if (!nodosVisitados.recuperar(v) && distancias.recuperar(v) <= minimo) {
                minimo = distancias.recuperar(v);
                indiceMinimo = v;
            }
        }
        return indiceMinimo;
    }

    private static void imprimirResultados(Arreglo<Integer> distancias, int origen, int vertices) throws IndicieFueraExeption {
        System.out.println("Distancias mínimas desde el nodo origen (" + origen + "):");
        for (int i = 0; i < vertices; i++) {
            System.out.println("Hacia el nodo " + i + " = " + distancias.recuperar(i));
        }
    }
}

class Arista {
    int destino, peso;
    public Arista(int destino, int peso) {
        this.destino = destino;
        this.peso = peso;
    }
}