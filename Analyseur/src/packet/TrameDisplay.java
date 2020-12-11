package packet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TrameDisplay {

	public static void main(String[] args) throws IOException {
		Trame trame = new Trame("data/ExempleARP.txt");
		String suite = trame.addEthernet();
		System.out.println(trame);
	}



}
