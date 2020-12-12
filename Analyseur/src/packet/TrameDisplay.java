package packet;

import java.io.IOException;
import java.util.List;

public class TrameDisplay {

	public static void main(String[] args) throws IOException {
		List<String> data;
		String suite;
		int options = 0;
		Trame trame = new Trame("data/ExempleICMP2.txt");
		data = trame.getOctets();
		
		
		/** ajout de la trame Ethernet */
		data = trame.addEthernet(data);
		suite = trame.getNextSegment(0);
		
		
		/** ajout de IP */
		if(suite == "Datagramme IP") {
			data = trame.addHeaderIP(data);
			suite = trame.getNextSegment(1);
			options = trame.getTailleOptions(1);
			
			if(options > 0) {
				for(int i = 0; i<options; i++)
					data.remove(0);
			}
		}
		
		/** ajout de UDP */
		if(suite == "UDP") {
			data = trame.addUDP(data);
		}
		
		/** ajout de TCP */
		if(suite == "TCP") {
			data = trame.addTCPHeader(data);
			options = trame.getTailleOptions(2);
		}
		
		/** ajout de ICMP */
		if(suite == "ICMP") {
			data = trame.addICMP(data);
		}
		
		System.out.println(trame.formatDisplay(0));
		
		
	}
	
	



}
