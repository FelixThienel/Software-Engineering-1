package org.hbrs.se1.ws23.uebung1.view;

import org.hbrs.se1.ws23.uebung1.control.Factory;
import org.hbrs.se1.ws23.uebung1.control.GermanTranslator;

public class Client {

		/*
		 * Methode zur Ausgabe einer Zahl auf der Console
		 * (auch bezeichnet als CLI, Terminal)
		 *
		 */
		 void display( int aNumber ){
			 GermanTranslator germanTranslator = Factory.createGermanTranslator();
			 String ausgabe	= germanTranslator.translateNumber(aNumber);
			 System.out.println("Das Ergebnis der Berechnung: " + ausgabe);
		 }
}





