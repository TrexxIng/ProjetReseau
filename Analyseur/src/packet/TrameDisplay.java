package packet;

import java.io.IOException;
import java.util.List;

public class TrameDisplay {

	public static void main(String[] args) throws IOException {
		List<String> data;
		try {
			
			/** determine la liste d'octets à partir du fichier */
			//data = TraitementFichier.readFile2Col("data/ExempleHTTP.txt");
			
			data = TraitementFichier.readFile2Col("data/ErreurTaille.txt");
			
			/** calcule la trame */
			Trame trame = new Trame(data);	
			try {
				trame.calculTrameEthernet(data);
			} catch (ExceptionTaille e) {
				System.out.println(e.toString());
			}
				
			/** affichage */
			System.out.println(trame.formatDisplay(0));
			
		} catch (IOException e) {
			System.out.println("Fichier non trouvé: vérifier son nom (ou le chemin relatif)");
		} catch (ExceptionFormat e) {
			System.out.println(e.toString());
		} 
		
	}
	
	



}
