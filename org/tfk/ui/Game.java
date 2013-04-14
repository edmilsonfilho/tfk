/*
 * Copyright © 2013 Helio Frota <heliofrota at gmail.com>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package org.tfk.ui;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.util.ResourceBundle;
import java.util.Locale;

/**
 * The main frame class Game.
 *
 * @author Helio Frota http://www.heliofrota.com
 * Edited on GNU/Emacs.
 */
public class Game {

    private JFrame frame;
    private JButton buttonCredits;

    public Game() {

    }

    public void initComponents() {

        Locale currentLocale = new Locale("pt", "BR");
        ResourceBundle myResources =  ResourceBundle.getBundle("messages.properties", currentLocale);

        //Frame
        frame = new JFrame();
        frame.setTitle("TFK - The Fellowship of the Knowledge");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setVisible(true);

        //Button Credits
        buttonCredits = new JButton(myResources.getString("buttonCredits"));
        buttonCredits.setBounds(10, 10, 170, 30);
        frame.add(buttonCredits);
    }

}
