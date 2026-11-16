package AlgoritmoDijkstra;

import mx.unam.fes.nolineal.grafos.Grafo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Main {

    // Archivo en la raiz del proyecto, dentro de la carpeta "archivos"
    private static final Path ARCHIVO = Paths.get("archivos", "grafo.txt");

    private static class Conexion {
        final int origen;
        final int destino;
        final double peso;

        Conexion(int origen, int destino, double peso) {
            this.origen = origen;
            this.destino = destino;
            this.peso = peso;
        }
    }

    private static class DatosGrafo {
        final Grafo<Integer> grafo;
        final Integer origen;

        DatosGrafo(Grafo<Integer> grafo, Integer origen) {
            this.grafo = grafo;
            this.origen = origen;
        }
    }

    public static void main(String[] args) {
        DatosGrafo datos;
        try {
            datos = cargarGrafo(ARCHIVO);
        } catch (IOException e) {
            System.err.println("No se pudo leer " + ARCHIVO.toAbsolutePath() + ": " + e.getMessage());
            return;
        }

        if (datos == null) {
            return;
        }

        Grafo<Integer> grafo = datos.grafo;
        Set<Integer> vertices = new TreeSet<>(grafo.obtenerVertices());
        int nodoOrigen = datos.origen != null ? datos.origen : vertices.iterator().next();

        System.out.println("--- Grafo " + (grafo.esDirigido() ? "dirigido" : "no dirigido")
                + " | nodos: " + vertices.size() + " | origen: " + nodoOrigen + " ---");

        // Ejecución del algoritmo con tus estructuras personalizadas
        Dijkstra.ResultadoDijkstra<Integer> resultado = Dijkstra.calcularRutas(grafo, nodoOrigen);

        // Impresión de resultados matemáticos (Costos mínimos usando Mapas)
        System.out.println("--- Distancias mínimas desde el nodo " + nodoOrigen + " ---");
        for (int nodo : vertices) {
            double costo = resultado.distancias.getOrDefault(nodo, Double.POSITIVE_INFINITY);
            System.out.println("Costo hacia nodo " + nodo + ": "
                    + (costo == Double.POSITIVE_INFINITY ? "Infinito" : costo));
        }

        // Impresión de reconstrucción de rutas usando el método que creamos en ResultadoDijkstra
        System.out.println("\n--- Rutas óptimas exactas ---");
        for (int nodo : vertices) {
            if (nodo == nodoOrigen) {
                continue;
            }

            System.out.print("Ruta hacia " + nodo + ": ");
            double costo = resultado.distancias.getOrDefault(nodo, Double.POSITIVE_INFINITY);

            if (costo == Double.POSITIVE_INFINITY) {
                System.out.println("Sin ruta disponible");
                continue;
            }

            List<Integer> ruta = resultado.reconstruirCamino(nodo);
            for (int j = 0; j < ruta.size(); j++) {
                System.out.print(ruta.get(j));
                if (j < ruta.size() - 1) {
                    System.out.print(" -> ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Lee el grafo desde el archivo indicado.
     *
     * Formato:
     *   - Líneas vacías y texto después de '#' se ignoran.
     *   - Primera línea de datos: "dirigido" o "no dirigido".
     *   - Aristas: "origen destino peso" (separados por espacios o tabulaciones).
     *   - Origen opcional: "origen=N".
     *
     * Devuelve null si el archivo no existe o tiene errores (ya reportados por consola).
     */
    private static DatosGrafo cargarGrafo(Path archivo) throws IOException {
        if (!Files.exists(archivo)) {
            System.err.println("No se encontró el archivo " + archivo.toAbsolutePath());
            return null;
        }

        List<String> lineas = Files.readAllLines(archivo, StandardCharsets.UTF_8);
        Boolean esDirigido = null;
        Integer origen = null;
        List<Conexion> conexiones = new ArrayList<>();

        for (int i = 0; i < lineas.size(); i++) {
            int numeroLinea = i + 1;
            String linea = lineas.get(i);

            int comentario = linea.indexOf('#');
            if (comentario >= 0) {
                linea = linea.substring(0, comentario);
            }
            linea = linea.trim();

            if (linea.isEmpty()) {
                continue;
            }

            if (esDirigido == null) {
                String tipo = linea.toLowerCase().replaceAll("\\s+", " ");
                if (tipo.equals("dirigido")) {
                    esDirigido = Boolean.TRUE;
                } else if (tipo.equals("no dirigido")) {
                    esDirigido = Boolean.FALSE;
                } else {
                    System.err.println("Error en la línea " + numeroLinea
                            + ": se esperaba 'dirigido' o 'no dirigido' y se encontró '" + linea + "'");
                    return null;
                }
                continue;
            }

            if (linea.toLowerCase().startsWith("origen=")) {
                String valor = linea.substring("origen=".length()).trim();
                try {
                    origen = Integer.valueOf(valor);
                } catch (NumberFormatException e) {
                    System.err.println("Error en la línea " + numeroLinea
                            + ": el origen debe ser un número entero, se encontró '" + valor + "'");
                    return null;
                }
                continue;
            }

            String[] partes = linea.split("\\s+");
            if (partes.length != 3) {
                System.err.println("Error en la línea " + numeroLinea
                        + ": se esperaba 'origen destino peso' y se encontró '" + linea + "'");
                return null;
            }

            int nodoOrigen;
            int nodoDestino;
            double peso;
            try {
                nodoOrigen = Integer.parseInt(partes[0]);
                nodoDestino = Integer.parseInt(partes[1]);
                peso = Double.parseDouble(partes[2]);
            } catch (NumberFormatException e) {
                System.err.println("Error en la línea " + numeroLinea
                        + ": origen y destino deben ser enteros y el peso numérico ('" + linea + "')");
                return null;
            }

            if (peso < 0) {
                System.err.println("Error en la línea " + numeroLinea
                        + ": el peso no puede ser negativo (" + peso + ")");
                return null;
            }

            conexiones.add(new Conexion(nodoOrigen, nodoDestino, peso));
        }

        if (esDirigido == null) {
            System.err.println("El archivo está vacío o no indica el tipo de grafo. "
                    + "La primera línea debe ser 'dirigido' o 'no dirigido'.");
            return null;
        }

        if (conexiones.isEmpty()) {
            System.err.println("El archivo no contiene aristas.");
            return null;
        }

        Grafo<Integer> grafo = new Grafo<>(esDirigido);
        for (Conexion conexion : conexiones) {
            grafo.agregarArista(conexion.origen, conexion.destino, conexion.peso);
        }

        if (origen != null && !grafo.obtenerVertices().contains(origen)) {
            System.err.println("El nodo origen " + origen + " no aparece en ninguna arista del grafo.");
            return null;
        }

        return new DatosGrafo(grafo, origen);
    }
}
