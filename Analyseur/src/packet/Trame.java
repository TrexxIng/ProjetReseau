package packet;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import segment.ARP;
import segment.Contenu;
import segment.Ethernet;
import segment.ICMP;
import segment.ITrame;
import segment.InternetProtocol;
import segment.TCP;
import segment.UDP;


public class Trame {
	private List<ITrame> listTrame;
	private List<String> listOctets;
	private int tailleTrame;
	
	public Trame(String fileName) throws IOException {
		this.listTrame = new ArrayList<>();
		this.listOctets = readFile(fileName);
		this.tailleTrame = listOctets.size();
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
		List<String> list = new ArrayList<>();
		while ((line = br.readLine())!=null) { 
			for (String word : line.split(" ")) {
				list.add(word);
				if(word.equals("")) continue;	// ignore les espaces vides
				if(word.length() != 2) continue;	// ignore ce qui n'est pas un octet
				if(word.matches("-?[0-9a-fA-F]+"))
	        	hex.add(word);
	        } 
		} 
		List<List<String>> liste = traitementDataFichier(list);
	    br.close();
		return hex;
	}
	
	/** fait une liste, chaque element correspondant à une liste d'octet
	 * pour chaque tram
	 * @param list: liste de String tel que lu dans le fichier
	 * @return liste de liste d'octets
	 */
	public  List<List<String>> traitementDataFichier(List<String> list){
		List<List<String>> listTrame = new ArrayList<>();
		
		/** on separe en deux listes par ligne: 
		 * 1 pour les octets
		 * 1 pour composée de [3eme colonne, Offset]*/
		List<List<String>> colonnes = new ArrayList<>();
		List<String> element = new ArrayList<>();
		element.add("debut");
		for(int i = 0; i<list.size(); i++) {
			
			/** nouvelle colonne **/
			if(list.size() - 1 > i && 
					list.get(i).equals("") && 
					list.get(i+1).equals("")) {
				colonnes.add(element);
				element = new ArrayList<>();
				i++;
				
			} else {
				element.add(list.get(i));
			}
				
		}
		colonnes.add(element);
	
		return listTrame;
		
	}
	
	/**
	 * ajout de la trame ethernet
	 * @param data: liste d'octets de l'ensemble de la trame
	 * @return la liste des octets en données
	 */
	public List<String> addEthernet(List<String> data) {
		Ethernet ether = new Ethernet(data);
		listTrame.add(ether);
		return ether.getData();
	}
	
	
	/**
	 * ajoute le datagramme IP
	 * @param data: liste d'octets de l'ensemble du datagramme
	 * @return la liste des octets en données
	 */
	public List<String> addIP(List<String> data) {
		InternetProtocol hip = new InternetProtocol(data);
		listTrame.add(hip);
		return hip.getData();
	}
	
	
	/**
	 * ajoute l'entete de l'UDP
	 * @param data: liste d'octets de l'ensemble de la trame
	 * @return la liste des octets en données
	 */
	public List<String> addUDP(List<String> data) {
		UDP udp = new UDP(data);
		listTrame.add(udp);
		return udp.getData();
		
	}	
	
	
	/**
	 * ajoute l'entete du TCP
	 * @param data: liste d'octets de l'ensemble de la trame
	 * @return la liste des octets en données
	 */
	public List<String> addTCP(List<String> data) {
		TCP tcp = new TCP(data);
		listTrame.add(tcp);
		return tcp.getData();
	}
	
	public List<String> addARP(List<String> data,String type){
		ARP arp = new ARP(data, type);
		listTrame.add(arp);
		return arp.getData();
	}

	/**
	 * ajoute le message ICMP
	 * @param data: liste d'octets du message ICMP
	 * @return la liste des octets en données
	 */
	public List<String> addICMP(List<String> data){
		ICMP icmp = new ICMP(data);
		listTrame.add(icmp);
		return icmp.getData();
	}
	
	
	/**
	 * ajoute les données
	 * @param data: liste d'octets représentant les données
	 * @return liste vide
	 */
	public List<String> addDonnees(List<String> data){
		Contenu fin = new Contenu(data);
		listTrame.add(fin);
		return fin.getData();
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
		if(listTrame.get(seg) instanceof InternetProtocol)
			return ((InternetProtocol)listTrame.get(seg)).getProtocol();
		return "pas de segment";
	}
	
	
	/**
	 * determine la taille des options d'une couche
	 * @param i: position dans la liste de segments
	 * @return la taille des options, -1 s'il y a un probleme de couche
	 */
	public int getTailleOptions(int i) {
		if(listTrame.size() < i) 
			return -1;
		return listTrame.get(i).getTailleOptions();
	}
	
	/**
	 * retourne la liste des octets de la trame
	 * @return liste des octets (string)
	 */
	public List<String> getOctets(){
		return listOctets;
	}

	
	@Override
	public String toString() {
		String s = "Trame: "+this.tailleTrame+" octets";
		for(int i = 0; i< listTrame.size(); i++) {
			s = s + "\n" + listTrame.get(i).toString();
		}
		return s;
	}
	
	/**
	 * donne un string permettant d'afficher la trame avec la tabulation
	 * @param tab tabulation initiale
	 * @return message pour affichage
	 */
	public String formatDisplay(int tab) {
		String stab ="";
		if(tab > 0) {
			for (int i = 0; i<tab; i++) {
				stab += "\t";
			}
		}
		String s =stab+ "Trame: "+this.tailleTrame+" octets";
		for(int i = 0; i< listTrame.size(); i++) {
			s = s + "\n" + listTrame.get(i).formatDisplay(tab+1);
		}
		return s;
	}
	

	

		
}
