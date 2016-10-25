package chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JDesktopPane;

public class ServerUI extends javax.swing.JFrame {

    SocketOutput1 output = null;
    Map<Integer, ChatFrame> chatFrames = new HashMap<>();
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;

    public ServerUI() {
        initComponents();

        setSize(800, 600);
        setTitle("Server UI");
        setLocationRelativeTo(jLabel1);
    }

    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jDesktopPane1 = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        getContentPane().add(jLabel1, java.awt.BorderLayout.NORTH);
        getContentPane().add(jDesktopPane1, java.awt.BorderLayout.CENTER);

        pack();
    }

    private void initServer() throws IOException {
        ServerSocket server = new ServerSocket(2000);
        setVisible(true);
        jLabel1.setText(jLabel1.getText().concat("<html><font color='red'>Waiting for clients...</font>"));
        Socket socket = null;

        int i = 0;
        while ((socket = server.accept()) != null) {
            jLabel1.setText(jLabel1.getText().concat("<br/><font color='green'>Client connected ... " + (++i))+"</font>");
            SocketInput input = new SocketInput(i, socket, this);
            input.start();

            if (output == null) {
                output = new SocketOutput1(this);
            }

            output.clientSockets.put(i, socket);

            ChatFrame chatFrame = new ChatFrame(i, this);
            jDesktopPane1.add(chatFrame, JDesktopPane.DEFAULT_LAYER);

            chatFrames.put(i, chatFrame);
        }
    }

    public void updateMessage(int id, String message) {
        if (id == 0) {
            jLabel1.setText(jLabel1.getText().concat("<br/>" + message));
        } else {
            if (message.contains("Client") && message.contains("disconnected")) {
                jLabel1.setText(jLabel1.getText().concat("<br/>" + message));
                chatFrames.get(id).disableChat();
            } else
                chatFrames.get(id).updateMessage(message);
        }

    }

    public static void main(String args[]) throws IOException {

        ServerUI serverUI = new ServerUI();
        serverUI.setVisible(true);
        serverUI.initServer();
    }
    
}
