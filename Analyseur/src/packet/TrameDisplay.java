package packet;

import java.io.IOException;

public class TrameDisplay {

	public static void main(String[] args) throws IOException {
		String suite;
		int options = 0;
		Trame trame = new Trame("data/ExempleTCP2.txt");
		suite = trame.addEthernet();
		
		if(suite == "Datagramme IP")
			options = trame.addHeaderIP();
			suite = trame.getProtocol();
		System.out.println(trame);
		
		
		
	}
	
	



}
