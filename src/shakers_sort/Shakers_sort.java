package shakers_sort;

public class Shakers_sort {

    public void shakers_sort(int[] arreglo) {
        int IZQ= 0;
        int DER = arreglo.length - 1;  // apuntadores
        
                while (IZQ< DER) {
            
            // aqui es la primera barrida que hace que el numero mas grande este hasta la derecha 
            for (int j = IZQ; j < DER; j++) { //Inicializamos j en IZQ porque iremos comprobando si es > o <
                if (arreglo[j] > arreglo[j + 1]) {
                    //(swap) se hace si mi arreglo anterior es mayor al sigueinte, guardamos el indice del ultimo cambio para no comparar hasta alla 
                    int temp = arreglo[j];
                    arreglo[j] = arreglo[j + 1];
                    arreglo[j + 1] = temp;
                }
            }
            DER--; // Reducimos el límite derecho porque el mayor ya quedó al final
            
            // aqui es la pasada de derecha a izquierda qu elleva al menor hasta el inicio 
            for (int j = DER; j > IZQ; j--) {
                if (arreglo[j] < arreglo[j - 1]) {
                    //(swap)
                    int temp = arreglo[j];
                    arreglo[j] = arreglo[j - 1];
                    arreglo[j - 1] = temp;
                }
            }
            IZQ++; // Aumentamos el límite izquierdo porque el menor ya quedó al inicio
        }
    }
}