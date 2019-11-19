package Controller;

import Domain.SearchCriteria;
import PresentationLayer.*;

import java.net.Socket;
import java.io.*;
import java.lang.StringBuilder;

public class Client {

    private Socket socket;
    private BufferedReader socketIn;
    private PrintWriter socketOut;
    private GUI currentGUI;

    public Client(String serverName, int portNumber) {
        try {
        socket = new Socket(serverName, portNumber);
        socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        socketOut = new PrintWriter((socket.getOutputStream()), true);
        } catch (IOException e) {
        System.err.println(e.getStackTrace());
        }
    }

    public void setGUI(GUI g) {
        currentGUI = g;
    }

    public void runGUI() {
        currentGUI.updateView();
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

}