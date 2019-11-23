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
        boolean userFound = false;
        String input = "";
        try {
            while(!userFound) {
                input = socketIn.readLine();
                System.out.println(input);
                if(input.startsWith("USER/")) {
                    userFound = true;
                }
            }

            switch(input) {
                case "USER/REGULAR_USER":
                    communicateRegularUser();
                    break;
                case "USER/LANDLORD":
                    communicateLandlord();
                    break;
                case "USER/RENTER":
                    communicateRenter();
                    break;
                case "USER/MANAGER":
                    communicateManager();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + input);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void communicateRegularUser() {

    }

    private void communicateLandlord() {

    }

    private void communicateRenter() {

    }

    private void communicateManager() {

    }

    public static void main(String[] args) {
        Server server = new Server(5000);
        server.communicate();
    }



}
