package monitores;

public class CocheNorte extends Thread {
    private Puente puente;

    // Constructor que recibe una instancia del puente
    public CocheNorte(Puente puente) {
        this.puente = puente;
    }

    // Método que se ejecuta cuando el hilo del coche del Norte arranca
    @Override
    public void run() {
        puente.cruzarPuenteDesdeElNorte();
    }
}
