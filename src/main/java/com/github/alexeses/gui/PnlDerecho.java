package com.github.alexeses.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PnlDerecho extends JPanel {

    private JLabel lblUser;
    private JPanel pnl;

    public PnlDerecho(String usuario, String usuarios) {

        pnl = new JPanel();
        pnl.setSize(200, 240);

        lblUser = new JLabel("User: "+usuario);
        lblUser.setBounds(900, 900, 100, 100);

        this.add(lblUser);
        GridLayout gridLayout = new GridLayout(2, 1);
        gridLayout.setHgap(5);
        gridLayout.setVgap(5);
        this.setLayout(gridLayout);

        this.setBorder(new EmptyBorder(10, 10, 10, 10));
    }
}
