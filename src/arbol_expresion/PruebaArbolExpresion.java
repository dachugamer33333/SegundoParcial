package arbol_expresion;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class PruebaArbolExpresion {

    // Archivo en la raiz del proyecto, dentro de la carpeta "archivos"
    private static final Path ARCHIVO = Paths.get("archivos", "prefija.txt");

    public static void main(String[] args) throws Exception {
        List<String> lineas = Files.readAllLines(ARCHIVO, StandardCharsets.UTF_8);

        ArbolExpresion arbol = new ArbolExpresion();
        int procesadas = 0;

        // Cada linea no vacia del archivo es una expresion prefija
        for (String linea : lineas) {
            String expresion = linea.trim();
            if (expresion.isEmpty()) {
                continue;
            }

            arbol.construirPrefija(expresion);
            arbol.recorridoAmplitud();
            System.out.print("Expresion interfija: ");
            arbol.orden(arbol.getRaiz());
            System.out.println("\nResultado: " + arbol.evaluar(arbol.getRaiz()));
            System.out.println("----");
            procesadas++;
        }

        if (procesadas == 0) {
            System.out.println("No hay expresiones en " + ARCHIVO.toAbsolutePath());
        }
    }
}
