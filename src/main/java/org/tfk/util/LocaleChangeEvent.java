package org.tfk.util;

import java.util.EventObject;

/**
 * 
 * @author Levy Moreira http://www.levymoreira.com
 *
 */
@SuppressWarnings("serial")
public class LocaleChangeEvent extends EventObject {

    public LocaleChangeEvent(Object source) {       
        super(source);
    }

}
