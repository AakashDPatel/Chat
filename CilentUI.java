package chat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

public class ClientUI extends javax.swing.JFrame {

    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;

    public ClientUI() {
        initComponents();

        setSize(300, 500);
        setLocationByPlatform(true);
        setTitle("Client");

    }

    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(jLabel1, java.awt.BorderLayout.CENTER);

        jPanel1.setLayout(new java.awt.BorderLayout());
        jPanel1.add(jTextField1, java.awt.BorderLayout.CENTER);

        jButton1.setText("Send");
        jPanel1.add(jButton1, java.awt.BorderLayout.EAST);

        getContentPane().add(jPanel1, java.awt.BorderLayout.SOUTH);

        pack();
    }

    private void connectToServer() throws IOException {
        Socket socket = new Socket("192.168.3.11", 2000);
        System.out.println("Connected to server");
        SocketInput input = new SocketInput(0, socket, this);
        input.start();
        jLabel1.setText(jLabel1.getText().concat("<html><font color='green'>Connected to server</font>"));
        SocketOutput output = new SocketOutput(socket, this);
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!jTextField1.getText().isEmpty()) {
                    output.sendMessage(jTextField1.getText());
                    jLabel1.setText(jLabel1.getText().concat("<br/>"+jTextField1.getText()));
                }
                jTextField1.setText("");
            }
        });
    }

    void updateMessage(String message) {
        jLabel1.setText(jLabel1.getText().concat("<br/>" + message));
        if (message.equals("Server closed connection") || message.equals("EXIT!")) {
            jTextField1.setEnabled(false);
            jButton1.setEnabled(false);
        }
    }

    public static void main(String args[]) throws IOException {

        ClientUI client = new ClientUI();
        client.setVisible(true);
        client.connectToServer();
    }
}
