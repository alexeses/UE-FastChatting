package com.github.alexeses.gui;

import javax.swing.*;
import java.awt.*;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class PrincipalFrame extends JFrame {

    private DialogNewUser inicio;
    private Socket socket;
    private Messages messages;
    private ObjectOutputStream out;
    private PnlEnviar enviar;
    private PnlDerecho pancelDerecho;

    public PrincipalFrame(String nombreDelUsuario) {
        super("Chat");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setSize(600, 250);
        super.setLocationRelativeTo(null);

        String usuariodelchat = nombreDelUsuario;

        super.setResizable(Boolean.FALSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        enviar = new PnlEnviar();

        int intentos = 0;
        while (intentos < 3) {
            try {
                socket = new Socket("127.0.0.1", 6125);
                messages = new Messages(socket);
                messages.start();

                out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(usuariodelchat);

                this.add(messages.getjPanel(), BorderLayout.CENTER);
                break;
            } catch (Exception e) {
                intentos++;
                if (intentos < 3) {
                    System.out.println("Error al conectarse al servidor. Intento " + intentos + " de 3");

                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "El servidor no está disponible. Intente más tarde.");
                    System.exit(0);
                    break;
                }
            }
        }

        enviar = new PnlEnviar();
        enviar.setListener(new ActionListenerSendMsg() {
            @Override
            public void enviarButtonClick() {
                try {

                    System.out.println(PrincipalFrame.this.getSize());
                    out = new ObjectOutputStream(socket.getOutputStream());
                    out.writeObject(enviar.getMensaje());
                    enviar.clean();

                } catch (Exception e) {
                    System.out.println("Error al enviar el mensaje");
                }
            }

        });

        pancelDerecho = new PnlDerecho(usuariodelchat, usuariodelchat);

        super.add(pancelDerecho, BorderLayout.EAST);

        super.add(enviar, BorderLayout.SOUTH);
        super.setVisible(true);
    }
}
