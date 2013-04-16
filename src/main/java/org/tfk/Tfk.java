/*
 * Copyright © 2013 Helio Frota <heliofrota at gmail.com>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package org.tfk;

import org.tfk.ui.*;

/**
 * The main class Tfk.
 *
 * @author Helio Frota http://www.heliofrota.com
 * Edited on GNU/Emacs.
 */
public class Tfk {

    /**
    * Método que inicializa o programa.
    * Ele simplesmente imprime uma mensagem.
    * @param String[] args
    **/
    public static void main(String[] args){

        Game game = new Game();
        game.initComponents();

    }

}
