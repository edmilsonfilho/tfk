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

import org.tfk.util.TFKUtils;
import org.tfk.util.LocaleChangeListener;
import org.tfk.util.LocaleChangeEvent;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * The main frame class Game.
 *
 * @author Helio Frota http://www.heliofrota.com
 * Edited on GNU/Emacs.
 */
public class Game {

    private GridBagLayout gridBagLayout;
    private GridBagConstraints gridBagConstraints;

    public Game() {
        //Set Locale
        TFKUtils.changeLanguage("pt", "BR");

        //Open initial screen
        new MainFrame();
    }

    private class MainFrame extends JFrame implements LocaleChangeListener{

        JButton buttonCredits, buttonOptions, buttonStart;
        JMenuBar menuBar;
        JMenu menuLanguage;
        JMenu menuAlterLanguage;
        JMenuItem menuItemEnUS;
        JMenuItem menuItemPtBR;
        JMenu menuOptions;
        JMenuItem menuItemExit;
        JPanel panel;

	
        public MainFrame(){
            TFKUtils.addLocaleChangeListener(this);
            this.initComponents();
        }

        public void initComponents() {
            //Frame
            this.setTitle("TFK - The Fellowship of the Knowledge");
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setSize(800, 600);
            this.setLocationRelativeTo(null);


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
            this.setJMenuBar(menuBar);

            //Menu Bar Itens
            menuOptions = new JMenu(TFKUtils.getResString("options"));
            menuBar.add(menuOptions);
            menuItemExit = new JMenuItem(TFKUtils.getResString("exit"));
            menuOptions.add(menuItemExit);
            menuItemExit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent actionEvent){
                    System.exit(0);
                }
            });

            menuLanguage = new JMenu(TFKUtils.getResString("language"));
            menuBar.add(menuLanguage);
            menuAlterLanguage = new JMenu(TFKUtils.getResString("alter"));
            menuLanguage.add(menuAlterLanguage);

            menuItemEnUS = new JMenuItem("en-US");
            menuAlterLanguage.add(menuItemEnUS);
            menuItemEnUS.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent actionEvent){
                    TFKUtils.changeLanguage("en", "US");
                }
            });

            menuItemPtBR = new JMenuItem("pt-BR");
            menuAlterLanguage.add(menuItemPtBR);
            menuItemPtBR.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent actionEvent){
                    TFKUtils.changeLanguage("pt", "BR");
                }
            });

            //Buttons
            buttonStart = new JButton(TFKUtils.getResString("start"));
            buttonStart.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent actionEvent) {
                    new FullScreen().initComponents();
                }
            });
            gridBagLayout.setConstraints(buttonStart, gridBagConstraints);
            panel.add(buttonStart);

            buttonOptions = new JButton(TFKUtils.getResString("options"));
            gridBagLayout.setConstraints(buttonOptions, gridBagConstraints);
            panel.add(buttonOptions);

            buttonCredits = new JButton(TFKUtils.getResString("credits"));
            buttonCredits.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent actionEvent){
                    new DialogCredits().initComponents();
                }
            });
            gridBagLayout.setConstraints(buttonCredits, gridBagConstraints);

            panel.add(buttonCredits);
            this.add(panel);
            this.add(new BackgroundPanel());

            /*JPanel p = new JPanel();
            try {
            File f = new File("warrior.png");
            BufferedImage bi = ImageIO.read(f);
            Graphics2D big = bi.createGraphics();
            //p.paint(big);

            //big.paint();
            big.drawImage(bi, 0, 0, this);

            JPanel x = new JPanel();
            
            this.getContentPane().paintComponent(big);
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }
            //this.add(p);*/


            this.setVisible(true);
        }

        @Override
        public void localeChanged(LocaleChangeEvent event) {
            this.getContentPane().removeAll(); //remove all components for init add again
            this.initComponents();
        }

	/* public void paint(Graphics g) {  
            super.paint(g);
	    try{
                f = new File("warrior.png");
                bi = ImageIO.read(f);
	    } catch(Exception e) {}
            g.drawImage(bi, 0, 0, this);  
	   }*/ 

    }

    private class BackgroundPanel extends JPanel {
        File f;
        BufferedImage bi;

        public void paint(Graphics g) {  
            super.paint(g);
	    try{
                f = new File("warrior.png");
                bi = ImageIO.read(f);
	    } catch(Exception e) {}
            g.drawImage(bi, 0, 0, this);  
        } 
    }

    private class DialogCredits extends JDialog{

        public DialogCredits(){
            this.setSize(450, 170);
            this.setTitle(TFKUtils.getResString("credits"));
            this.setLocationRelativeTo(null);
        }

        public void initComponents() {
            gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;

            JPanel panelCredits = new JPanel();
            panelCredits.setLayout(gridBagLayout);

            JLabel labelHelioFrota = new JLabel("Helio Frota https://github.com/heliofrota");
            gridBagLayout.setConstraints(labelHelioFrota, gridBagConstraints);
            panelCredits.add(labelHelioFrota);

            JLabel labelLevyMoreira = new JLabel("Levy Moreira https://github.com/levymoreira");
            gridBagLayout.setConstraints(labelLevyMoreira, gridBagConstraints);
            panelCredits.add(labelLevyMoreira);

            JLabel labelLeandroNascimento = new JLabel("Leandro Nascimento https://github.com/LeandroNascimento");
            gridBagLayout.setConstraints(labelLeandroNascimento, gridBagConstraints);
            panelCredits.add(labelLeandroNascimento);

            JLabel labelManoelCalixto = new JLabel("Manoel Calixto https://github.com/manoelcalixto");
            gridBagLayout.setConstraints(labelManoelCalixto, gridBagConstraints);
            panelCredits.add(labelManoelCalixto);

            JLabel labelMacielMelo = new JLabel("Maciel Melo https://github.com/MacielMelo");
            gridBagLayout.setConstraints(labelMacielMelo, gridBagConstraints);
            panelCredits.add(labelMacielMelo);

            JLabel labelThiagoRocha = new JLabel("Thiago Rocha https://github.com/RochaFortal");
            gridBagLayout.setConstraints(labelThiagoRocha, gridBagConstraints);
            panelCredits.add(labelThiagoRocha);

            JLabel labelWallysonBatista = new JLabel("Wallyson Batista https://github.com/wallyson2712");
            gridBagLayout.setConstraints(labelWallysonBatista, gridBagConstraints);
            panelCredits.add(labelWallysonBatista);

            JButton buttonExit = new JButton(TFKUtils.getResString("exit"));
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
