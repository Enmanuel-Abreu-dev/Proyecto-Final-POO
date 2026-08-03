package server;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Servidor extends Thread {
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
            System.out.println("> Client connect " + cliente.getInetAddress() + " at " + LocalDate.now().toString());
            ObjectInputStream data = new ObjectInputStream(cliente.getInputStream());
            guardarArchivo(data);
            cliente.close();
            System.out.println("> Sesion termined");
            data.close();
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
   }

   public static void guardarArchivo(ObjectInputStream data) {
      try {
         File arch = new File("./backups/save.bin");

         // Si ya existe un archivo previo, lo renombramos con la fecha/hora actual
         if (arch.exists()) {
            String fecha = LocalDateTime.now().format(
                  DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            File respaldo = new File("./backups/save_" + fecha + ".bin");
            boolean renombrado = arch.renameTo(respaldo);
            if (renombrado) {
               System.out.println("> Archivo anterior renombrado a: " + respaldo.getName());
            } else {
               System.out.println("> No se pudo renombrar el archivo anterior");
            }
         }

         ObjectOutputStream oi = new ObjectOutputStream(new FileOutputStream(arch));
         oi.writeObject(data.readObject());
         oi.close();
         System.out.println("> Data saved");
      } catch (IOException | ClassNotFoundException e) {
         e.printStackTrace();
      }
   }
}