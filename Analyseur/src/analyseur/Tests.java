package analyseur;

import java.io.IOException;
import java.util.List;

import exceptions.ExceptionFormat;
import exceptions.ExceptionIncoherence;
import exceptions.ExceptionSupport;
import exceptions.ExceptionTaille;

public class Tests {

	public static void main(String[] args) throws IOException {
		try {
			
			/** determine la liste d'octets à partir du fichier */
			List<String> data1 = TraitementFichier.readFile2Col("data/ExempleHTTP2.txt");			
			List<String> data2 = TraitementFichier.readFile2Col("data/ErreurTaille.txt");
			/**List<List<String>> data3 = TraitementFichier.ReadFile("data/DoubleTrame.txt");*/
			
			System.out.println("-----------------------------------------------------");
			displayTrame(data2);
			System.out.println("-----------------------------------------------------");
			displayTrame(data1);
			System.out.println("-----------------------------------------------------");
			
			/**for(int i = 0; i<data3.size();i++) {
				displayTrame(data3.get(i));
				System.out.println("-----------------------------------------------------");
			}*/
			
			/**TraitementFichier.writeFile("trame.txt", data3, true);*/
			
			
		} catch (IOException e) {
			System.out.println("Fichier non trouvé: vérifier son nom (ou le chemin relatif)");
		} catch (ExceptionFormat e) {
			System.out.println(e.toString());
		} 
		
	}
	
	
	public static void displayTrame(List<String> data) {
		Trame trame = new Trame(data);	
		try {				
			/** calcule la trame */
			trame.calculTrameEthernet();				
		} catch (ExceptionTaille | ExceptionSupport | ExceptionIncoherence e) {			
			/** attrape une exception et permet l'affichage des datas non traitées */
			trame.addDataNonTraduite();
			System.out.println(e.toString());			
		} finally {				
			/** affichage */
			System.out.println(trame.formatDisplay(0));
			System.out.println(trame.messageVerification());
		}			
	}
	
	



}
