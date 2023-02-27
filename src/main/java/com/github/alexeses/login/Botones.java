package com.github.alexeses.login;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Botones extends JPanel {

    private JButton btnLogin;
    private JButton btnCancelar;
    private BotonesListener listener;

    public Botones() {
        btnLogin = new JButton("Login");
        btnCancelar = new JButton("Exit");

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                listener.loginButtonClick();
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                listener.cancelarButtonClick();
            }
        });

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS)); // Usar BoxLayout en lugar de BorderLayout
        setBorder(new EmptyBorder(30, 100, 20, 100));

        add(Box.createHorizontalGlue()); // Agregar un espacio en blanco a la izquierda de los botones
        add(btnLogin);
        add(Box.createRigidArea(new Dimension(10, 0))); // Agregar un espacio de 10 píxeles entre los botones
        add(btnCancelar);
        add(Box.createHorizontalGlue()); // Agregar otro espacio en blanco a la derecha de los botones
    }

    public void setListener(BotonesListener listener) {
        this.listener = listener;
    }
}
