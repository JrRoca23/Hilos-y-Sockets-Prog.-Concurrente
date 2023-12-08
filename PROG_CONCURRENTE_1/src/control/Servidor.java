package control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor implements Runnable {
	
	private Socket clientSocket;
	
	public Servidor(Socket clientSocket) {
		this.clientSocket = clientSocket; 
	}
	
	
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(5001);
			System.out.println("Servidor de Chat iniciado.");
			
			int i = 0; 
			
			while (true) {
				Socket clientSocket2 = serverSocket.accept(); //me quedo esperando que entre una solicitud.
				System.out.println("Cliente conectado desde: " + clientSocket2.getInetAddress());
				new Thread(new Servidor(clientSocket2), "Client" + i++).start();
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		
		String inputLine;

        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out.println("¡Bienvenido! Este es el chat de atención al cliente de PackageExpress.");
            out.println("Selecciona una de las siguientes opciones:");
            out.println("1. ¿Cuál es el estado de mi pedido?");
            out.println("2. ¿Cómo puedo devolver un producto?");
            out.println("3. ¿Cuáles son sus horarios de atención al cliente?");
            out.println("4. Necesito ayuda con la configuración de mi cuenta.");
            out.println("5. ¿Puede proporcionarme información sobre sus promociones actuales?");
            out.println("END");

            // Espera el mensaje del cliente
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Llego Esto: " + inputLine); //

                switch (inputLine) {
                    case "1":
                        out.println("Ingrese su código de seguimiento para verificar el estado de su pedido.");
                        out.println("END");
                        break;
                    case "2":
                        out.println("Si desea devolver un producto, ingrese su código de pedido y porsteriormente rellene el formulario.");
                        out.println("END");
                        break;
                    case "3":
                    	out.println("Nuestro horario de atención al cliente es de lunes a viernes de 9:00 a.m. a 6:00 p.m. \n"
                    			+ "Puedes contactarnos en cualquier momento a través de nuestro chat en línea.");
                        out.println("END");
                        break;
                    case "4":
                        out.println("¡Claro que sí! Dime en qué parte de la configuración necesitas ayuda.");
                        out.println("END");
                        break;
                    case "5":
                        out.println("¡Tenemos varias promociones interesantes!. Pero deben ser gestionadas por uno de nuestros asistentes.");
                        out.println("END");
                        break;
                    default:
                        out.println("Opción no válida. Por favor, elige una opción del 1 al 5.");
                        out.println("END");
                }
            }
            
            clientSocket.close();
        }
		
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
