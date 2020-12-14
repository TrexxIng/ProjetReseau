package packet;

import java.io.IOException;
import java.util.List;

public class TrameDisplay {

	public static void main(String[] args) throws IOException {
		List<String> data = 
				TraitementFichier.readFile2Col("data/ExempleHTTP.txt");
		String suite;		
		Trame trame = new Trame(data);
		
		
		
		/** ajout de la trame Ethernet */
		data = trame.addEthernet(data);
		suite = trame.getNextSegment(0);
		
		/** ajout de ARP/RARP */
		if(suite == "ARP" || suite == "RARP") {
			data = trame.addARP(data,suite);
		}	
		
		/** ajout de IP */
		else if(suite == "Datagramme IP") {
			data = trame.addIP(data);
			suite = trame.getNextSegment(1);
			
			/** ajout de UDP */
			if(suite == "UDP") {
				data = trame.addUDP(data);
			}
			
			/** ajout de TCP */
			else if(suite == "TCP") {
				data = trame.addTCP(data);
				suite =  trame.getNextSegment(2);
				if(suite == "HTTP") {
					System.out.println("HTTP");
				}
			}
			
			/** ajout de ICMP */
			else if(suite == "ICMP") {
				data = trame.addICMP(data);
			}
		}
		
		/** ajout des données */
		if(data.size() > 0) {
			data = trame.addDonnees(data);
		}
		
		/** affichage */
		System.out.println(trame.formatDisplay(0));
		
		
	}
	
	



}
