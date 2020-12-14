package segment;

import java.util.ArrayList;
import java.util.List;

import offset.*;

public class HeaderDatagramIP implements ITrame {
	private List<IOffset> listIP;
	private List<String> listData;
	private int sizeOptions;
	private int sizeIP = 0;
	
	public HeaderDatagramIP(List<String> listOctet) {
		this.sizeIP = 0;
		this.listData = listOctet;
		this.listIP = new ArrayList<>();
		
		/** ajout de version et IHL */
		List<String> list= new ArrayList<>(); 
		list.add(listData.get(0));
		this.sizeIP += list.size();
		listIP.add(new VersionIP(list));
		listIP.add(new LengthQuartet(list,"IP"));
		listData.remove(0);
		
		/** ajout de TOS */
		list.remove(0);
		list.add(listData.get(0));
		listData.remove(0);
		this.sizeIP += list.size();
		listIP.add(new TOS(list));
		
		/** ajout de la taille totale */
		list= new ArrayList<>(); 
		list.add(listData.get(0));
		listData.remove(0);
		list.add(listData.get(0));
		listData.remove(0);
		this.sizeIP += list.size();
		listIP.add(new TotalLength(list));
		
		/** ajout de l' Identification */
		list= new ArrayList<>(); 
		list.add(listData.get(0));
		listData.remove(0);
		list.add(listData.get(0));
		listData.remove(0);
		this.sizeIP += list.size();
		listIP.add(new Identification(list));
		
		/** ajout des Flags */
		list= new ArrayList<>(); 
		list.add(listData.get(0));
		listData.remove(0);
		list.add(listData.get(0));
		listData.remove(0);
		this.sizeIP += list.size();
		listIP.add(new Flags(list,"IP"));
		
		/** ajout de TTL */
		list= new ArrayList<>(); 
		list.add(listData.get(0));
		listData.remove(0);
		this.sizeIP += list.size();
		listIP.add(new TimeToLive(list));
		
		/** ajout de Protocol */
		list= new ArrayList<>(); 
		list.add(listData.get(0));
		listData.remove(0);
		this.sizeIP += list.size();
		listIP.add(new Protocol(list,"IP"));
		
		/** ajout du checksum */
		list= new ArrayList<>(); 
		list.add(listData.get(0));
		listData.remove(0);
		list.add(listData.get(0));
		listData.remove(0);
		this.sizeIP += list.size();
		listIP.add(new Checksum(list));
		
		
		/** ajout de l'adresse IP source */
		list= new ArrayList<>(); 
		for(int i = 0; i<4;i++) {
			list.add(listData.get(0));
			listData.remove(0);
		}
		this.sizeIP += list.size();
		listIP.add(new AdresseIP(list,"Source"));
		
		/** ajout de l'adresse IP destination */
		list= new ArrayList<>(); 
		for(int i = 0; i<4;i++) {
			list.add(listData.get(0));
			listData.remove(0);
		}
		this.sizeIP += list.size();
		listIP.add(new AdresseIP(list,"Destination"));
		
		/** calcul de la taille des options */
		this.sizeOptions = ((LengthQuartet)listIP.get(1)).getTailleIP() - 20;
		
		
		
	}


	@Override
	public boolean checkSize() {
		if((sizeIP  == 20) && 
				listIP.size() == 11) return true;
		return false;
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
	
	
	
	@Override
	public String toString() {
		return "Entête IP: "+sizeIP+" octets";
	}

	@Override
	public List<String> getData() {
		return listData;
	}

	@Override
	public String formatDisplay(int tab) {
		String stab ="";
		if(tab > 0) {
			for (int i = 0; i<tab; i++) {
				stab += "\t";
			}
		}
		String s = stab+this.toString();
		for(int i = 0; i<listIP.size(); i++) {
			s +="\n"+listIP.get(i).formatDisplay(tab+1);
		}
		return s;
	}
	
	@Override
	public int getSize() {
		return sizeIP;
	}

}
