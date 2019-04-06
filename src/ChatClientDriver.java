import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ChatClientDriver {

    /**
     *  main function of client
     * @param args
     * @throws RemoteException
     * @throws NotBoundException
     * @throws MalformedURLException
     */
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {

        String url = "rmi://172.31.20.1/chatserver";
        ChatServerIF chatServerIF = (ChatServerIF) Naming.lookup(url);
        new Thread(new ChatClient(args[0],chatServerIF, Integer.parseInt(args[1]))).start();

    }
}
