package chat;

import java.net.*;
import java.io.*;
import javax.swing.JFrame;

class SocketInput extends Thread {

    Socket socket;
    int id;
    JFrame frame;

    SocketInput(int id, Socket socket, JFrame frame) {
        this.socket = socket;
        this.id = id;
        this.frame = frame;
    }

    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line = null;
            while ((line = br.readLine()) != null) {
                if (line.equals("EXIT!")) {
                    if(frame instanceof ServerUI) 
                        ((ServerUI) frame).updateMessage(id, "Client "+id+" disconnected");
                    else
                        ((ClientUI) frame).updateMessage("Server closed connection");
                } else if (id > 0) {
                    ((ServerUI) frame).updateMessage(id, line);
                } else {
                    ((ClientUI) frame).updateMessage(line);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
