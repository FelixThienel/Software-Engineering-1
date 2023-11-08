package org.hbrs.se1.ws23.uebung3.Container;

import org.hbrs.se1.ws23.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws23.uebung3.persistence.PersistenceStrategy;
import org.hbrs.se1.ws23.uebung3.persistence.PersistenceStrategyMongoDB;
import org.hbrs.se1.ws23.uebung3.persistence.PersistenceStrategyStream;

import java.util.ArrayList;
import java.util.List;

public class Container {
    private List<Member> container;
    private PersistenceStrategy<Member> strategie;

    public Container() {
        container = new ArrayList<Member>();
    }

    public void addMember(Member member) throws ContainerException {
        for(int x = 0; x < container.size(); x++) {
            if(member.getID() == container.get(x).getID())
                throw new ContainerException("Das Member-Objekt mit der ID " + member.getID() + " ist bereits vorhanden!");
        }
        container.add(member);
    }

    public String deleteMember(int member) {
        for(int x = 0; x < container.size(); x++) {
            if(member == container.get(x).getID()) {
                container.remove(x);
                return "Erfolgreich gelöscht.";
            }
        }
        return "Konnte Member mit der ID: " + member + " nicht finden.";
    }

    public List<Member> getCurrentList() {
        return container;
    }

    public int size() {
        return container.size();
    }

    public void setPersistenceStrategy(int x) {
        switch(x) {
            case 0:
                strategie = new PersistenceStrategyMongoDB<Member>();
                break;
            case 1:
                strategie = new PersistenceStrategyStream<Member>();
                break;
        }
    }

    public void setLocation(String location) throws PersistenceException {
        if(strategie instanceof PersistenceStrategyStream<Member> stream)
            stream.setLocation(location);
    }

    public void store() throws PersistenceException {
        try {
            strategie.openConnection();
            strategie.save(container);
            strategie.closeConnection();
        } catch(NullPointerException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "Es wurde noch keine Persistence Strategie ausgewählt");
        }
    }

    public void load() throws PersistenceException {
        try {
            strategie.openConnection();
            container = strategie.load();
            strategie.closeConnection();
        } catch(NullPointerException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "Es wurde noch keine Persistence Strategie ausgewählt");
        }
    }
}