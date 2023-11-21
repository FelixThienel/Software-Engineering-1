package org.hbrs.se1.ws23.uebung4;

import java.io.Serializable;

public class UserStory implements Serializable {
    private final int id;
    private final String beschreibung, akzeptanzkriterium, projekt;
    private final byte mehrwert, strafe, risiko, aufwand;
    private final float prioritaet;

    public UserStory(int id, String beschreibung, String akzeptanzkriterium, byte mehrwert, byte strafe, byte risiko, byte aufwand, String projekt) {
        this.id = id;
        this.beschreibung = beschreibung;
        this.akzeptanzkriterium = akzeptanzkriterium;
        this.mehrwert = mehrwert;
        this.strafe = strafe;
        this.risiko = risiko;
        this.aufwand = aufwand;
        this.projekt = projekt;
        prioritaet = (float) (mehrwert + strafe) / (aufwand + risiko);
    }

    public int getId() {
        return id;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public String getAkzeptanzkriterium() {
        return akzeptanzkriterium;
    }

    public byte getMehrwert() {
        return mehrwert;
    }

    public byte getStrafe() {
        return strafe;
    }

    public byte getRisiko() {
        return risiko;
    }

    public byte getAufwand() {
        return aufwand;
    }

    public String getProjekt() {
        return projekt;
    }

    public float getPrioritaet() {
        return prioritaet;
    }
}