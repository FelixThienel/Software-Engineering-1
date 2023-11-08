package org.hbrs.se1.ws23.uebung3.Container;

import java.io.Serializable;

public class ConcreteMember implements Member, Serializable {
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
