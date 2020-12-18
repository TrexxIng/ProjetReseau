package analyseur;


import java.util.ArrayList;
import java.util.List;

import exceptions.ExceptionIncoherence;
import exceptions.ExceptionSupport;
import exceptions.ExceptionTaille;
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
	private List<String> data = new ArrayList<>();
	
	public Trame(List<String> listOctets) {
		this.listTrame = new ArrayList<>();
		this.listOctets = listOctets;
		this.tailleTrame = listOctets.size();
		this.data = listOctets;
	}
	
	/**
	 * Calcul des sections de la trame 
	 * @param data: liste d'octets correspondant à une trame
	 * @throws ExceptionTaille probleme de nombre d'octets
	 * @throws ExceptionSupport probleme de version supporté
	 * @throws ExceptionIncoherence probleme d'incoherence des donnees
	 */
	public void calculTrameEthernet() throws ExceptionTaille, ExceptionSupport, ExceptionIncoherence {
	
		
		String suite = "Ethernet";
		
		while(suite != "Rien") {
			
			/** ajout de la trame Ethernet */
			if(suite == "Ethernet") {
				data = this.addEthernet(data);
				suite = this.getNextSegment();
				
			} 
			/** ajout de ARP/RARP */
			else if(suite == "ARP" || suite == "RARP") {
				data = this.addARP(data,suite);
				suite = this.getNextSegment();
			}
			/** ajout de IP */
			else if(suite == "Datagramme IP") {
				data = this.addIP(data);
				suite = this.getNextSegment();
			}
			/** ajout de UDP */
			else if(suite == "UDP") {
				data = this.addUDP(data);
				suite = this.getNextSegment();
			}			
			/** ajout de TCP */
			else if(suite == "TCP") {
				data = this.addTCP(data);
				suite =  this.getNextSegment();
			}
			/** ajout de HTTP */
			else if(suite == "HTTP") {
				if(data.size() > 0) {
					data = this.addHTTP(data);
					suite =  this.getNextSegment();
				}
				else {
					suite = "Rien";
				}
			}			
			/** ajout de ICMP */
			else if(suite == "ICMP") {
				data = this.addICMP(data);
				suite =  this.getNextSegment();		
			}
			else {
				/** ajout des données s'il en reste */
				if(data.size() > 0) {
					data = this.addDonnees(data);
					suite = this.getNextSegment();
				}
				else {
					suite = "Rien";
				}
			}
			/** apres l'ajout de chaque trame, detecte des erreurs interrompant la suite */
			detectMessageError();
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
	 * @throws ExceptionTaille: probleme du nombre d'octets 
	 * @throws ExceptionIncoherence: probleme d'incoherence entre data et octets
	 */
	private List<String> addIP(List<String> data) throws ExceptionTaille, ExceptionIncoherence {
		InternetProtocol hip = new InternetProtocol(data);
		listTrame.add(hip);
		return hip.getData();
	}	
	
	/**
	 * ajoute l'entete de l'UDP
	 * @param data: liste d'octets de l'ensemble de la trame
	 * @return la liste des octets en données
	 * @throws ExceptionTaille: probleme de nombre d'octets 
	 * @throws ExceptionIncoherence: probleme d'incoherence entre data et octets
	 */
	private List<String> addUDP(List<String> data) throws ExceptionTaille, ExceptionIncoherence{
		UDP udp = new UDP(data);
		listTrame.add(udp);
		return udp.getData();
		
	}	
		
	/**
	 * ajoute l'entete du TCP
	 * @param data: liste d'octets de l'ensemble de la trame
	 * @return la liste des octets en données
	 * @throws ExceptionTaille: probleme du nombre d'octets 
	 * @throws ExceptionIncoherence 
	 */
	private List<String> addTCP(List<String> data) throws ExceptionTaille, ExceptionIncoherence{
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
		DataDump fin = new DataDump(data,false);
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
		if(http.getSize() < 26)
			throw new ExceptionTaille("message HTTP trop court, vérifiez la taille");
		return http.getData();
	}
	
	
	
	/**
	 * determine le segment suivant
	 * @return le nom du segment suivant
	 */
	private String getNextSegment() {		
		if(listTrame.size() == 0)
			return "pas de segment";
		else
			return listTrame.get(listTrame.size()-1).nextSegment();
	}
	
	private void detectMessageError() throws ExceptionSupport, ExceptionIncoherence {
		listTrame.get(listTrame.size()-1).errorDetect();
	}
	
	public void addDataNonTraduite() {
		DataDump dump = new DataDump(data,true);
		listTrame.add(dump);
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
	
	
	public String messageVerification() {
		String s = "";
		String message;
		for(int i = 0; i<listTrame.size();i++) {
			message = listTrame.get(i).messageVerification();
			if(!message.equals(""))
				s += message+"\n";
		}
		if(!s.equals(""))
			return "\n"+s;
		return s;
	}
	

	

		
}
