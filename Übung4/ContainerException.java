package org.hbrs.se1.ws23.uebung4;

public class ContainerException extends Exception{
    public ContainerException() {
        super("Es ist ein Fehler aufgetreten.");
    }

    public ContainerException(String message) {
        super(message);
    }
}