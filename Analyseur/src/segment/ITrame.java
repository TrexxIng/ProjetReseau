package segment;

import java.util.List;


public interface ITrame {
	
	/**
	 * retourne les octets qui ne font pas partie de la trame
	 * c'est à dire le segment suivant ou les données
	 * @return liste d'octets
	 */
	List<String> getData();
	
	/**
	 * permet un affichage formaté avec les tabulations
	 * @param tab le nombre de tabulation
	 * @return l'affichage texte
	 */
	String formatDisplay(int tab);
	
	/**
	 * taille des options de la trame calculé
	 * @return taille
	 */
	int getTailleOptions();
	
	/**
	 * taille de la trame calculé
	 * @return la taille
	 */
	int getSize();
}
