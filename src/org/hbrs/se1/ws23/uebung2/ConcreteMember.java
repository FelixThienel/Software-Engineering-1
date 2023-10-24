package org.hbrs.se1.ws23.uebung2;

public class ConcreteMember implements Member{
    int id;

    public ConcreteMember(int id) {
        this.id = id;
    }

    public Integer getID() {
        return id;
    }

    @Override
    public String toString() {
        return "Member (ID = " + id +")";
    }
}
