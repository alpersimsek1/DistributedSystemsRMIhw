import java.lang.reflect.Array;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ChatClient extends UnicastRemoteObject implements ChatClientIF, Runnable {
    private ChatServerIF chatServerIF;
    private String name = null;
    private Integer id;
    private ArrayList<Integer> timestamp;

    /**
     * init client
     * @param name
     * @param chatServer
     * @param processId
     * @throws RemoteException
     */
    protected ChatClient(String name, ChatServerIF chatServer, Integer processId) throws RemoteException {

        this.name = name;
        this.chatServerIF = chatServer;
        this.id = processId;
        timestamp = new ArrayList<>(5);
        initTimeStamp();
        addTimeStammp(id);
        chatServerIF.registerChatClient(this);
    }

    /**
     * init timestamps
     */
    private void initTimeStamp(){
        for(int i = 0; i< 5; i++) timestamp.add(0);
    }

    /**
     * add +1 to timestamp
     * @param id
     */
    private void addTimeStammp(Integer id) {
        timestamp.set(id, timestamp.get(id) + 1);
    }

    /**
     * retreive message from server
     * @param str
     * @throws RemoteException
     */
    public void retrieveMessage(String str) throws RemoteException {
        String[] vector = str.split(" ");
        String[] vec2 = vector[1].split(",");
//        for(String i: vector) System.out.println(i);
        for(int i  = 0 ; i<timestamp.size(); i++){
            timestamp.set(i, Integer.parseInt(vec2[i]));
        }
        System.out.println(str);
    }

    /**
     * for vector timestamps
     * @param list
     * @return
     */
    private String createStrOfList(ArrayList<Integer> list){
        String s = "";
        for(Integer i: list){
            s += i + ",";
        }
        return s;
    }

    /**
     * runnable function
     *
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String message;
        while (true) {
            message = scanner.nextLine();
            try {
                addTimeStammp(id);
                chatServerIF.broadcastMessage(message + " " + createStrOfList(timestamp));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
