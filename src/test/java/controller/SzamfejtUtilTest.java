package controller;

import org.junit.jupiter.api.Test;

import static controller.SzamfejtUtil.*;
import static org.junit.jupiter.api.Assertions.*;

public class SzamfejtUtilTest {

    @Test
    void szakkepzTest() {
        assertEquals(0, szakkepz(0));
        assertEquals(2475, szakkepz(165000));
    }

    @Test
    void szochoTest() {
        assertEquals(0, szocho(0));
        assertEquals(25575, szocho(165000));
    }

    @Test
    void tbTest() {
        assertEquals(0, tb(0));
        assertEquals(14025, tb(165000));
    }

    @Test
    void szjaTest() {
        assertEquals(0, szja(0));
        assertEquals(24750, szja(165000));
    }

    @Test
    void nyjTest() {
        assertEquals(0, nyj(0));
        assertEquals(16500, nyj(165000));
    }

    @Test
    void nettoberTest() {
        assertEquals(0, nettober(0));
        assertEquals(109725, nettober(165000));
    }

    @Test
    void aranyositBruttoTest() {
        assertEquals(0, aranyositBrutto(100000, 20, 0));
        assertEquals(50000, aranyositBrutto(100000, 20, 10));
        assertEquals(100000, aranyositBrutto(100000, 20, 20));
    }
}