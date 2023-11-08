package org.hbrs.se1.ws23.uebung3;

import org.hbrs.se1.ws23.uebung3.Container.Container;

public class CreateContainer {
    private Container container;

    public Container getContainer() {
        if(container == null)
            container = new Container();
        return container;
    }

    public void deleteContainer() {
        container = null;
    }

    public String setPersistenceStrategy(String x) {
        switch(x) {
            case "MongoDB":
                container.setPersistenceStrategy(0);
                return "Die Strategie wurde auf MongoDB gestellt";
            case "Stream":
                container.setPersistenceStrategy(1);
                return "Die Strategie wurde auf Stream gestellt";
            default:
                return "Es sind nur die Auswahlmöglichkeiten \"MongoDB\" und \"Stream\" verfügbar";
        }
    }
}