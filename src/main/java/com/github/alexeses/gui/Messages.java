package com.github.alexeses.gui;

import javax.swing.*;
import java.awt.*;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.Socket;

public class Messages extends Thread implements Serializable{
    private TextArea textArea;
    private Socket socket;
    private JPanel jPanel;

    public Messages(Socket socket) {
        this.socket = socket;
        textArea = new TextArea();
        textArea.setSize(190,390);
        textArea.setEditable(false);
        textArea.setText("Welcome to Fast Chat :D");
        jPanel = new JPanel();
        jPanel.add(textArea);
    }

    public JPanel getjPanel() {
        return jPanel;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Entro");
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                String x = (String) in.readObject();
                //System.out.println("Me enviaron : " + x);
                actualizar(x);
                textArea.repaint();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Test");
            }
        }
    }

    public void actualizar(String x){
        String a = textArea.getText();
        textArea.setText(a +"\n"+x );                
    }
}