package AlgoritmoDijkstra;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
public class Dijkstra {
    // Uso de 'record' para estructuras de datos inmutables y sin código boilerplate
    public record Arista(int destino, int peso) {}
    
    public record NodoDistancia(int nodo, int distanciaAcumulada) implements Comparable<NodoDistancia> {
        @Override
        public int compareTo(NodoDistancia otro) {
            return Integer.compare(this.distanciaAcumulada, otro.distanciaAcumulada);
        }
    }

    // Clase contenedora para devolver múltiples arreglos de estado sin variables globales
    public static class ResultadoDijkstra {
        public final int[] distancias;
        public final int[] predecesores;

        public ResultadoDijkstra(int[] distancias, int[] predecesores) {
            this.distancias = distancias;
            this.predecesores = predecesores;
        }
    }

    /**
     * Calcula las distancias más cortas desde un nodo origen a todos los demás.
     * 
     * @param grafo Lista de adyacencia que representa la red de nodos.
     * @param origen Índice del nodo donde inicia la búsqueda.
     * @return Objeto ResultadoDijkstra con los costos mínimos y el rastro de la ruta.
     */
    public static ResultadoDijkstra calcularRutas(List<List<Arista>> grafo, int origen) {
        int n = grafo.size();
        int[] distancias = new int[n];
        int[] predecesores = new int[n];
        boolean[] visitados = new boolean[n];

        // Inicialización del estado matemático del grafo
        Arrays.fill(distancias, Integer.MAX_VALUE);
        Arrays.fill(predecesores, -1);
        distancias[origen] = 0;

        // El motor de búsqueda que prioriza la ruta más barata actual
        PriorityQueue<NodoDistancia> colaPrioridad = new PriorityQueue<>();
        colaPrioridad.offer(new NodoDistancia(origen, 0));

        while (!colaPrioridad.isEmpty()) {
            NodoDistancia actual = colaPrioridad.poll();
            int u = actual.nodo;

            // Evitar reevaluar nodos que ya encontraron su camino óptimo definitivo
            if (visitados[u]) {
                continue;
            }
            visitados[u] = true;

            // Escaneo de nodos adyacentes
            for (Arista arista : grafo.get(u)) {
                int v = arista.destino;
                int peso = arista.peso;

                // Relajación de la arista: actualizar si encontramos un atajo
                if (!visitados[v] && distancias[u] + peso < distancias[v]) {
                    distancias[v] = distancias[u] + peso;
                    predecesores[v] = u;
                    colaPrioridad.offer(new NodoDistancia(v, distancias[v]));
                }
            }
        }

        return new ResultadoDijkstra(distancias, predecesores);
    }
}

