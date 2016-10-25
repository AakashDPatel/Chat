package chat;

public class ChatFrame extends javax.swing.JInternalFrame {

    private ServerUI server;
    int id;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;

    public ChatFrame(int id, ServerUI server) {
        initComponents();
        this.id = id;
        this.server = server;
        jLabel1.setText("<html>");
        setTitle("Client " + id);
        setSize(300, 500);
        setResizable(true);
        setMaximizable(true);
        setIconifiable(true);
        setVisible(true);
    }

    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(jLabel1, java.awt.BorderLayout.CENTER);

        jPanel1.setLayout(new java.awt.BorderLayout());
        jPanel1.add(jTextField1, java.awt.BorderLayout.CENTER);

        jButton1.setText("Send");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, java.awt.BorderLayout.EAST);

        getContentPane().add(jPanel1, java.awt.BorderLayout.SOUTH);

        pack();
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        if (!jTextField1.getText().isEmpty()) {
            server.output.sendMessage(id, jTextField1.getText());
            jLabel1.setText(jLabel1.getText().concat("<br/>" + jTextField1.getText()));
        }
        jTextField1.setText("");
    }

    void updateMessage(String message) {
        jLabel1.setText(jLabel1.getText().concat("<br/>" + message));
        if (message.equals("EXIT!")) {
            jTextField1.setEnabled(false);
            jButton1.setEnabled(false);
        }
    }

    void disableChat() {
        jTextField1.setEnabled(false);
        jButton1.setEnabled(false);
    }
}