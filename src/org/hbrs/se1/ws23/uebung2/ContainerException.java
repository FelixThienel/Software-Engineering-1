package org.hbrs.se1.ws23.uebung2;

public class ContainerException extends RuntimeException {
    public ContainerException() {
        super("Es ist ein Fehler mit dem Container aufgetreten.");
    }

    public ContainerException(String fehler) {
        super(fehler);
    }
}
