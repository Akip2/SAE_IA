package ia.framework.common;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Quelques trucs pour ce faciliter la vie
 */

public class Misc {


        // Regular text colors
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String MAGENTA = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    // Bright text colors
    public static final String BRIGHT_BLACK = "\u001B[30;1m";
    public static final String BRIGHT_RED = "\u001B[31;1m";
    public static final String BRIGHT_GREEN = "\u001B[32;1m";
    public static final String BRIGHT_YELLOW = "\u001B[33;1m";
    public static final String BRIGHT_BLUE = "\u001B[34;1m";
    public static final String BRIGHT_MAGENTA = "\u001B[35;1m";
    public static final String BRIGHT_CYAN = "\u001B[36;1m";
    public static final String BRIGHT_WHITE = "\u001B[37;1m";

    // Background colors
    public static final String BACKGROUND_BLACK = "\u001B[40m";
    public static final String BACKGROUND_RED = "\u001B[41m";
    public static final String BACKGROUND_GREEN = "\u001B[42m";
    public static final String BACKGROUND_YELLOW = "\u001B[43m";
    public static final String BACKGROUND_BLUE = "\u001B[44m";
    public static final String BACKGROUND_MAGENTA = "\u001B[45m";
    public static final String BACKGROUND_CYAN = "\u001B[46m";
    public static final String BACKGROUND_WHITE = "\u001B[47m";

    // Bright background colors
    public static final String BRIGHT_BACKGROUND_BLACK = "\u001B[40;1m";
    public static final String BRIGHT_BACKGROUND_RED = "\u001B[41;1m";
    public static final String BRIGHT_BACKGROUND_GREEN = "\u001B[42;1m";
    public static final String BRIGHT_BACKGROUND_YELLOW = "\u001B[43;1m";
    public static final String BRIGHT_BACKGROUND_BLUE = "\u001B[44;1m";
    public static final String BRIGHT_BACKGROUND_MAGENTA = "\u001B[45;1m";
    public static final String BRIGHT_BACKGROUND_CYAN = "\u001B[46;1m";
    public static final String BRIGHT_BACKGROUND_WHITE = "\u001B[47;1m";

    
    /**
     * Affiche une Collection de quelconque
     * @param c Une collection
     * @param sep Un séparateur
     */
    
    public static void printCollection(Collection<?> c, char sep){
        System.out.println(collection2string(c, sep));
    }

    /** 
     * Convertir une collection en String avec un séparateur
     * @param c Une collection
     * @param sep Un séparateur
     */
    
    public static String collection2string(Collection<?> c, char sep){
         return new String(c.stream()
                       .map(Object::toString)
                       .collect(Collectors.joining(" "+sep+" ")));
    }

    /** 
     * Convertir une collection en String avec un séparateur
     * @param c Une collection
     * @param sep Un séparateur
     */
    
    public static String collection2string(Collection<?> c, String sep){
         return new String(c.stream()
                       .map(Object::toString)
                       .collect(Collectors.joining(sep)));
    }

    
    /**
     * Duplique la chaine s n fois
     * <p> from : https://stackoverflow.com/questions/1235179/simple-way-to-repeat-a-string </p>
     *
     * @param s la chaine a dupliqué
     * @param n le nombre de répétition 
     * @return s dupliqué n fois 
     */
    public static String dupString(String s, int n){
        return new String(new char[n]).replace("\0", s);
        
    }

    
}
