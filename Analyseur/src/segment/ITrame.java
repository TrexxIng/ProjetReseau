package segment;

import java.util.List;

import exceptions.ExceptionIncoherence;
import exceptions.ExceptionSupport;


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
	
	/**
	 * détermine le segent suivant de la trame 
	 * @return le segment suivant
	 */
	String nextSegment();
	
	/**
	 * detection d'erreur sans interrompre le programme 
	 * @throws ExceptionSupport probleme de support de version
	 * @throws ExceptionIncoherence probleme d'incoherence des donnees
	 */
	void errorDetect() throws ExceptionSupport, ExceptionIncoherence;
	
	
	/**
	 * detecte des erreurs qui n'impacte pas le programme
	 * @return le message à afficher
	 */
	String messageVerification();
}
