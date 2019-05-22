package server;

public class Launcher {
    public static void main(String [] args) {
     try  {
       java.rmi.registry.Registry registro
          = java.rmi.registry.LocateRegistry.getRegistry("localhost", 1099);

       server.BoxImpl oBox = new server.BoxImpl();
       registro.rebind("RemoteBox", oBox);
       
     } catch (Exception e) {
       System.err.println("Error en el Lanzador: "+ e);
     }
   }
}