package AlgoritmoDijkstra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        int numeroNodos = 5;
        // Creación del grafo como Lista de Listas de adyacencia
        List<List<Dijkstra.Arista>> grafo = new ArrayList<>();

        // Inicializar las sub-listas para cada nodo
        for (int i = 0; i < numeroNodos; i++) {
            grafo.add(new ArrayList<>());
        }

        // Poblado del grafo (Nodos: 0=A, 1=B, 2=C, 3=D, 4=E)
        grafo.get(0).add(new Dijkstra.Arista(1, 4)); // Nodo 0 al 1, peso 4
        grafo.get(0).add(new Dijkstra.Arista(2, 1)); // Nodo 0 al 2, peso 1
        grafo.get(2).add(new Dijkstra.Arista(1, 2)); // Nodo 2 al 1, peso 2
        grafo.get(2).add(new Dijkstra.Arista(3, 5)); // Nodo 2 al 3, peso 5
        grafo.get(1).add(new Dijkstra.Arista(3, 1)); // Nodo 1 al 3, peso 1
        grafo.get(3).add(new Dijkstra.Arista(4, 3)); // Nodo 3 al 4, peso 3

        int nodoOrigen = 0;

        // Ejecución del algoritmo
        Dijkstra.ResultadoDijkstra resultado = Dijkstra.calcularRutas(grafo, nodoOrigen);

        // Impresión de resultados matemáticos (Costos mínimos)
        System.out.println("--- Distancias mínimas desde el nodo " + nodoOrigen + " ---");
        for (int i = 0; i < resultado.distancias.length; i++) {
            System.out.println("Costo hacia nodo " + i + ": " + resultado.distancias[i]);
        }

        // Impresión de reconstrucción de rutas
        System.out.println("\n--- Rutas óptimas exactas ---");
        for (int i = 1; i < numeroNodos; i++) {
            System.out.print("Ruta hacia " + i + ": ");
            imprimirRutaPasoAPaso(nodoOrigen, i, resultado.predecesores);
        }
    }

    /**
     * Método auxiliar que lee el arreglo de predecesores de reversa
     * para imprimir la ruta exacta desde el origen hasta el destino.
     */
    private static void imprimirRutaPasoAPaso(int origen, int destino, int[] predecesores) {
        // Validar si el nodo es inalcanzable
        if (destino != origen && predecesores[destino] == -1) {
            System.out.println("Sin ruta disponible");
            return;
        }

        List<Integer> ruta = new ArrayList<>();
        int actual = destino;

        // Rastreo de reversa hasta llegar al nodo origen (donde el predecesor es -1)
        while (actual != -1) {
            ruta.add(actual);
            actual = predecesores[actual];
        }

        // Invertir la lista para mostrar el orden lógico (Origen -> Destino)
        Collections.reverse(ruta);

        // Formatear la salida visualmente
        for (int i = 0; i < ruta.size(); i++) {
            System.out.print(ruta.get(i));
            if (i < ruta.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }
}
