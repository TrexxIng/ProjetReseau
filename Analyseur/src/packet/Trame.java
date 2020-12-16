package packet;


import java.util.ArrayList;
import java.util.List;

import segment.ARP;
import segment.DataDump;
import segment.Ethernet;
import segment.HTTP;
import segment.ICMP;
import segment.ITrame;
import segment.InternetProtocol;
import segment.TCP;
import segment.UDP;


public class Trame {
	private List<ITrame> listTrame;
	private List<String> listOctets;
	private int tailleTrame;
	
	public Trame(List<String> listOctets) {
		this.listTrame = new ArrayList<>();
		this.listOctets = listOctets;
		this.tailleTrame = listOctets.size();
	}
	
	/**
	 * Calcul des sections de la trame 
	 * @param data: liste d'octets correspondant à une trame
	 * @throws ExceptionTaille probleme de nombre d'octets
	 */
	public void calculTrameEthernet(List<String> data) throws ExceptionTaille {
		
		String suite = "Ethernet";
		
		/** ajout de la trame Ethernet */
		data = this.addEthernet(data);
		suite = this.getNextSegment(0);
		
		/** ajout de ARP/RARP */
		if(suite == "ARP" || suite == "RARP") {
			data = this.addARP(data,suite);
		}	
		
		/** ajout de IP */
		else if(suite == "Datagramme IP") {
			data = this.addIP(data);
			suite = this.getNextSegment(1);
			
			/** ajout de UDP */
			if(suite == "UDP") {
				data = this.addUDP(data);
			}
			
			/** ajout de TCP */
			else if(suite == "TCP") {
				data = this.addTCP(data);
				suite =  this.getNextSegment(2);
				if(suite == "HTTP") {
					data = this.addHTTP(data);
				}
			}
			
			/** ajout de ICMP */
			else if(suite == "ICMP") {
				data = this.addICMP(data);
			}
		}
		
		/** ajout des données s'il en reste */
		if(data.size() > 0) {
			data = this.addDonnees(data);
		}
	}
	
	/**
	 * ajout de la trame ethernet
	 * @param data: liste d'octets de l'ensemble de la trame
	 * @return la liste des octets en données
	 * @throws ExceptionTaille: probleme de nombre d'octets
	 */
	private List<String> addEthernet(List<String> data) throws ExceptionTaille{
		Ethernet ether = new Ethernet(data);
		listTrame.add(ether);
		return ether.getData();
	}
	
	
	/**
	 * ajoute le datagramme IP
	 * @param data: liste d'octets de l'ensemble du datagramme
	 * @return la liste des octets en données
	 * @throws ExceptionTaille: probleme du nombre d'octets d'octets
	 */
	private List<String> addIP(List<String> data) throws ExceptionTaille {
		InternetProtocol hip = new InternetProtocol(data);
		listTrame.add(hip);
		return hip.getData();
	}	
	
	/**
	 * ajoute l'entete de l'UDP
	 * @param data: liste d'octets de l'ensemble de la trame
	 * @return la liste des octets en données
	 * @throws ExceptionTaille: probleme de nombre d'octets 
	 */
	private List<String> addUDP(List<String> data) throws ExceptionTaille{
		UDP udp = new UDP(data);
		listTrame.add(udp);
		return udp.getData();
		
	}	
		
	/**
	 * ajoute l'entete du TCP
	 * @param data: liste d'octets de l'ensemble de la trame
	 * @return la liste des octets en données
	 * @throws ExceptionTaille: probleme du nombre d'octets 
	 */
	private List<String> addTCP(List<String> data) throws ExceptionTaille{
		TCP tcp = new TCP(data);
		listTrame.add(tcp);
		return tcp.getData();
	}
	
	
	/**
	 * ajoute de l'ARP/RARP
	 * @param data: liste d'octets de l'ensemble de la trame
	 * @param type: permet de savoir si c'est ARP ou RARP
	 * @return la liste des octets en données
	 * @throws ExceptionTaille: probleme du nombre d'octets
	 */
	private List<String> addARP(List<String> data,String type) throws ExceptionTaille{
		ARP arp = new ARP(data, type);
		listTrame.add(arp);
		return arp.getData();
	}

	/**
	 * ajoute le message ICMP
	 * @param data: liste d'octets du message ICMP
	 * @return la liste des octets en données
	 * @throws ExceptionTaille: probleme de nombre d'octets
	 */
	private List<String> addICMP(List<String> data) throws ExceptionTaille{
		ICMP icmp = new ICMP(data);
		listTrame.add(icmp);
		return icmp.getData();
	}
	
	
	/**
	 * ajoute les données
	 * @param data: liste d'octets représentant les données
	 * @return liste vide
	 */
	private List<String> addDonnees(List<String> data){
		DataDump fin = new DataDump(data);
		listTrame.add(fin);
		return fin.getData();
	}
	
	/**
	 * ajoute les données
	 * @param data: liste d'octets représentant les données
	 * @return liste vide
	 * @throws ExceptionTaille: erreur de taille sur le nombre d'octets
	 */
	private List<String> addHTTP(List<String> data) throws ExceptionTaille{
		HTTP http = new HTTP(data);
		listTrame.add(http);
		return http.getData();
	}
	
	
	
	/**
	 * determine le segment suivant
	 * @param seg: numero de la trame
	 * @return le nom du segment suivant, "pas de segment" si ce n'est pas le cas
	 */
	private String getNextSegment(int seg) {
		if(listTrame.size()-1 - seg < 0) 
			return "pas de segment";
		if(listTrame.get(seg) instanceof Ethernet)
			return ((Ethernet)listTrame.get(seg)).getDataType();
		if(listTrame.get(seg) instanceof InternetProtocol)
			return ((InternetProtocol)listTrame.get(seg)).getProtocol();
		if(listTrame.get(seg) instanceof TCP)
			return ((TCP)listTrame.get(seg)).getPort();
		return "pas de segment";
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
