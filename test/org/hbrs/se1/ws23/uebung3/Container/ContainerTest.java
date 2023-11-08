package org.hbrs.se1.ws23.uebung3.Container;

import org.hbrs.se1.ws23.uebung3.CreateContainer;
import org.hbrs.se1.ws23.uebung3.MemberView;
import org.hbrs.se1.ws23.uebung3.persistence.PersistenceException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class ContainerTest {
    private CreateContainer creator;
    private Container container;

    @BeforeEach
    void initialisierung() throws ContainerException {
        creator = new CreateContainer();
        container = creator.getContainer();
        container.addMember(new ConcreteMember(0));
        container.addMember(new ConcreteMember(1));
        container.addMember(new ConcreteMember(7));
        container.addMember(new ConcreteMember(9));
        File file = new File("objects.ser");
    }

    @AfterEach
    void loeschen() {
        creator = null;
        container = null;
    }

    @Test
    void keineStrategie() {
        try {
            container.store();
        } catch(Exception e) {
            assertEquals("Es wurde noch keine Persistence Strategie ausgewählt", e.getMessage());
        }
    }

    @Test
    void mongoDB() {
        creator.setPersistenceStrategy("MongoDB");
        try {
            container.store();
        } catch(Exception e) {
            assertEquals("Not implemented!", e.getMessage());
        }
    }

    @Test
    void location() {
        creator.setPersistenceStrategy("Stream");
        try {
            container.setLocation("test");
            container.store();
        } catch (PersistenceException e) {
            assertEquals("java.io.FileNotFoundException: test (Zugriff verweigert)", e.getMessage());
        }
    }

    @Test
    void rtt() throws PersistenceException {
        assertEquals(4, container.size()); //4 Member im Container
        container.setLocation("objects.ser");
        creator.setPersistenceStrategy("Stream");
        container.store();
        assertEquals(4, container.size()); //4 Member im Container zwar in der Datei gespeichert aber noch nicht gelöscht
        container.deleteMember(0);
        assertEquals(3, container.size());
        container.deleteMember(1);
        assertEquals(2, container.size());
        container.deleteMember(7);
        assertEquals(1, container.size());
        container.deleteMember(9);
        assertEquals(0, container.size());
        container.load();
        assertEquals(4, container.size()); //4 Member im Container aus der Datei einlesen
    }
}