package semaforos;

import java.util.concurrent.Semaphore;

public class GestorRecursos {
    private Semaphore mutex = new Semaphore(1);  // Exclusión mutua
    private Semaphore semRecursos;  // Semáforo para representar las unidades disponibles del recurso

    public GestorRecursos(int k) {
        semRecursos = new Semaphore(k);
    }

    // Operación de reserva
    public void reserva(int r) {
        try {
            mutex.acquire();  // Inicio de la sección crítica
            if (r <= semRecursos.availablePermits()) {
                semRecursos.acquire(r);
                mutex.release();  // Fin de la sección crítica

                // Realizar el trabajo con las r unidades del recurso reservadas
                System.out.println("Se reservaron " + r + " unidades del recurso.");

            } else {
                mutex.release();  // Fin de la sección crítica

                // No hay suficientes recursos disponibles, manejar el caso según sea necesario
                System.out.println("No hay suficientes recursos disponibles.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Operación de liberación
    public void libera(int l) {
        try {
            mutex.acquire();  // Inicio de la sección crítica
            semRecursos.release(l);
            mutex.release();  // Fin de la sección crítica

            System.out.println("Se liberaron " + l + " unidades del recurso.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}