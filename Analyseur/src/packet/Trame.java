package packet;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import protocole.Ethernet;
import protocole.HeaderDatagramIP;
import protocole.ITrame;


public class Trame {
	List<ITrame> listTrame;
	List<String> listOctets;
	
	public Trame(String fileName) throws IOException {
		this.listTrame = new ArrayList<>();
		this.listOctets = readFile(fileName);
	}
	
	public List<String> readFile(String file) throws IOException{
		List<String> hex = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		String line; 
		while ((line = br.readLine())!=null) { 
			for (String word : line.split(" ")) {
				if(word.equals("")) continue;	// ignore les espaces vides
				if(word.length() != 2) continue;	// ignore ce qui n'est pas un octet
	        	hex.add(word);
	        } 
		} 
	    br.close();
		return hex;
	}
	
	/**
	 * ajout de la trame ethernet
	 * @return le nom du type ethernet
	 */
	public String addEthernet() {
		List<String> listEther = new ArrayList<>();
		for(int i=0; i< 14; i++) {
			listEther.add(listOctets.get(i));
		}
		Ethernet ether = new Ethernet(listEther);
		listTrame.add(ether);
		return ether.dataType();
	}
	
	public int addHeaderIP() {
		List<String> listHIP = new ArrayList<>();
		for(int i = 14; i<34; i++) {
			listHIP.add(listOctets.get(i));
		}
		HeaderDatagramIP hip = new HeaderDatagramIP(listHIP);
		listTrame.add(hip);
		return hip.getTailleIP();
	}

	
	@Override
	public String toString() {
		String s = "Trame: "+listOctets.size()+" octets";
		for(int i = 0; i< listTrame.size(); i++) {
			s = s + "\n" + listTrame.get(i).toString();
		}
		return s;
	}
	

	

		
}
