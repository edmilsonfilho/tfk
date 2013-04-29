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
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.util.ResourceBundle;
import java.util.Locale;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

/**
 * The main frame class Game.
 *
 * @author Helio Frota http://www.heliofrota.com
 * Edited on GNU/Emacs.
 */
public class Game {

    private Locale currentLocale;
    private ResourceBundle myResources;
    private JFrame frame;
    private JButton buttonCredits, buttonOptions, buttonStart;
    private JMenuBar menuBar;
    private JMenu menuLanguage;
    private JMenu menuAlterLanguage;
    private JMenuItem menuItemEnUS;
    private JMenuItem menuItemPtBR;
    private JMenu menuOptions;
    private JMenuItem menuItemExit;
    private JPanel panel;
    private GridBagLayout gridBagLayout;
    private GridBagConstraints gridBagConstraints;


    public Game() {

    }

    public void initComponents() {

        //Set Locale
        currentLocale = new Locale("pt", "BR"); //default bt_BR - Brazilian language
        //currentLocale = new Locale("en", "US"); //to set english language remove comment of this line and comment out the line above
        myResources = ResourceBundle.getBundle("org.tfk.i18n.messages", currentLocale);

        //Frame
        frame = new JFrame();
        frame.setTitle("TFK - The Fellowship of the Knowledge");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        //Layout
        gridBagLayout = new GridBagLayout();
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER; //end row
        gridBagConstraints.insets = new Insets(15,0,38,0) ; //spaces between buttons
        
        //Panel
        panel = new JPanel();
        panel.setLayout(gridBagLayout);
        
        //Menu Bar
        menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        //Menu Bar Itens
        menuOptions = new JMenu(myResources.getString("options"));
        menuBar.add(menuOptions);
        menuItemExit = new JMenuItem(myResources.getString("exit"));
        menuOptions.add(menuItemExit);
        menuItemExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent){
                System.exit(0);
            }
        });
        
        menuLanguage = new JMenu(myResources.getString("language")); 
        menuBar.add(menuLanguage);
        menuAlterLanguage = new JMenu(myResources.getString("alter"));
        menuLanguage.add(menuAlterLanguage);

        menuItemEnUS = new JMenuItem("en-US");
        menuAlterLanguage.add(menuItemEnUS);
        menuItemEnUS.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent){
                changeLanguage("en", "US");
            }
        });  

        menuItemPtBR = new JMenuItem("pt-BR");
        menuAlterLanguage.add(menuItemPtBR);
        menuItemPtBR.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent){
                changeLanguage("pt", "BR");
            }
        });         

        //Buttons
        buttonStart = new JButton(myResources.getString("start"));
        buttonStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                new FullScreen().initComponents();
            }
        });        
        gridBagLayout.setConstraints(buttonStart, gridBagConstraints);
        panel.add(buttonStart);

        buttonOptions = new JButton(myResources.getString("options"));
        gridBagLayout.setConstraints(buttonOptions, gridBagConstraints);
        panel.add(buttonOptions);

        buttonCredits = new JButton(myResources.getString("credits"));
        buttonCredits.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent){
                new DialogCredits().initComponents();
            }
        });
        gridBagLayout.setConstraints(buttonCredits, gridBagConstraints);

        panel.add(buttonCredits);        
        frame.add(panel);
        frame.setVisible(true);
    }

    private void changeLanguage(String language, String country){
        currentLocale = new Locale(language, country);
        myResources = ResourceBundle.getBundle("org.tfk.i18n.messages", currentLocale);
        buttonStart.setText(myResources.getString("start"));
        buttonOptions.setText(myResources.getString("options"));
        buttonCredits.setText(myResources.getString("credits"));
        menuLanguage.setText(myResources.getString("language"));
        menuAlterLanguage.setText(myResources.getString("language"));
        menuOptions.setText(myResources.getString("options"));
        menuItemExit.setText(myResources.getString("exit"));
    }

    private class DialogCredits extends JDialog{
        public DialogCredits(){
            this.setSize(450, 140);
            this.setTitle(myResources.getString("credits"));
            this.setLocationRelativeTo(null);
        }

        public void initComponents() {
            gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER; 

            JPanel panelCredits = new JPanel();
            panelCredits.setLayout(gridBagLayout);

            LabelLink labelHelioFrota = new LabelLink("Helio Frota https://github.com/heliofrota");
            gridBagLayout.setConstraints(labelHelioFrota, gridBagConstraints);
            panelCredits.add(labelHelioFrota);

            LabelLink labelLevyMoreira = new LabelLink("Levy Moreira https://github.com/levymoreira");
            gridBagLayout.setConstraints(labelLevyMoreira, gridBagConstraints);
            panelCredits.add(labelLevyMoreira);

            LabelLink labelLeandroNascimento = new LabelLink("Leandro Nascimento https://github.com/LeandroNascimento");
            gridBagLayout.setConstraints(labelLeandroNascimento, gridBagConstraints);
            panelCredits.add(labelLeandroNascimento);

            LabelLink labelManoelCalixto = new LabelLink("Manoel Calixto https://github.com/manoelcalixto");
            gridBagLayout.setConstraints(labelManoelCalixto, gridBagConstraints);
            panelCredits.add(labelManoelCalixto);

            LabelLink labelMacielMelo= new LabelLink("Maciel Melo https://github.com/MacielMelo");
            gridBagLayout.setConstraints(labelMacielMelo, gridBagConstraints);
            panelCredits.add(labelMacielMelo);

            JButton buttonExit = new JButton(myResources.getString("exit"));
            buttonExit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent actionEvent){
                   setVisible(false);
                }
            });
            gridBagLayout.setConstraints(buttonExit, gridBagConstraints);
            panelCredits.add(buttonExit);

            this.add(panelCredits);
            this.setVisible(true);
        }
    }

    private class FullScreen extends JFrame {
        public void initComponents() {
            GraphicsEnvironment ge =  GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice gd = ge.getDefaultScreenDevice();
            gd.setFullScreenWindow(this);
            this.setResizable(false);
            this.setVisible(true);
        }
    }
}
