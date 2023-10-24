package org.hbrs.se1.ws23.uebung2.test;

import org.hbrs.se1.ws23.uebung2.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class ContainerTest {
    private Container container;
    private ConcreteMember member1, member2;

    @BeforeEach
    public void setUP() {
        container = new Container();
    }

    @Test
    public void neueMemberHinzufuegen() {
        member1 = new ConcreteMember(0);
        member2 = new ConcreteMember(1);
        try {
            container.addMember(member1);
            container.addMember(member2);
        } catch(Exception e) {
        }
        assertEquals(1, container.size());
    }
}