package server.box;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Box extends Remote {
    public void put(Accumulator a) throws RemoteException;
    public Accumulator take() throws RemoteException;
}
