package tlog16java;

/*
 * @author precognox kkovacs
 */

public class TLOG16Java {
    public static void main(String[] args) {
     tlog16java.TimeLoggerUI tlui = new tlog16java.TimeLoggerUI();
     tlog16java.TimeLogger tl = new tlog16java.TimeLogger();
     tlui.menu(tl);
    }
}