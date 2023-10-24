package org.hbrs.se1.ws23.uebung2;

import java.util.ArrayList;

public class Container {
    ArrayList<Member> container;

    public Container() {
        container = new ArrayList<Member>();
    }

    public void addMember(Member member) throws ContainerException{
        for(int x = 0; x < container.size(); x++) {
            if(member.getID() == container.get(x).getID())
                throw new ContainerException("Das Member-Objekt mit der ID " + member.getID() + " ist bereits vorhanden!");
        }
        container.add(member);
    }

    public String deleteMember(Member member) {
        for(int x = 0; x < container.size(); x++) {
            if(member.getID() == container.get(x).getID()) {
                container.remove(x);
                return "Erfolgreich gelöscht.";
            }
        }
        return "Konnte Member mit der ID: " + member.getID() + " nicht finden.";
    }

    public void dump() {
        for(int x = 0; x < container.size(); x++)
            System.out.println(container.get(x).toString());
    }

    public int size() {
        return container.size();
    }
}