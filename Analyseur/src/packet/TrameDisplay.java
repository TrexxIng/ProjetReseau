package packet;

import java.io.IOException;
import java.util.List;

public class TrameDisplay {

	public static void main(String[] args) throws IOException {
		List<String> data;
		String suite;
		int options = 0;
		Trame trame = new Trame("data/ExempleICMP.txt");
		data = trame.getOctets();
		
		
		/** ajout de la trame Ethernet */
		data = trame.addEthernet(data);
		suite = trame.getNextSegment(0);
		
		/** ajout de ARP/RARP */
		if(suite == "ARP" || suite == "RARP") {
			data = trame.addARP(data);
		}
		
		/** ajout de IP */
		if(suite == "Datagramme IP") {
			data = trame.addHeaderIP(data);
			suite = trame.getNextSegment(1);
			options = trame.getTailleOptions(1);
			data = trame.addOption(data,options);
		}
		
		/** ajout de UDP */
		if(suite == "UDP") {
			data = trame.addUDP(data);
			options = trame.getTailleOptions(3);
			data = trame.addOption(data,options);
		}
		
		/** ajout de TCP */
		if(suite == "TCP") {
			data = trame.addTCPHeader(data);
			options = trame.getTailleOptions(3);
			data = trame.addOption(data,options);
		}
		
		/** ajout de ICMP */
		if(suite == "ICMP") {
			data = trame.addICMP(data);
			options = trame.getTailleOptions(3);
			data = trame.addOption(data,options);
		}
		
		
		
		System.out.println(trame.formatDisplay(0));
		
		
	}
	
	



}
