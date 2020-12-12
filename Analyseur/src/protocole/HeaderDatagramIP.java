package protocole;

import java.util.ArrayList;
import java.util.List;

import offset.*;

public class HeaderDatagramIP implements ITrame {
	private List<IOffset> listIP;
	private List<String> listOctet;
	private int sizeOptions;
	
	public HeaderDatagramIP(List<String> listOctet) {
		this.listOctet = listOctet;
		this.listIP = new ArrayList<>();
		
		/** ajout de version et IHL */
		List<String> list= new ArrayList<>(); 
		list.add(listOctet.get(0));
		listIP.add(new VersionIP(list));
		listIP.add(new IHL(list));
		
		/** ajout de TOS */
		list.remove(0);
		list.add(listOctet.get(1));
		listIP.add(new TOS(list));
		
		/** ajout de la taille totale */
		list= new ArrayList<>(); 
		list.add(listOctet.get(2));
		list.add(listOctet.get(3));
		listIP.add(new TotalLength(list));
		
		/** ajout de l' Identification */
		list= new ArrayList<>(); 
		list.add(listOctet.get(4));
		list.add(listOctet.get(5));
		listIP.add(new Identification(list));
		
		/** ajout des Flags */
		list= new ArrayList<>(); 
		list.add(listOctet.get(6));
		list.add(listOctet.get(7));
		listIP.add(new Flags(list));
		
		/** ajout de TTL */
		list= new ArrayList<>(); 
		list.add(listOctet.get(8));
		listIP.add(new TimeToLive(list));
		
		/** ajout de Protocol */
		list= new ArrayList<>(); 
		list.add(listOctet.get(9));
		listIP.add(new Protocol(list));
		
		/** ajout du checksum */
		list= new ArrayList<>(); 
		list.add(listOctet.get(10));
		list.add(listOctet.get(11));
		listIP.add(new Checksum(list));
		
		
		/** ajout de l'adresse IP source */
		list= new ArrayList<>(); 
		for(int i = 12; i<16;i++)
			list.add(listOctet.get(i));
		listIP.add(new AdresseIP(list,true));
		
		/** ajout de l'adresse IP destination */
		list= new ArrayList<>(); 
		for(int i = 17; i<20;i++)
			list.add(listOctet.get(i));
		listIP.add(new AdresseIP(list,false));
		
		/** calcul de la taille des options */
		setTailleOptions();
		
	}

	@Override
	public List<IOffset> getListOffset() {
		return listIP;
	}

	@Override
	public boolean checkSize() {
		if(listIP.size() == 20) return true;
		return false;
	}
	
	/**
	 * donne la taille du header IP
	 * @return la taille en octets
	 */
	public int getTailleIP() {
		return ((IHL)listIP.get(1)).getTailleIP();
	}
	
	/**
	 * donne le protocole se trouvant dans les data de IP
	 * @return le protocole en type String
	 */
	public String getProtocol() {
		return ((Protocol)listIP.get(7)).getProtocol();
	}
	
	/**
	 * donne la taille des options
	 * @return la taille en octets
	 */
	public int getTailleOptions() {
		return sizeOptions;
	}
	
	/**
	 * determine la taille des options
	 */
	private void setTailleOptions() {
		this.sizeOptions = getTailleIP() - listOctet.size(); 
	}
	
	@Override
	public String toString() {
		String s = "Entete du Datagramme IP: "+listOctet.size()+" octets";
		for(int i = 0; i<listIP.size(); i++) {
			s +="\n\t"+listIP.get(i).toString();
		}
		return s;
	}

}
