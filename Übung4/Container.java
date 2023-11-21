package org.hbrs.se1.ws23.uebung4;

import org.hbrs.se1.ws23.uebung4.Werte.ANSICodes;
import org.hbrs.se1.ws23.uebung4.persistence.PersistenceStrategyStream;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Scanner;

public class Container {
    private static final Container container = new Container();
    private ArrayList<UserStory> userStories;
    private final float VERSION = 1.0F;
    private PersistenceStrategyStream<UserStory> stream = new PersistenceStrategyStream<>();

    private Container() {
        userStories = new ArrayList<>();
    }

    public static void main(String[] args) {
        container.eingabe();
    }

    private void eingabe() {
        Scanner scanner = new Scanner(System.in);
        head();
        while(true) {
            eingabeZeile();
            befehle(scanner.nextLine().split(" "));
        }
    }

    private void head() {
        System.out.print(ANSICodes.PURPLE);
        System.out.println("##############################################################");
        System.out.println("# WeissNix GmbH - Verwaltung von User Stories (Version: " + VERSION + ") #");
        System.out.println("##############################################################");
    }

    private void eingabeZeile() {
        System.out.print(ANSICodes.PURPLE);
        System.out.print(getName() + "> ");
        System.out.print(ANSICodes.GREEN);
    }

    private String getName() {
        String ausgabe;
        try {
            ausgabe = InetAddress.getLocalHost().getHostName();
        } catch(Exception e) {
            ausgabe = "";
        }
        return ausgabe;
    }

    private void befehle(String[] eingabe) {
        switch(eingabe[0]) {
            case "enter":
                enter(eingabe);
                break;
            case "store":
                store(eingabe);
                break;
            case "load":
                load(eingabe);
                break;
            case "dump":
                dump(eingabe);
                break;
            case "search":
                search(eingabe);
                break;
            case "exit":
                exit();
                break;
            case "help":
                help(eingabe);
                break;
            default:
                System.out.println("Befehl " + eingabe[0] + " konnte nicht gefunden werden.");
                System.out.println("Benutzen Sie den Befehl help, um eine Liste aller verfügbaren Befehle zu erhalten.");
                break;
        }
    }

    private void enter(String[] eingabe) {
        try {
            int id = Integer.parseInt(eingabe[1]);
            String beschreibung = eingabe[2];
            String akzeptanzkriterium = eingabe[3];
            byte mehrwert = Byte.parseByte(eingabe[4]);
            if (mehrwert < 1 || mehrwert > 5)
                throw new ContainerException("Mehrwert nicht zulässig\nIst: " + mehrwert + ", Soll: 1 - 5");
            byte strafe = Byte.parseByte(eingabe[5]);
            if (strafe < 1 || strafe > 5)
                throw new ContainerException("Strafe nicht zulässig\nIst: " + strafe + ", Soll: 1 - 5");
            byte risiko = Byte.parseByte(eingabe[6]);
            if (risiko < 1 || risiko > 5)
                throw new ContainerException("Risiko nicht zulässig\nIst: " + risiko + ", Soll: 1 - 5");
            byte aufwand = Byte.parseByte(eingabe[7]);
            if (aufwand < 1)
                throw new ContainerException("Aufwand nicht zulässig\nIst: " + aufwand + ", Soll: > 0");
            String projekt = eingabe[8];
            for (UserStory x : userStories)
                if (id == x.getId())
                    throw new ContainerException("User Story ID ist bereits vorhanden.");
            userStories.add(new UserStory(id, beschreibung, akzeptanzkriterium, mehrwert, strafe, risiko, aufwand, projekt));
            System.out.println("User Story erfolgreich erstellt.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Es wurden zu wenig Parameter übergeben");
        } catch(NumberFormatException e) {
            System.out.println("Eingabe ungültig, nur Ganzzahlen sind erlaubt.");
        } catch(ContainerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void store(String[] eingabe) {
        try {
            stream.save(userStories);
            System.out.println("User Stories erfolgreich gespeichert.");
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void load(String[] eingabe) {
        try {
            userStories = stream.load();
            System.out.println("User Stories erfolgreich eingelesen.");
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void dump(String[] eingabe) {
        userStories.sort((o1, o2) -> Float.compare(o2.getPrioritaet(), o1.getPrioritaet()));
        System.out.println("ID, Beschreibung, Akzeptanzkriterium, Mehrwert, Strafe, Risiko, Aufwand, Projekt");
        for(UserStory x: userStories) {
            System.out.println(x.getId() + ", " + x.getBeschreibung() + ", " + x.getAkzeptanzkriterium() + ", " + x.getMehrwert() + ", " + x.getStrafe() + ", " + x.getRisiko() + ", " + x.getAufwand() + ", " + x.getProjekt());
        }
    }

    private void search(String[] eingabe) {
        userStories.sort((o1, o2) -> Float.compare(o1.getId(), o2.getId()));
        System.out.println("ID, Beschreibung, Akzeptanzkriterium, Mehrwert, Strafe, Risiko, Aufwand, Projekt");
        for(UserStory x: userStories)
            if(x.getProjekt().equals(eingabe[1]))
                System.out.println(x.getId() + ", " + x.getBeschreibung() + ", " + x.getAkzeptanzkriterium() + ", " + x.getMehrwert() + ", " + x.getStrafe() + ", " + x.getRisiko() + ", " + x.getAufwand() + ", " + x.getProjekt());
    }

    private void exit() {
        System.exit(0);
    }

    private void help(String[] eingabe) {
        if(eingabe.length == 1) {
            System.out.println("Verfügbare Befehle:");
            System.out.println("enter, store, load, dump, search, exit, help");
            System.out.println("Für Hilfe zu bestimmten Befehlen:");
            System.out.println("help <Befehl>");
        } else {
            switch(eingabe[1]) {
                case "enter":
                    enterHelp();
                    break;
                case "store":
                    storeHelp();
                    break;
                case "load":
                    loadHelp();
                    break;
                case "dump":
                    dumpHelp();
                    break;
                case "search":
                    searchHelp();
                    break;
                case "exit":
                    exitHelp();
                    break;
                case "help":
                    helpHelp();
                    break;
                default:
                    System.out.println("Befehl " + eingabe[1] + " konnte nicht gefunden werden.");
                    System.out.println("Benutzen Sie den Befehl help, um eine Liste aller verfügbaren Befehle zu erhalten.");
                    break;
            }
        }
    }

    private void enterHelp() {
        System.out.println("Beschreibung:");
        System.out.println("Eingabe einer User Story in das System.");
        System.out.println("Syntax:");
        System.out.println("enter <ID> <Beschreibung> <Akzeptanzkriterium> <Mehrwert> <Strafe> <Risiko> <Aufwand> <Projekt>");
    }

    private void storeHelp() {
        System.out.println("Beschreibung:");
        System.out.println("Eingegebene User Stories werden auf dem System abgespeichert.");
        System.out.println("Syntax:");
        System.out.println("store");
    }

    private void loadHelp() {
        System.out.println("Beschreibung:");
        System.out.println("User Stories werden von dem System geladen.");
        System.out.println("Syntax:");
        System.out.println("load");
    }

    private void dumpHelp() {
        System.out.println("Beschreibung:");
        System.out.println("Ausgabe einer Liste aller, nach Priorität sortierten, User Stories.");
        System.out.println("Syntax:");
        System.out.println("dump");
    }

    private void searchHelp() {
        System.out.println("Beschreibung:");
        System.out.println("Es werden alle User Stories, die zu einem Projekt angelegt wurden, angezeigt.");
        System.out.println("Syntax:");
        System.out.println("search <Projekt Bezeichnung>");
    }

    private void exitHelp() {
        System.out.println("Beschreibung:");
        System.out.println("Verlassen der Anwendung");
        System.out.println("Syntax:");
        System.out.println("exit");
    }

    private void helpHelp() {
        System.out.println("Beschreibung:");
        System.out.println("Allgemeine Hilfe, oder auch zu bestimmten Befehlen.");
        System.out.println("Syntax:");
        System.out.println("help [Befehl]");
    }
}