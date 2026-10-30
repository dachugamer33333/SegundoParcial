package arbol_expresion;

public class PruebaArbolExpresion {

    public static void main(String[] args) {
        ArbolExpresion arbol = new ArbolExpresion();

        // Ejemplo 1: Con numeros de dos digitos -> ((25*3)+10) = 85.0
        arbol.construirPrefija("+ * 25 3 10");
        arbol.recorridoAmplitud();
        System.out.print("Expresion interfija: ");
        arbol.orden(arbol.getRaiz());
        System.out.println("\nResultado: " + arbol.evaluar(arbol.getRaiz()));

        System.out.println("----");

        // Ejemplo 2: De un digito sin espacios -> ((4*3)+5) = 17.0
        arbol.construirPrefija("+*435");
        arbol.recorridoAmplitud();
        System.out.print("Expresion interfija: ");
        arbol.orden(arbol.getRaiz());
        System.out.println("\nResultado: " + arbol.evaluar(arbol.getRaiz()));
    }
}