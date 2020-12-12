package packet;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import segment.Ethernet;
import segment.HeaderDatagramIP;
import segment.ITrame;
import segment.UDP;


public class Trame {
	List<ITrame> listTrame;
	List<String> listOctets;
	
	public Trame(String fileName) throws IOException {
		this.listTrame = new ArrayList<>();
		this.listOctets = readFile(fileName);
	}
	
	/**
	 * lit le fichier et retourne les octets
	 * @param file: nom du ficheir
	 * @return liste de String, chacun correspondant à un octet
	 * @throws IOException fichier non trouvé
	 */
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
	 * @return la liste des octets en données
	 */
	public List<String> addEthernet(List<String> data) {
		Ethernet ether = new Ethernet(data);
		listTrame.add(ether);
		return ether.getData();
	}
	
	
	/**
	 * ajout l'entete du datagramme IP
	 * @return la liste des octets en données
	 */
	public List<String> addHeaderIP(List<String> data) {
		HeaderDatagramIP hip = new HeaderDatagramIP(data);
		listTrame.add(hip);
		return hip.getData();
	}
	
	public List<String> addUDP(List<String> data) {
		UDP udp = new UDP(data);
		listTrame.add(udp);
		return udp.getData();
		
	}	
	
	
	/**
	 * determine le segment suivant
	 * @param seg: numero de la trame
	 * @return le nom du segment suivant, "pas de segment" si ce n'est pas le cas
	 */
	public String getNextSegment(int seg) {
		if(listTrame.size()-1 - seg < 0) 
			return "pas de segment";
		if(listTrame.get(seg) instanceof Ethernet)
			return ((Ethernet)listTrame.get(seg)).getDataType();
		if(listTrame.get(seg) instanceof HeaderDatagramIP)
			return ((HeaderDatagramIP)listTrame.get(seg)).getProtocol();
		return "pas de segment";
	}
	
	/**
	 * determine la taille des options du datagramme IP
	 * @return la taille des options, -1 si ce n'est pas un datagramme IP
	 */
	public int getTailleOptions() {
		if(listTrame.size() < 2) 
			return -1;
		if(listTrame.get(1) instanceof HeaderDatagramIP)
			return ((HeaderDatagramIP)listTrame.get(1)).getTailleOptions();
		return -1;
	}
	
	public List<String> getOctets(){
		return listOctets;
	}

	
	@Override
	public String toString() {
		String s = "Trame: "+listOctets.size()+" octets";
		for(int i = 0; i< listTrame.size(); i++) {
			s = s + "\n" + listTrame.get(i).toString();
		}
		return s;
	}
	
	public String formatDisplay(int tab) {
		String stab ="";
		if(tab > 0) {
			for (int i = 0; i<tab; i++) {
				stab += "\t";
			}
		}
		String s =stab+ "Trame: "+listOctets.size()+" octets";
		for(int i = 0; i< listTrame.size(); i++) {
			s = s + "\n" + listTrame.get(i).formatDisplay(tab+1);
		}
		return s;
	}
	

	

		
}
