package monitores;

import java.util.concurrent.locks.*;

//Monitor para coordinar el cruce de coches en el puente
public class Puente {
	 private boolean cocheSurCruzando;
	 private boolean cocheNorteCruzando;
	
	 private final Lock lock;             // Cerrojo para garantizar exclusión mutua
	 private final Condition esperaSur;   // Condición para coches que vienen del Sur
	 private final Condition esperaNorte; // Condición para coches que vienen del Norte

 // Constructor para inicializar el monitor
 public Puente() {
     cocheSurCruzando = false;
     cocheNorteCruzando = false;
     lock = new ReentrantLock();
     esperaSur = lock.newCondition();
     esperaNorte = lock.newCondition();
 }

 // Método para que un coche del Norte cruce el puente
 public void cruzarPuenteDesdeElNorte() {
     lock.lock();
     try {
         // Mientras haya coches del Sur cruzando, espera
         while (cocheSurCruzando) {
             esperaNorte.await();
         }

         // Marca que un coche del Norte está cruzando
         cocheNorteCruzando = true;
         System.out.println("Coche del Norte cruzando el puente.");
         cocheNorteCruzando = false;

         // Señala a los coches del Sur que pueden intentar cruzar
         esperaSur.signal();
     } catch (InterruptedException e) {
         e.printStackTrace();
     } finally {
         lock.unlock();
     }
 }

 // Método para que un coche del Sur cruce el puente
 public void cruzarPuenteDesdeElSur() {
     lock.lock();
     try {
         // Mientras haya coches del Norte cruzando, espera
         while (cocheNorteCruzando) {
             esperaSur.await();
         }

         // Marca que un coche del Sur está cruzando
         cocheSurCruzando = true;
         System.out.println("Coche del Sur cruzando el puente.");
         cocheSurCruzando = false;

         // Señala a los coches del Norte que pueden intentar cruzar
         esperaNorte.signal();
     } catch (InterruptedException e) {
         e.printStackTrace();
     } finally {
         lock.unlock();
     }
 }
}