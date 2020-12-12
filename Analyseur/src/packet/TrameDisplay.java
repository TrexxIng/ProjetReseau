package packet;

import java.io.IOException;
import java.util.List;

public class TrameDisplay {

	public static void main(String[] args) throws IOException {
		List<String> data;
		String suite;
		int options = 0;
		Trame trame = new Trame("data/ExempleUDP2.txt");
		data = trame.getOctets();
		
		data = trame.addEthernet(data);
		suite = trame.getNextSegment(0);
		
		if(suite == "Datagramme IP") {
			data = trame.addHeaderIP(data);
			suite = trame.getNextSegment(1);
			options = trame.getTailleOptions();
			
			if(options > 0) {
				for(int i = 0; i<options; i++)
					data.remove(0);
			}
		}
		
		if(suite == "UDP") {
			data = trame.addUDP(data);
		}
		
		System.out.println(trame.formatDisplay(0));
		
		
	}
	
	



}
