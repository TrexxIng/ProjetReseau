package analyseur;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import exceptions.ExceptionFormat;
import exceptions.ExceptionIncoherence;
import exceptions.ExceptionSupport;
import exceptions.ExceptionTaille;

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
				throw new ExceptionFormat("Probleme de nombre d'octets ou caractère non conforme",file,cptligne);
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
				throw new ExceptionFormat("Probleme de numerotation de la ligne",file,cptligne);
			}
			numligne = "";
		} 
		if(cpt > 16) {
			throw new ExceptionFormat("Probleme de nombre d'octets ou caractère non conforme",file,cptligne);
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
	 * @return liste de String, chacun correspondant à un octet ou Offset
	 * @throws IOException fichier non trouvé
	 */
	private static List<String> readFile3Col(String file) throws IOException{
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

	    br.close();
		return hex;
	}
	
	public static List<List<String>> ReadFile(String file) throws IOException, ExceptionFormat{
		/** octets et offset */
		List<String> hex = readFile3Col(file);	
		/** liste de trames */
		List<List<String>> listData = new ArrayList<>();
		/** une trame (liste d'octets) */
		List<String> data = new ArrayList<>();
		
		int cpt = 0;   		// compteur d'octets entre chaque Offset
		int Offset;  	// valeur de l'Offset de la ligne
		int ligne = 1;		// numero de la ligne
		String mot;
	
		/** trouve les trames avec la liste d'octets */
		for(int i = 0; i<hex.size(); i++) {
			mot = hex.get(i);
			if(mot.length() == 4) {
				Offset = Integer.parseInt(mot,16);
				/** debut de la premiere trame */
				if(Offset == 0 && i == 0) {
					cpt = 0;
					ligne = 1;
				}
				/** debut d'une trame qui n'est pas la première */
				else if(Offset == 0 && i != 0) {
					listData.add(data);			
					data = new ArrayList<>();
					ligne++;
					cpt = 0;
				} 
				/** nouvelle ligne d'une trame */
				else if(Offset%16 == 0) {
					if(cpt%16 != 0) {
						throw new ExceptionFormat("erreur dans le nombre d'octets",file,ligne);
					}
					cpt = 0;
					ligne++;
				} 
				else {
					throw new ExceptionFormat("erreur dans la numérotation des Offset",file,ligne);
				}
			}
			if(mot.length() == 2) {
				data.add(mot);
				cpt++;
			}
		}
		listData.add(data);
		
		
		return listData;
	}
	
	
	public static void writeFile(String fileName,List<List<String>> data, boolean multiple) {

		/** Trame */
		Trame trame;
		
		/** separateur entre les trames si on ecrit dans un unique fichier */
		String separateur = "\n------------------------------------------------------------\n";
		
		/** dossier dans lequel on ecrit le fichier */
		String dossier = "trames/";
		
		/** extension du fichier */
		String extension = "";
		int f = fileName.lastIndexOf('.');
		int p = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));
		if (f > p) {
		    extension = "."+fileName.substring(f+1);
		}
		if(extension.contentEquals("."))
			extension = ".txt";
		
		/** nom du fichier en sortie */
		String fileNameExit;
				
		try {
			/** tout ecrire en un seul fichier */
			if(!multiple) {
				fileNameExit = dossier+fileName;
				FileWriter exitFile = new FileWriter(fileNameExit); 
				exitFile.write(separateur);
				for(int i = 0; i<data.size();i++) {
					trame = new Trame(data.get(i));
					try {				
						/** calcule la trame */
						trame.calculTrameEthernet();				
					} catch (ExceptionTaille | ExceptionSupport | ExceptionIncoherence e) {			
						/** attrape une exception et permet l'affichage des datas non traitées */
						trame.addDataNonTraduite();
						exitFile.write(e.toString());			
					} finally {				
						/** ecriture */
						exitFile.write(trame.formatDisplay(0));
						exitFile.write(trame.messageVerification());
						exitFile.write(separateur);
					}
					
				}
				System.out.println("Ecriture des trames dans le fichier '"+fileNameExit+"'");
				exitFile.close();
			} 
			/** ecrire une trame par fichier */
			else {
				
				for(int i = 0; i<data.size();i++) {
					fileNameExit = dossier+ fileName.replace(extension,"")+"_"+i+extension;
					FileWriter exitFile = new FileWriter(fileNameExit); 
					trame = new Trame(data.get(i));
					try {				
						/** calcule la trame */
						trame.calculTrameEthernet();				
					} catch (ExceptionTaille | ExceptionSupport | ExceptionIncoherence e) {			
						/** attrape une exception et permet l'affichage des datas non traitées */
						trame.addDataNonTraduite();
						exitFile .write(e.toString());			
					} finally {				
						/** ecriture */
						exitFile .write(trame.formatDisplay(0));
						exitFile .write(trame.messageVerification());
					}
					exitFile.close();
					System.out.println("Ecriture d'une trame dans le fichier '"+fileNameExit+"'");
				}
			}
			
		} catch (IOException e) {
			System.out.println("Erreur lors de l'écriture du fichier '"+fileName+"'");
		}
		
	}
	
}
