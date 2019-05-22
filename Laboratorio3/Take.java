package client;

public class Take {
   public static void main (String [] args) {
     try {
       java.rmi.registry.Registry registro
          = java.rmi.registry.LocateRegistry.getRegistry("localhost", 1099);
       server.box.Box oRemoto 
          = (server.box.Box) registro.lookup("RemoteBox");
       
       server.box.Accumulator nAcum = oRemoto.take();
       
       if(nAcum == null){
          System.out.println("[!] Caja sin contenido.");
       }else{
          System.out.println("[*] Valor: " + nAcum.value());
       }
     } catch (Exception e) {
       System.err.println("Error en cliente: " + e);
     }
   }
}
