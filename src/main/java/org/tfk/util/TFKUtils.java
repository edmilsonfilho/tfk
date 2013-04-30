package org.tfk.util;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;
import org.tfk.ui.Game;

/**
 * 
 * @author Levy Moreira http://www.levymoreira.com
 *
 */
public class TFKUtils {
    
    private static Locale currentLocale;
    private static ResourceBundle myResources;
    
    public static final Vector<LocaleChangeListener> localeChangeListeners = new Vector<LocaleChangeListener>();
    
    public static void changeLanguage(String language, String country){ 
        currentLocale = new Locale(language, country);   
        myResources = ResourceBundle.getBundle("org.tfk.i18n.messages", currentLocale);       
        notifyLocaleChangeListeners();
    }

    public static void addLocaleChangeListener(LocaleChangeListener listener) {
        localeChangeListeners.add(listener);
    }
    
    public static String getResString(String value){        
        return myResources.getString(value);        
    }
    
    private static void notifyLocaleChangeListeners() {
        @SuppressWarnings("unchecked")
        Vector<LocaleChangeListener> listeners = (Vector<LocaleChangeListener>) localeChangeListeners.clone();
        for (LocaleChangeListener listener : listeners) {
            listener.localeChanged(new LocaleChangeEvent(Game.class)); //myFrame
        }
    }

}
