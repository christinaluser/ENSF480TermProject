package BackendLayer;

import Domain.RentalPropertyManagementSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
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
        boolean userFound = false;
        String input = "";
        try {
            while(true) {
                RentalPropertyManagementSystem rmp= new RentalPropertyManagementSystem(serverSocket.accept());
                rmp.setDbController(database);
                pool.execute(rmp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server(5050);
        server.communicate();
    }



}
