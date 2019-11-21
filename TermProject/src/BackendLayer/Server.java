package BackendLayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private BufferedReader socketIn;
    private PrintWriter socketOut;
    private Socket socket;
    private ExecutorService pool;
    private ServerSocket serverSocket;
    private DatabaseController database;

    public Server(int portNum) {
        try {
            serverSocket = new ServerSocket(portNum);
            pool = Executors.newCachedThreadPool();
            database = new DatabaseController();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Server is now running...");
    }

    public void communicate() {
//        try {
//            while (true) {
//                Shop theShop = new Shop(serverSocket.accept(), theInventory, suppliers);
//                theShop.setDbController(db);
//                pool.execute(theShop);
//            }
//        } catch(IOException e) {
//            e.printStackTrace();
//            pool.shutdown();
//            db.close();
//        }
        
    }   
    
    public static void main(String[] args) {
        Server server = new Server(5000);
        server.communicate();
    }



}
