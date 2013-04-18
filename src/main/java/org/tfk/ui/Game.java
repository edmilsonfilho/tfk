/*
 * Copyright © 2013 Helio Frota <heliofrota at gmail.com>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package org.tfk.ui;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JMenuItem;
import java.util.ResourceBundle;
import java.util.Locale;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

/**
 * The main frame class Game.
 *
 * @author Helio Frota http://www.heliofrota.com
 * Edited on GNU/Emacs.
 */
public class Game {

    private JFrame frame;
    private JButton buttonCredits, buttonOptions, buttonStart;
    private JMenuBar menuBar;
    private JMenu menuOptions;
    private JMenuItem menuItemExit;
    private JPanel panel;

    public Game() {

    }

    public void initComponents() {

        //Set Locale
        Locale currentLocale = new Locale("pt", "BR"); //default bt_BR - Brazilian language
        //Locale currentLocale = new Locale("en", "US"); //to set english language remove comment of this line and comment out the line above
        ResourceBundle myResources = ResourceBundle.getBundle("org.tfk.i18n.messages", currentLocale);

        //Frame
        frame = new JFrame();
        frame.setTitle("TFK - The Fellowship of the Knowledge");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        //Layout
        GridBagLayout gridBagLayout = new GridBagLayout();       
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER; //end row
        
        //Panel
        panel = new JPanel();
        panel.setLayout(gridBagLayout);
        
        //Menu Bar
        menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        //Menu Bar Itens
        menuOptions = new JMenu(myResources.getString("menuOptions"));
        menuBar.add(menuOptions);
        JMenuItem menuItemExit = new JMenuItem(myResources.getString("menuItemExit"));
        menuOptions.add(menuItemExit);
        menuItemExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent){
                System.exit(0);
            }
        });       

        //Buttons
        buttonStart = new JButton(myResources.getString("buttonStart"));
        gridBagLayout.setConstraints(buttonStart, gridBagConstraints);
        panel.add(buttonStart);

        buttonOptions = new JButton(myResources.getString("buttonOptions"));
        gridBagLayout.setConstraints(buttonOptions, gridBagConstraints);
        panel.add(buttonOptions);

        buttonCredits = new JButton(myResources.getString("buttonCredits"));
        gridBagLayout.setConstraints(buttonCredits, gridBagConstraints);
        panel.add(buttonCredits);
        
        frame.add(panel);
        frame.setVisible(true);
    }

}
