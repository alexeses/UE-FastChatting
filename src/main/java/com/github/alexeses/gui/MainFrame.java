package com.github.alexeses.gui;

import javax.swing.*;
import java.awt.*;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MainFrame extends JFrame {

    private DialogNewUser inicio;
    private Socket socket;
    private Messages messages;
    private ObjectOutputStream out;
    private PnlEnviar enviar;

    public MainFrame() {
        super("Chat");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setSize(500, 500);
        super.setLocationRelativeTo(null);

        inicio = new DialogNewUser(this);
        inicio.setVisible(true);
        enviar = new PnlEnviar();

        super.setResizable(Boolean.FALSE);

        while (true) {
            try {
                socket = new Socket("127.0.0.1", 6125);
                System.out.println("ACEPTADO");
                messages = new Messages(socket);
                messages.start();

                out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(inicio.getNombre());                
                this.add(messages.getjPanel(), BorderLayout.NORTH);
                break;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        enviar = new PnlEnviar();
        enviar.setListener(new ActionListenerSendMsg() {
            @Override
            public void enviarButtonClick() {
                try {
                    out = new ObjectOutputStream(socket.getOutputStream());
                    out.writeObject(enviar.getMensaje());
                    enviar.clean();

                } catch (Exception e) {
                }
            }
        });

        super.add(enviar, BorderLayout.SOUTH);
        super.setVisible(true);
    }

    public static void main(String[] args) {
        new MainFrame();
    }

}
