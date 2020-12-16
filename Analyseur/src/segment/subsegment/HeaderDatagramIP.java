package segment.subsegment;

import java.util.ArrayList;
import java.util.List;

import champs.*;
import champs.adresseEtPort.AdresseIP;
import champs.longueur.LengthQuartet;
import champs.longueur.TotalLength;
import champs.simple.Identification;
import champs.simple.TOS;
import champs.simple.TimeToLive;
import champs.simple.VersionIP;
import champs.trameSuiv.Protocol;
import exceptions.ExceptionIncoherence;
import exceptions.ExceptionSupport;
import exceptions.ExceptionTaille;
import segment.ITrame;

public class HeaderDatagramIP implements ITrame {
	private List<IChamps> listIP;
	private List<String> listData;
	private int sizeOptions;
	private int sizeIP = 0;
	private int tailleCoherente = 0;
	private int taillePaquet;
	private int reserved;
	
	
	public HeaderDatagramIP(List<String> listOctet) throws ExceptionTaille, ExceptionIncoherence {
		this.sizeIP = 0;
		this.taillePaquet = listOctet.size();
		this.listData = listOctet;
		this.listIP = new ArrayList<>();
		
		if(listData.size() < 20) 
			throw new ExceptionTaille("pas assez d'octets pour le datagramme IP");
		
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
		
		/** on voudra verifier que la taille du paquet correspond à la taille indiqué */
		this.tailleCoherente = ((TotalLength)listIP.get(3)).getTailleTotale();
		
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
		this.reserved = ((Flags)listIP.get(5)).reserved();
		
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
		
		
		
		/** on s'assure que la taille du header indiqué est bien supérieure à 20 */
		int taille = ((LengthQuartet)listIP.get(1)).getTailleIP();
		if(taille < 20)
			throw new ExceptionIncoherence("Taille de l'entête IP indiquée en données ("+taille+") inférieur à 20 octets");
		
		/** calcul de la taille des options */
		this.sizeOptions = ((LengthQuartet)listIP.get(1)).getTailleIP() - 20;
		
		
		
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
	
	@Override
	public String nextSegment() {
		return null;
	}




	@Override
	public void errorDetect() throws ExceptionSupport, ExceptionIncoherence {
		if(tailleCoherente != taillePaquet)
			throw new  ExceptionIncoherence("taille totale indiquée en données ("+tailleCoherente+" octets) différente de la taille du paquet ("+
			taillePaquet+" octets)");
	}




	@Override
	public String messageVerification() {
		if(reserved != 0)
			return "Datagramme IP: le bit réservé des flags n'est pas à zéro";
		return "";
	}

}
