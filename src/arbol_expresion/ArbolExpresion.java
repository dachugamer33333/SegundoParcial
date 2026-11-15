package arbol_expresion;

import mx.unam.fes.dinamico.Pila;
import mx.unam.fes.nolineal.ArbolBinarioOrden;
import mx.unam.fes.nolineal.Nodo;

public class ArbolExpresion extends ArbolBinarioOrden<String> {

    public boolean esOperador(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("^");
    }

    // ALGORITMO 1: Construir el arbol binario de una expresion prefija (pagina 15 del PDF)
    public void construirPrefija(String expresion) {
        String[] simbolos;
        if (expresion.contains(" ")) {
            simbolos = expresion.trim().split("\\s+");
        } else {
            simbolos = expresion.trim().split("");
        }

        int i = 0;

        // Obtener simbolo
        String simbolo = simbolos[i++];

        // Crear Nodo y poner simbolo en nodo nuevo
        Nodo<String> nuevoNodo = new Nodo<>(simbolo, "raiz");
        raiz = nuevoNodo;

        // sigMovimiento = izquierdo
        String sigMovimiento = "izquierdo";

        // Limpiar pila
        Pila<Nodo<String>> pila = new Pila<>();
        Nodo<String> ultimoNodo;

        // Obtener simbolo / While no sea fin de la cadena do
        while (i < simbolos.length) {
            simbolo = simbolos[i++];

            ultimoNodo = nuevoNodo;
            nuevoNodo = new Nodo<>(simbolo, "");

            if (sigMovimiento.equals("izquierdo")) {
                nuevoNodo.setEtiqueta("izquierdo, padre = " + ultimoNodo.getDato());
                ultimoNodo.setIzquierdo(nuevoNodo);
                pila.agregar(ultimoNodo);
            } else {
                ultimoNodo = pila.sacar();
                nuevoNodo.setEtiqueta("derecho, padre = " + ultimoNodo.getDato());
                ultimoNodo.setDerecho(nuevoNodo);
            }

            if (esOperador(simbolo)) {
                sigMovimiento = "izquierdo";
            } else {
                sigMovimiento = "derecho";
            }
        }
    }

    // ALGORITMO 2: Evaluar la expresion del arbol binario
    public double evaluar(Nodo<String> n) {
        if (n == null) {
            return 0;
        }

        String simbolo = n.getDato();

        if (esOperador(simbolo)) {
            double izq = evaluar(n.getIzquierdo());
            double der = evaluar(n.getDerecho());

            switch (simbolo) {
                case "+": return izq + der;
                case "-": return izq - der;
                case "*": return izq * der;
                case "/": return izq / der;
                case "^": return Math.pow(izq, der);
            }
        }

        return Double.parseDouble(simbolo);
    }

    // Recorrido en Orden (LVR) para mostrar la expresion interfija
    @Override
    public void orden(Nodo<String> n) {
        if (n != null) {
            if (esOperador(n.getDato())) {
                System.out.print("(");
            }
            orden(n.getIzquierdo());
            System.out.print(n.getDato());
            orden(n.getDerecho());
            if (esOperador(n.getDato())) {
                System.out.print(")");
            }
        }
    }
}
