/*
 * Copyright © 2013 Helio Frota <heliofrota at gmail.com>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package org.tfk.sound;

import javax.sound.sampled.*;
import javax.sound.midi.*;
import javax.sound.sampled.spi.*;
import javax.sound.midi.spi.*;
import java.io.*;
import java.net.*;
/**
 * The Midi class to play midi files.
 *
 * @author Helio Frota http://www.heliofrota.com
 * Edited on GNU/Emacs.
 */
public class Midi {

    public void play() throws Exception {

        Sequencer sequencer = MidiSystem.getSequencer();
        if (sequencer == null) {
            System.out.println("error");
        } else {
           sequencer.open();
        }

        URL url = Midi.class.getResource("x.mid");
        File file = new File(url.getPath());
        Sequence sequence = MidiSystem.getSequence(file);
        sequencer.setSequence(sequence);
        sequencer.start();
    }

}
