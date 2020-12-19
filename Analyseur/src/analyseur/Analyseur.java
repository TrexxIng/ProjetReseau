package analyseur;

import java.io.IOException;
import java.util.List;

import exceptions.ExceptionFormat;

public class Analyseur {

	public static void main(String[] args) throws IOException {
		String message ="On demande 3 arguments\nArguments: <filenameEntry> <multifile> <filenameExit>\n"
				+ "\t filenameEntry: nom du fichier d'entrée (chemin relatif)\n"
				+ "\t multifile: 0 si on ecrit toute le trame sur le même fichier, 1 si on ecrit une trame par fichier\n"
				+ "\t filenameExit: nom du fichier en sortie (sera ecrit dans le dossier trames)\n";
	
		try {
			if(args.length != 3) {
				System.out.println(message+"\nErreur: nombre d'arguments incorrect");
			} else {

				/** Pour la liste de trame sous forme de listes d'octets */
				List<List<String>> data;
						
				/** Choix entre ecriture dans un fichier ou plusieurs fichiers */
				if(args[1].equals("0") ||
						args[1].equals("False") || args[1].equals("false") ||
						args[1].equals("Faux") || args[1].equals("faux") ||
						args[1].equals("F") || args[1].equals("f")) {
					data = TraitementFichier.ReadFile(args[0]);
					TraitementFichier.writeFile(args[2], data, false);
				}
				else if(args[1].equals("1")||
						args[1].equals("T") || args[1].equals("t") ||
						args[1].equals("True") || args[1].equals("true") ||
						args[1].equals("Vrai") || args[1].equals("vrai") ||
						args[1].equals("V") || args[1].equals("v")) {
					data = TraitementFichier.ReadFile(args[0]);
					TraitementFichier.writeFile(args[2], data, true);
				}
				else {
					data = TraitementFichier.ReadFile(args[1]);
					System.out.println(message+"\nErreur: valeur <mult> incorrecte (valeurs acceptées: 0|1|vrai|faux|true|false|Vrai|Faux|True|False|V|F|T");			
				}
			}
		} catch (IOException e) {
			System.out.println(message+"\nErreur: fichier d'entrée introuvable\n"
					+ "Conseil: le placer dans le dossier data et utiliser l'argument filenameEntry = data/[nom du fichier]");
		} catch (ExceptionFormat e) {
			System.out.println(e.toString());
		} 
		
	}

}
