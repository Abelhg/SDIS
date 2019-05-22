package client;

public class Put {
   public static void main (String [] args) {
     try {
       java.rmi.registry.Registry registro
          = java.rmi.registry.LocateRegistry.getRegistry("localhost", 1099);
       server.box.Box oRemoto 
          = (server.box.Box) registro.lookup("RemoteBox");
       
       server.box.Accumulator nAcum = new server.box.Accumulator(8);
       oRemoto.put(nAcum);
     } catch (Exception e) {
       System.err.println("Error en cliente: " + e);
     }
   }
}
