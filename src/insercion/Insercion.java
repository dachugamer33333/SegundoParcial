package insercion;

import mx.unam.fes.estatico.Arreglo;
import mx.unam.fes.exepciones.IndicieFueraExeption;

/**
 * Ordenamiento por insercion directa sobre un Arreglo generico.
 * Sigue el algoritmo Insercion (A, N) del libro, ajustado a indices desde 0.
 */
public class Insercion {

	/**
	 * Ordena el arreglo de menor a mayor con insercion directa.
	 *
	 * @param a arreglo que se va a ordenar
	 * @param <E> tipo de los elementos, debe implementar Comparable
	 * @throws IndicieFueraExeption si ocurre un error de posicion en el arreglo
	 */
	public static <E extends Comparable<? super E>> void insercion(Arreglo<E> a)
			throws IndicieFueraExeption {
		int n = a.numElementos();
		for (int i = 1; i < n; i++) {
			E temp = a.recuperar(i);         
			int k = i - 1;                   
			while (k >= 0 && temp.compareTo(a.recuperar(k)) < 0) {
				a.asignar(a.recuperar(k), k + 1); 
				k--;                             
			}
			a.asignar(temp, k + 1);           
		}
	}

	/**
	 * Prueba del ordenamiento con los valores de la tabla 8.3 del libro.
	 */
	public static void main(String[] args) throws IndicieFueraExeption {
		Arreglo<Integer> a = new Arreglo<>(8);
		int[] datos = {44, 16, 67, 8, 15, 27, 12, 35};
		for (int d : datos) {
			a.insertar(d);
		}
		System.out.print("Antes:   ");
		a.imprimir();
		insercion(a);
		System.out.print("Despues: ");
		a.imprimir();
	}
}