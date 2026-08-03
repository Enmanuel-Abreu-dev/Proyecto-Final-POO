package server;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servervidor extends Thread {

   public static void main(String[] args) {
      int puerto = 7000;
      ServerSocket soc = null;

      try {
         soc = new ServerSocket(puerto);
      } catch (IOException e) {
         e.printStackTrace();
         System.exit(1);
      }
      System.out.println("Server online on port: " + puerto);

      while (true) {
         try {
            Socket cliente = soc.accept();
            System.out.print("Client connect");

            ObjectInputStream data = new ObjectInputStream(cliente.getInputStream());
            guardarArchivo(data);

            cliente.close();
            System.out.print("Sesion termined");
            data.close();
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
   }

   public static void guardarArchivo(ObjectInputStream data) {
      try {
         File arch = new File("save.bin");

         ObjectOutputStream oi = new ObjectOutputStream(new FileOutputStream(arch));
         oi.writeObject(data.readObject());

         oi.close();
         System.out.print("Data saved");
      } catch (IOException | ClassNotFoundException e) {
         e.printStackTrace();
      }
   }
}
