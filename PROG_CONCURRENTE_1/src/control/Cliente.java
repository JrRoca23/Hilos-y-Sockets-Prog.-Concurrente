package control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5001);
            // Buffer de lectura
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // Buffer de escritura
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);

            while (true) {
                // Mostrar opciones del servidor
                String inputLine;
                while (!(inputLine = in.readLine()).equals("END")) {
                    System.out.println(inputLine);
                }

                // Solicitar la opción del usuario y enviarla al servidor
                String userChoice = scanner.nextLine();
                out.println(userChoice);

                // Mostrar la respuesta del servidor
                while (!(inputLine = in.readLine()).equals("END")) {
                    System.out.println(inputLine);
                }
                
                // Si el usuario no desea continuar y escribe "no", para salir del bucle principal
                String continuar = scanner.nextLine().toLowerCase();
                out.println(continuar);
                
                if (continuar.equals("no")) {
                    break;
                }
            }

            // Cerrar la conexión al salir del bucle principal
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
