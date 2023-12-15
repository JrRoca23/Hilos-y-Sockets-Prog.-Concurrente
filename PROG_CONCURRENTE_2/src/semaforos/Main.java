package semaforos;

public class Main {

    public static void main(String[] args) {
        int k = 5;  // Número total de unidades del recurso
        GestorRecursos gestorRecursos = new GestorRecursos(k);

        // Crear y ejecutar procesos
        for (int i = 1; i <= 10; i++) {
            Proceso proceso = new Proceso(gestorRecursos, i);
            Thread hilo = new Thread(proceso);
            hilo.start();
        }
    }
}