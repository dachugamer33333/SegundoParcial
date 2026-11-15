package AlgoritmoDijkstra;

import mx.unam.fes.grafos.Grafo;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        int numeroNodos = 5;
        
        // Creación del grafo utilizando tu nueva clase Grafo de la librería Herramientas (true = dirigido)
        Grafo<Integer> grafo = new Grafo<>(true);

        // Poblado del grafo con tus métodos personalizados (Nodos: 0, 1, 2, 3, 4)
        grafo.agregarArista(0, 1, 4.0); // Nodo 0 al 1, peso 4
        grafo.agregarArista(0, 2, 1.0); // Nodo 0 al 2, peso 1
        grafo.agregarArista(2, 1, 2.0); // Nodo 2 al 1, peso 2
        grafo.agregarArista(2, 3, 5.0); // Nodo 2 al 3, peso 5
        grafo.agregarArista(1, 3, 1.0); // Nodo 1 al 3, peso 1
        grafo.agregarArista(3, 4, 3.0); // Nodo 3 al 4, peso 3

        int nodoOrigen = 0;

        // Ejecución del algoritmo con tus estructuras personalizadas
        Dijkstra.ResultadoDijkstra<Integer> resultado = Dijkstra.calcularRutas(grafo, nodoOrigen);

        // Impresión de resultados matemáticos (Costos mínimos usando Mapas)
        System.out.println("--- Distancias mínimas desde el nodo " + nodoOrigen + " ---");
        for (int i = 0; i < numeroNodos; i++) {
            double costo = resultado.distancias.getOrDefault(i, Double.POSITIVE_INFINITY);
            System.out.println("Costo hacia nodo " + i + ": " + (costo == Double.POSITIVE_INFINITY ? "Infinito" : costo));
        }

        // Impresión de reconstrucción de rutas usando el método que creamos en ResultadoDijkstra
        System.out.println("\n--- Rutas óptimas exactas ---");
        for (int i = 1; i < numeroNodos; i++) {
            List<Integer> ruta = resultado.reconstruirCamino(i);
            System.out.print("Ruta hacia " + i + ": ");
            
            if (ruta.isEmpty()) {
                System.out.println("Sin ruta disponible");
            } else {
                for (int j = 0; j < ruta.size(); j++) {
                    System.out.print(ruta.get(j));
                    if (j < ruta.size() - 1) {
                        System.out.print(" -> ");
                    }
                }
                System.out.println();
            }
        }
    }
}
