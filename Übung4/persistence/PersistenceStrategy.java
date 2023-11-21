package org.hbrs.se1.ws23.uebung4.persistence;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public interface PersistenceStrategy<E> {
    public void save(ArrayList<E> member) throws IOException;
    public ArrayList<E> load() throws IOException, ClassNotFoundException;
}