package org.hbrs.se1.ws23.uebung2.test;

import org.hbrs.se1.ws23.uebung2.*;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ContainerTest {
    private Container container;
    private ConcreteMember member1, member2;
    private final PrintStream originalAusgabe = System.out;
    private final ByteArrayOutputStream ausgabe = new ByteArrayOutputStream();

    @BeforeEach
    public void starten() {
        container = new Container();
        member1 = new ConcreteMember(0);
        member2 = new ConcreteMember(1);
        System.setOut(new PrintStream(ausgabe));
    }

    @AfterEach
    public void beenden() {
        container = null;
        member1 = null;
        member2 = null;
        System.setOut(originalAusgabe);
    }

    @Test
    public void neueMemberHinzufuegen() {
        try {
            container.addMember(member1);
            container.addMember(member2);
        } catch(Exception e) {
        }
        //Es wurden beide Member in den Container aufgenommen
        assertEquals(2, container.size());
    }

    @Test
    public void vorhandenenMemberHinzufuegen() {
        member2 = new ConcreteMember(0);
        try {
            container.addMember(member1);
            container.addMember(member2);
        } catch(Exception e) {
            //Exception wurde richtig geworfen
            assertEquals("Das Member-Objekt mit der ID 0 ist bereits vorhanden!", e.getMessage());
        } finally {
            //Es wurde nur ein Member in den Container aufgenommen
            assertEquals(1, container.size());
        }
    }

    @Test
    public void memberLoeschen() {
        try {
            container.addMember(member1);
            container.addMember(member2);
        } catch(Exception e) {
        }
        //Es wurde ein Member gelöscht
        assertEquals("Erfolgreich gelöscht.", container.deleteMember(0));
        //Der Member ist auch nicht mehr in der Liste
        assertEquals(1, container.size());
        container.dump();
        assertEquals("Member (ID = 1)\r\n", ausgabe.toString());
    }

    @Test
    public void nichtVorhandenerMemberLoeschen() {
        try {
            container.addMember(member1);
            container.addMember(member2);
        } catch(Exception e) {
        }
        assertEquals("Konnte Member mit der ID: 2 nicht finden.", container.deleteMember(2));
        assertEquals(2, container.size());
    }

    @Test
    public void ausgabeMember() {
        try {
            container.addMember(member1);
            container.addMember(member2);
        } catch(Exception e) {
        }
        container.dump();
        //Richtige Ausgabe
        assertEquals("Member (ID = 0)\r\nMember (ID = 1)\r\n", ausgabe.toString());
    }

    @Test
    public void ausgabeOhneMember() {
        container.dump();
        //Keine Ausgabe, da es keine Member in dem Container gibt.
        assertEquals("", ausgabe.toString());
    }
}