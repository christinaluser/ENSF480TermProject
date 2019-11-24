package Controller;

import Domain.Address;
import PresentationLayer.*;

import java.net.Socket;
import java.io.*;
import java.lang.StringBuilder;

public class Client {

    private Socket socket;
    private BufferedReader socketIn;
    private PrintWriter socketOut;
    private GUI currentGUI = null;

    public Client(String serverName, int portNumber) {
        try {
        socket = new Socket(serverName, portNumber);
        socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        socketOut = new PrintWriter((socket.getOutputStream()), true);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void setGUI(GUI g) {
        if(currentGUI != null) {
            currentGUI.close();
        }
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

    public String search(String searchCriteria) throws IOException {
        socketOut.println(searchCriteria);
        //server should make a new criteria and actually search client just sends strings to server
        socketOut.flush();
        return socketIn.readLine();
    }

    public String search(Address address) throws IOException {
        socketOut.println("SEARCHADDRESS/" + address);
        socketOut.flush();
        return socketIn.readLine();
    }

    public String display() throws IOException {
        socketOut.println("DISPLAY");
        socketOut.flush();
        String response = socketIn.readLine();
        StringBuilder data = new StringBuilder();
        while (!response.equals("END")) {
            data.append(response);
            data.append(";");
            response = socketIn.readLine();
        }
        return data.toString();
    }

    public String editFee(String message) throws IOException {
        socketOut.println(message);
        socketOut.flush();
        return socketIn.readLine();
    }

    public String getReport(String message) throws IOException {
        socketOut.println(message);
        socketOut.flush();
        return socketIn.readLine();
    }

    public String registerProperty(String propertyInfo) throws IOException {
        socketOut.println(propertyInfo);
        socketOut.flush();
        return socketIn.readLine();
    }

}