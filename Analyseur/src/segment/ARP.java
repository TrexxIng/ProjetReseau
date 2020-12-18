package segment;

import java.util.ArrayList;
import java.util.List;

import champs.IChamps;
import champs.adresseEtPort.AdresseIP;
import champs.adresseEtPort.AdresseMAC;
import champs.informations.Hardware;
import champs.informations.Operation;
import champs.longueur.Length1Bytes;
import champs.trameSuiv.Protocol;
import exceptions.ExceptionSupport;
import exceptions.ExceptionTaille;


public class ARP implements ITrame {
	
	private List<IChamps> listARP;
	private List<String> listData;
	private int sizeARP;
	private String type;
	private int hardwareL;
	private int protocoleL;
	
	public ARP(List<String> listOctet, String type) throws ExceptionTaille {
		this.type = type;
		this.sizeARP = 0;
		this.listARP = new ArrayList<>();
		this.listData = listOctet;
		
		if(listData.size() < 28) 
			throw new ExceptionTaille("pas assez d'octets pour ARP ");
		
		/** ajout du hardware */
		List<String> list= new ArrayList<>(); 
		list.add(listData.get(0));
		listData.remove(0);
		list.add(listData.get(0));
		listData.remove(0);
		this.sizeARP += list.size();
		listARP.add(new Hardware(list));
		
		/** ajout du protocol */
		list= new ArrayList<>(); 
		list.add(listData.get(0));
		listData.remove(0);
		list.add(listData.get(0));
		listData.remove(0);
		this.sizeARP += list.size();
		listARP.add(new Protocol(list,"ARP"));
		
		/** ajout taille hardware */
		list= new ArrayList<>(); 
		list.add(listData.get(0));
		listData.remove(0);
		this.sizeARP += list.size();
		listARP.add(new Length1Bytes(list, "Hardware"));
		this.hardwareL = ((Length1Bytes)listARP.get(2)).getLongueur();

		/** ajout taille protocole */
		list= new ArrayList<>(); 
		list.add(listData.get(0));
		listData.remove(0);
		this.sizeARP += list.size();
		listARP.add(new Length1Bytes(list,"Protocole"));
		this.protocoleL = ((Length1Bytes)listARP.get(3)).getLongueur();
		
		/** ajout de l'operation */
		list= new ArrayList<>(); 
		list.add(listData.get(0));
		listData.remove(0);
		list.add(listData.get(0));
		listData.remove(0);
		this.sizeARP += list.size();
		listARP.add(new Operation(list));
		
		/** ajout adresseMac emetteur */
		list = new ArrayList<>(); 
		for(int i = 0; i < 6; i++) {
			list.add(listData.get(0));
			listData.remove(0);
		}
		this.sizeARP += list.size();
		listARP.add(new AdresseMAC(list,"Emetteur"));
		
		/** ajout de l'adresse IP emetteur */
		list= new ArrayList<>(); 
		for(int i = 0; i<4;i++) {
			list.add(listData.get(0));
			listData.remove(0);
		}
		this.sizeARP += list.size();
		listARP.add(new AdresseIP(list,"Emetteur"));
		
		
		/** ajout adresseMac destinataire */
		list = new ArrayList<>(); 
		for(int i = 0; i < 6; i++) {
			list.add(listData.get(0));
			listData.remove(0);
		}
		this.sizeARP += list.size();
		listARP.add(new AdresseMAC(list,"Destinataire"));
		
		/** ajout de l'adresse IP destinaire */
		list= new ArrayList<>(); 
		for(int i = 0; i<4;i++) {
			list.add(listData.get(0));
			listData.remove(0);
		}
		this.sizeARP += list.size();
		listARP.add(new AdresseIP(list,"Destinataire"));
		
	}

	@Override
	public List<String> getData() {
		return listData;
	}
	
	@Override
	public String toString() {
		String s = "Reverse Address Resolution Protocole";
		if(type == "ARP")
			s = "Address Resolution Protocole";
		return s+": "+sizeARP+" octets";
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
		for(int i = 0; i<listARP.size(); i++) {
			s +="\n"+listARP.get(i).formatDisplay(tab+1);
		}
		return s;
	}
	

	@Override
	public int getTailleOptions() {
		return 0;
	}


	@Override
	public int getSize() {
		return sizeARP;
	}
	
	@Override
	public String nextSegment() {
		return "Data";
	}

	@Override
	public void errorDetect() throws ExceptionSupport {
		// pas d'erreur d'incoherence sévère ou de support detectable
	}

	@Override
	public String messageVerification() {
		// adresse hardware = 6, adresse protocole 4
		if(hardwareL != 6 && protocoleL != 4)
			return "ARP: problème sur la longueur de l'adresse protocole et l'adresse hardware (en IPv4, doit etre égale à 6 et 4 respectivement)";
		if(hardwareL != 6)
			return "ARP: problème sur la longueur de l'adresse hardware (en IPv4, doit etre égale à 6)";
		if (protocoleL != 4)
			return "ARP: problème sur la longueur de l'adresse protocole (en IPv4, doit etre égale à 4)";
		return "";
	}



}
