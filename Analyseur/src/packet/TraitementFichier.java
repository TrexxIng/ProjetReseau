package packet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TraitementFichier {

	/**
	 * lit le fichier contenant deux colonnes et retourne les octets
	 * - première colonne pour l'Offset
	 * - deuxieme colonne pour les octets
	 * @param file: nom du fichier
	 * @return liste de String, chacun correspondant à un octet
	 * @throws IOException fichier non trouvé
	 */
	public static List<String> readFile2Col(String file) throws IOException,ExceptionFormat{
		
		List<String> hex = new ArrayList<>();
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		String line; 
		int cpt = 16;
		String numligne="";
		int cptligne = 0;
		while ((line = br.readLine())!=null) { 
			if(cpt != 16) {
				throw new ExceptionFormat("Probleme de nombre d'octets ou caractère non conforme",cptligne);
			}
			cptligne++;
			cpt = 0;
			for (String word : line.split(" ")) {
				if(word.equals("")) continue; 	
				if(word.length() == 4 && word.matches("-?[0-9a-fA-F]+")) {
					numligne = word;
				}
				if(word.length() != 2) continue;	// ignore ce qui n'est pas un octet
				if(word.matches("-?[0-9a-fA-F]+")) {
					hex.add(word);
					cpt++;
				}
	        }
			if(numligne == "" || Integer.parseInt(numligne,16)%16 != 0) {
				throw new ExceptionFormat("Probleme de numerotation de la ligne",cptligne);
			}
			numligne = "";
		} 
		if(cpt > 16) {
			throw new ExceptionFormat("Probleme de nombre d'octets ou caractère non conforme",cptligne);
		}
		
	    br.close();
		return hex;
	}
	

	/**
	 * lit le fichier contenant trois colonnes et retourne les octets
	 * - première colonne pour l'Offset
	 * - deuxieme colonne pour les octets
	 * - troisieme pour les octets en ascii
	 * @param file: nom du fichier
	 * @return liste de String, chacun correspondant à un octet
	 * @throws IOException fichier non trouvé
	 */
	public static List<String> readFile3Col(String file) throws IOException{
		List<String> hex = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		String line; 
		boolean secondColonne;
		boolean firstColonne;
		boolean follow;
		//List<String> list = new ArrayList<>();
		while ((line = br.readLine())!=null) { 
			
			/** permet de savoir sur quelle colonne on se trouve
			 * les colonnes sont séparées par deux espaces */
			secondColonne = false;
			firstColonne = true;
			follow = false;

			for (String word : line.split(" ")) {
				
				/** s'il y a un espace, on le retiens */
				if(word.equals("") && !follow ) {
					 follow = true;
					 continue;
				}
				/** si deux espace se suivent alors on est à la colonne suivante
				 *  sinon on reste dans la meme colonne */
				if(word.equals("") && follow) {
					if(firstColonne) {
						firstColonne = false;
						secondColonne = true;
					} else if(secondColonne) {
						secondColonne = false;
					} else if(!secondColonne && !firstColonne) {
						break;
					}
				} else if(follow) {
					follow = false;
				}
				
				/** dans une meme colonne, on garde les offsets et octets
				 *  on supprime ce qui n'est pas en hexa */
				if(word.length() != 2 && word.length() != 4) continue;	
				if(word.matches("-?[0-9a-fA-F]+"))
	        	hex.add(word);
	        } 
		} 
		//List<List<String>> liste = traitementDataFichier(list);
	    br.close();
		return hex;
	}
}
