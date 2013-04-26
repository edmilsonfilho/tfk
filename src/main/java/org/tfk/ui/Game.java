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
        menuOptions = new JMenu(myResources.getString("menuOptions"));
        menuBar.add(menuOptions);
        menuItemExit = new JMenuItem(myResources.getString("menuItemExit"));
        menuOptions.add(menuItemExit);
        menuItemExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent){
                System.exit(0);
            }
        });
        
        menuLanguage = new JMenu(myResources.getString("menuLanguage")); 
        menuBar.add(menuLanguage);
        menuAlterLanguage = new JMenu(myResources.getString("menuAlterLanguage"));
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
        buttonStart = new JButton(myResources.getString("buttonStart"));
	buttonStart.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent actionEvent) {
		    new FullScreen().initComponents();
		}
	});
        gridBagLayout.setConstraints(buttonStart, gridBagConstraints);
        panel.add(buttonStart);

        buttonOptions = new JButton(myResources.getString("buttonOptions"));
        gridBagLayout.setConstraints(buttonOptions, gridBagConstraints);
        panel.add(buttonOptions);

        buttonCredits = new JButton(myResources.getString("buttonCredits"));
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
        buttonStart.setText(myResources.getString("buttonStart"));
        buttonOptions.setText(myResources.getString("buttonOptions"));
        buttonCredits.setText(myResources.getString("buttonCredits"));
        menuLanguage.setText(myResources.getString("menuLanguage"));
        menuAlterLanguage.setText(myResources.getString("menuAlterLanguage"));
        menuOptions.setText(myResources.getString("menuOptions"));
        menuItemExit.setText(myResources.getString("menuItemExit"));
    }

    private class DialogCredits extends JDialog{
        public DialogCredits(){
            this.setSize(450, 140);
            this.setTitle(myResources.getString("buttonCredits"));
            this.setLocationRelativeTo(null);
        }

        public void initComponents() {
            JPanel panelCredits = new JPanel();
            panelCredits.add(new JLabel("Helio Frota https://github.com/heliofrota"));
            panelCredits.add(new JLabel("Levy Moreira https://github.com/levymoreira"));
            panelCredits.add(new JLabel("Leandro Nascimento https://github.com/LeandroNascimento"));
            panelCredits.add(new JLabel("Manoel Calixto https://github.com/manoelcalixto")); 
            panelCredits.add(new JLabel("Maciel Melo https://github.com/MacielMelo"));

            this.add(panelCredits);
            this.setVisible(true);
        }
    }

    private class FullScreen extends JFrame {

	public void initComponents() {
	    GraphicsEnvironment ge =  
		GraphicsEnvironment.getLocalGraphicsEnvironment();
	    GraphicsDevice gd = ge.getDefaultScreenDevice();
	    gd.setFullScreenWindow(this);
	    this.setResizable(false);
	    this.setVisible(true);
	}
    }
}
