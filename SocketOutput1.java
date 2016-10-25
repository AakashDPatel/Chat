package chat;

import java.net.*;
import java.io.*;
import java.util.*;

class SocketOutput1 {

    Map<Integer, Socket> clientSockets;
    ServerUI server;
    SocketOutput1(ServerUI server) {
        clientSockets = new HashMap<>();
        this.server = server;
    }

    public void sendMessage(int id, String line) {
        try {
            PrintWriter out = new PrintWriter(clientSockets.get(id).getOutputStream());
            out.println(line);
            out.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
