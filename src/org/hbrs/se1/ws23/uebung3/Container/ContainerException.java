package org.hbrs.se1.ws23.uebung3.Container;

public class ContainerException extends Exception {
    public ContainerException() {
        super("Es ist ein Fehler mit dem Container aufgetreten.");
    }

    public ContainerException(String fehler) {
        super(fehler);
    }
}
