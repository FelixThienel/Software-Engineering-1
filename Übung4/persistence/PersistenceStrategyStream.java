package org.hbrs.se1.ws23.uebung4.persistence;

import java.io.*;
import java.util.ArrayList;

public class PersistenceStrategyStream<E> implements PersistenceStrategy<E> {
    private String LOCATION = "UserStories.ser";
    private ObjectOutputStream oos = null;
    private FileOutputStream fos = null;
    private FileInputStream fis = null;
    private ObjectInputStream ois = null;

    @Override
    public void save(ArrayList<E> list) throws IOException {
        fos = new FileOutputStream(LOCATION);
        oos = new ObjectOutputStream(fos);
        oos.writeObject(list);
        oos.close();
        fos.close();
    }

    @Override
    public ArrayList<E> load() throws IOException, ClassNotFoundException {
        fis = new FileInputStream(LOCATION);
        ois = new ObjectInputStream(fis);
        ArrayList<E> list = (ArrayList<E>) ois.readObject();
        ois.close();
        fis.close();
        return list;
    }
}
