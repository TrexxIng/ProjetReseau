package packet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TrameDisplay {

	public static void main(String[] args) throws IOException {
		String suite;
		int options;
		Trame trame = new Trame("data/ExempleTCP.txt");
		suite = trame.addEthernet();
		if(suite == "Datagramme IP")
			options = trame.addHeaderIP();
		System.out.println(trame);
		
	}
	
	



}
