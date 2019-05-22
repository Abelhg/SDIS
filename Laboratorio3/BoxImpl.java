package server;

import java.rmi.RemoteException;

public class BoxImpl extends java.rmi.server.UnicastRemoteObject
                     implements server.box.Box {
   
   private server.box.Accumulator acumulador; 

   public BoxImpl() throws RemoteException {
      super();
      acumulador = null;
   }

   public void put(server.box.Accumulator a) throws RemoteException {
      System.out.println("[*] Asignando nuevo acumulador. Valor: "  + a.value());
      acumulador = a;
   }

   public server.box.Accumulator take() throws RemoteException {
      System.out.println("[*] Retirando acumulador.");
      server.box.Accumulator res = acumulador;
      acumulador = null;
      return res;
   }

}
