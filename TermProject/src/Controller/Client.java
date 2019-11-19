package Controller;

import Domain.SearchCriteria;

import java.net.Socket;
import java.io.*;
import java.lang.StringBuilder;

public class Client {

    private Socket socket;
    private BufferedReader socketIn;
    private PrintWriter socketOut;

    public Client(String serverName, int portNumber) {
        try {
        socket = new Socket(serverName, portNumber);
        socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        socketOut = new PrintWriter((socket.getOutputStream()), true);
        } catch (IOException e) {
        System.err.println(e.getStackTrace());
        }
    }

    public void close() {
        try {
        socketIn.close();
        socketOut.close();
        socket.close();
        } catch (IOException e) {
        System.out.println("Closing error: " + e.getMessage());
        }
    }

    public String search(SearchCriteria criteria) throws IOException {
        socketOut.println("SEARCH");
        socketOut.println();
        return socketIn.readLine();
    }

    public String display() throws IOException {
        socketOut.println("DISPLAY");
        String response = socketIn.readLine();
        StringBuilder data = new StringBuilder();
        while (!response.equals("END")) {
            data.append(response);
            data.append(";");
            response = socketIn.readLine();
        }
        return data.toString();
    }

}