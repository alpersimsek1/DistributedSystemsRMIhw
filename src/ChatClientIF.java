import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * interface of chat client
 */
public interface ChatClientIF extends Remote {

    void retrieveMessage(String str) throws RemoteException;

}
