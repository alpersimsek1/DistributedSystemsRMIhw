import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ChatServer extends UnicastRemoteObject implements ChatServerIF {

    private ArrayList<ChatClientIF> chatClients;

    /**
     * init server
     * @throws RemoteException
     */
    protected ChatServer() throws RemoteException {
        chatClients = new ArrayList<ChatClientIF>();

    }

    /**
     * register client
     * @param chatClientIF
     * @throws RemoteException
     */
    public synchronized void registerChatClient(ChatClientIF chatClientIF) throws RemoteException {
        this.chatClients.add(chatClientIF);
    }

    /**
     * broadcast message
     * @param message
     * @throws RemoteException
     */
    public synchronized void broadcastMessage(String message) throws RemoteException {
        for (ChatClientIF client : chatClients) {
            client.retrieveMessage(message);
        }
    }
}
