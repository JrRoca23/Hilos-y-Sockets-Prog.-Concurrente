package semaforos;

public class Proceso implements Runnable {
    private GestorRecursos gestorRecursos;
    private int id;

    public Proceso(GestorRecursos gestorRecursos, int id) {
        this.gestorRecursos = gestorRecursos;
        this.id = id;
    }

    @Override
    public void run() {
        // Realizar operaciones de reserva y liberación según sea necesario
        gestorRecursos.reserva(2 * id);
        // Realizar trabajo con las unidades del recurso reservadas
        gestorRecursos.libera(id);
    }
}