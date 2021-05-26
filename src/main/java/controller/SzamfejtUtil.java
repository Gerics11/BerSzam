package controller;

public class SzamfejtUtil {

    public static int aranyositBrutto(int alapber, int munkanapok, int ledolgnapok) {
        return alapber * ledolgnapok / munkanapok;
    }

    public static int szakkepz(int brutto) {
        return (int) Math.round(brutto * 0.015);
    }

    public static int szocho(int brutto) {
        return (int) Math.round(brutto * 0.155);
    }

    public static int tb(int brutto) {
        return (int) Math.round(brutto * 0.085);
    }

    public static int szja(int brutto) {
        return (int) Math.round(brutto * 0.15);
    }

    public static int nyj(int brutto) {
        return (int) Math.round(brutto * 0.10);
    }

    public static int nettober(int brutto) {
        return (int) Math.round(brutto - brutto * 0.335);
    }
}
