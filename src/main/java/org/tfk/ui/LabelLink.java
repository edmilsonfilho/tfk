package org.tfk.ui;

import javax.swing.JLabel;
import java.net.URI;
import java.awt.Desktop;
import java.net.URISyntaxException;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.awt.Color;

//import java.awt.event.MouseAdapter;

/**
 * LabelLink é uma label clicável que lhes direciona
 * usando o browser padrão do sistema para o endereço
 * especificado como string. 
 *
 *
 * Criado: Sabado Abril 27 10:09:16 2013
 *
 * @author thiagorochafortal@gmail.com
 */

public class LabelLink extends JLabel implements MouseListener {

    private URI uri;
    private final String HTTP = "(https?|ftp|file|git)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

    public LabelLink() {
	super();
	initAttributes();
    }

    public LabelLink(String label) {
	super(label);
	initAttributes();
    }

    //@Override
    //public void setText(String text) {
    //	this.uri = new URI(text);
    //}

    private void initAttributes() {
	this.addMouseListener(this);
	this.setForeground(Color.BLUE);
    }

    public void openLink() throws URISyntaxException {
	Pattern p = Pattern.compile(HTTP);
	Matcher m = p.matcher(this.getText());

	if (m.find()) {
	    this.uri = new URI(m.group());
	} else {
	    this.uri = new URI(this.getText());
	}

	if (Desktop.isDesktopSupported()) {
	    try {
		Desktop.getDesktop().browse(this.uri);
	    } catch(Exception e) {
		e.printStackTrace();
	    }
	}
    }

    public void mouseClicked(MouseEvent e) {
	try {
	    this.openLink();
	} catch (URISyntaxException err) {
	    err.printStackTrace();
	}
    }

    public void mouseEntered(MouseEvent e) {
	Cursor c = new Cursor(Cursor.HAND_CURSOR);
	this.setCursor(c);
	this.setForeground(Color.RED);
    }

    public void mouseExited(MouseEvent e) {
	Cursor c = Cursor.getDefaultCursor();
	this.setCursor(c);
	this.setForeground(Color.BLUE);
    }

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

}
