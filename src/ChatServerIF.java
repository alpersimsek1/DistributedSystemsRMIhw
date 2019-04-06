
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * interface of server
 */
public interface ChatServerIF extends Remote {
    void registerChatClient(ChatClientIF fib) throws RemoteException;
    void broadcastMessage(String message) throws RemoteException;
}
