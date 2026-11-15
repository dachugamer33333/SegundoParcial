package AlgoritmoDijkstra;

import mx.unam.fes.grafos.Grafo;
import mx.unam.fes.grafos.Arista;
import mx.unam.fes.dinamico.ListaSimple.ListaEnlazada;
import mx.unam.fes.dinamico.ListaSimple.Nodo;

import java.util.*;

public class Dijkstra {

    public static class NodoDistancia<T> implements Comparable<NodoDistancia<T>> {
        public T vertice;
        public double distancia;

        public NodoDistancia(T vertice, double distancia) {
            this.vertice = vertice;
            this.distancia = distancia;
        }

        @Override
        public int compareTo(NodoDistancia<T> otro) {
            return Double.compare(this.distancia, otro.distancia);
        }
    }

    public static class ResultadoDijkstra<T> {
        public final Map<T, Double> distancias;
        public final Map<T, T> predecesores;

        public ResultadoDijkstra(Map<T, Double> distancias, Map<T, T> predecesores) {
            this.distancias = distancias;
            this.predecesores = predecesores;
        }

        public List<T> reconstruirCamino(T destino) {
            LinkedList<T> camino = new LinkedList<>();
            T actual = destino;

            if (!predecesores.containsKey(destino) && !distancias.containsKey(destino)) {
                return camino;
            }

            while (actual != null) {
                camino.addFirst(actual);
                actual = predecesores.get(actual);
            }
            return camino;
        }
    }

    public static <T> ResultadoDijkstra<T> calcularRutas(Grafo<T> grafo, T origen) {
        Map<T, Double> distancias = new HashMap<>();
        Map<T, T> predecesores = new HashMap<>();
        Set<T> visitados = new HashSet<>();

        for (T vertice : grafo.obtenerVertices()) {
            distancias.put(vertice, Double.POSITIVE_INFINITY);
        }
        distancias.put(origen, 0.0);

        PriorityQueue<NodoDistancia<T>> colaPrioridad = new PriorityQueue<>();
        colaPrioridad.offer(new NodoDistancia<>(origen, 0.0));

        while (!colaPrioridad.isEmpty()) {
            NodoDistancia<T> actual = colaPrioridad.poll();
            T u = actual.vertice;

            if (visitados.contains(u)) {
                continue;
            }
            visitados.add(u);

            ListaEnlazada<Arista<T>> adyacentes = grafo.obtenerAdyacentes(u);
            Nodo<Arista<T>> temp = adyacentes.getCabeza();

            while (temp != null) {
                Arista<T> arista = temp.getDato();
                T v = arista.getDestino();
                double peso = arista.getPeso();

                if (!visitados.contains(v) && distancias.get(u) + peso < distancias.get(v)) {
                    double nuevaDistancia = distancias.get(u) + peso;
                    distancias.put(v, nuevaDistancia);
                    predecesores.put(v, u);
                    colaPrioridad.offer(new NodoDistancia<>(v, nuevaDistancia));
                }

                temp = temp.getSiguiente();
            }
        }

        return new ResultadoDijkstra<>(distancias, predecesores);
    }
}