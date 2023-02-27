package com.github.alexeses.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogNewUser extends JDialog {

    private JLabel lblNombre;
    private JTextField txtNombre;
    private JPanel panel;
    private JButton btnAceptar;
    public DialogNewUser(JFrame parent) {

        super(parent, true);
        super.setTitle("Login");
        super.setSize(416, 473);
        panel = new JPanel();
        panel.setSize(416, 473);

        lblNombre = new JLabel("Ingresa tu nombre");
        lblNombre.setBounds(20, 10, 200, 100);

        txtNombre = new JTextField(15);
        txtNombre.setBounds(20, 80, 100, 30);

        btnAceptar = new JButton("Aceptar");
        btnAceptar.setBounds(190, 110, 100, 20);

        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {                
                DialogNewUser.this.setVisible(false);
            }   
        });
        
        panel.setBorder(new EmptyBorder(30,100,20,100));

        panel.setLayout(null);
        panel.add(lblNombre);
        panel.add(txtNombre);
        panel.add(btnAceptar);

        this.add(panel);
    }

    public String getNombre() {
        return txtNombre.getText();
    }

    public static void main(String[] args) {
        new DialogNewUser(null);
    }

}
