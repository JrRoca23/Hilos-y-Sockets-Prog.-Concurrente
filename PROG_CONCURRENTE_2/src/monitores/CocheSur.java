package monitores;

public class CocheSur extends Thread {
    private Puente puente;

    // Constructor que recibe una instancia del puente
    public CocheSur(Puente puente) {
        this.puente = puente;
    }

    // Método que se ejecuta cuando el hilo del coche del Sur arranca
    @Override
    public void run() {
        puente.cruzarPuenteDesdeElSur();
    }
}
