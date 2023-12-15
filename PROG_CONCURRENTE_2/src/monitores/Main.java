package monitores;

public class Main {
    public static void main(String[] args) {
        // Se crea una instancia del puente
        Puente puente = new Puente();

        // Se crean instancias de coches del Norte y del Sur     
        try {
        	
        	int coches = 10;
        	
        	for (int i = 0; i < coches; i++) {
            	new CocheNorte(puente).start();
            	new CocheSur(puente).start();
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        
    }
}