package org.hbrs.se1.ws23.uebung2;

import java.util.ArrayList;

public class Container {
    ArrayList<Member> container = new ArrayList<Member>();

    public Container() {
    }

    public void add(Member member) {
        for(int x = 0; x < container.size(); x++) {
            if(member.getID() == container.get(x).getID())
                throw ContainerException;
        }
        container.add(member);
    }
}