package chat;

import java.net.*;
import java.io.*;

class SocketOutput {

    Socket socket;
    ClientUI client;

    SocketOutput(Socket socket, ClientUI client) {
        this.socket = socket;
        this.client = client;
    }

    public void sendMessage(String message) {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            out.println(message);
            out.flush();

            if (message.equals("EXIT!")) {
                client.updateMessage(message);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
