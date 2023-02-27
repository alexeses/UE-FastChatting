package com.github.alexeses.gui;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class Messages extends Thread implements Serializable {
    private TextArea textArea;
    private Socket socket;
    private JPanel jPanel;
    private boolean socketCerrado = false;

    public Messages(Socket socket) {
        this.socket = socket;
        textArea = new TextArea();
        textArea.setSize(190, 390);
        textArea.setEditable(false);
        //textArea.setText("Welcome to Fast Chat :D");
        jPanel = new JPanel();
        jPanel.add(textArea);
    }

    public JPanel getjPanel() {
        return jPanel;
    }

    @Override
    public void run() {
        while (!socketCerrado) {
            try {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                String x = (String) in.readObject();
                if (x.trim().isEmpty()) {
                    continue;
                }

                actualizar(x);
                textArea.repaint();
            } catch (EOFException | SocketException e) {
                textArea.setForeground(Color.RED);
                textArea.setText("Se ha perdido la conexión con el servidor");
                socketCerrado = true;
                break;
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void actualizar(String x) {
        String a = textArea.getText();
        textArea.setText(a + "\n" + x);
    }

}