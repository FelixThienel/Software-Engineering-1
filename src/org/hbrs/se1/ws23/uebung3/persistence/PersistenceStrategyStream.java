package org.hbrs.se1.ws23.uebung3.persistence;

import org.hbrs.se1.ws23.uebung3.Container.Member;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersistenceStrategyStream<E> implements PersistenceStrategy<E> {

    // URL of file, in which the objects are stored
    private String location = "objects.ser";
    private FileOutputStream fos;
    private ObjectOutputStream out;
    private FileInputStream fis;
    private ObjectInputStream in;

    // Backdoor method used only for testing purposes, if the location should be changed in a Unit-Test
    // Example: Location is a directory (Streams do not like directories, so try this out ;-)!
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    /**
     * Method for opening the connection to a stream (here: Input- and Output-Stream)
     * In case of having problems while opening the streams, leave the code in methods load
     * and save.
     */
    public void openConnection() throws PersistenceException {
        //Wenn ich an dieser Stelle sowohl Out- als auch InputObjects und Files generiere, wird beim Auslesen einer vorhandenen Datei der Inhalt mit einer neuen Datei überschrieben
    }

    @Override
    /**
     * Method for closing the connections to a stream
     */
    public void closeConnection() throws PersistenceException {
        try {
            if(out != null) {
                out.close();
                fos.close();
            } else if (in != null) {
                in.close();
                fis.close();
            }
        } catch(Exception e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, e.toString());
        }
    }

    @Override
    /**
     * Method for saving a list of Member-objects to a disk (HDD)
     */
    public void save(List<E> member) throws PersistenceException  {
        try {
            fos = new FileOutputStream(location);
            out = new ObjectOutputStream(fos);
            out.writeObject(member);
        } catch(Exception e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, e.toString());
        }
    }

    @Override
    /**
     * Method for loading a list of Member-objects from a disk (HDD)
     * Some coding examples come for free :-)
     * Take also a look at the import statements above ;-!
     */
    public List<E> load() throws PersistenceException  {
        List<E> member = new ArrayList<E>();
        try {
            fis = new FileInputStream(location);
            in = new ObjectInputStream(fis);
            member = (List<E>) in.readObject();
        } catch(Exception e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, e.toString());
        }
        return member;
    }
}
