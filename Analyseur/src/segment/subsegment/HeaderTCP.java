package segment.subsegment;

import java.util.ArrayList;
import java.util.List;

import champs.*;
import champs.adresseEtPort.Port;
import champs.longueur.LengthQuartet;
import champs.simple.AckSeqNumber;
import champs.simple.UrgPointeur;
import champs.simple.Windows;
import segment.ITrame;

public class HeaderTCP implements ITrame {
	private List<IChamps> listTCP;
	private List<String> listData;
	private int sizeOptions;
	private int sizeTCP;
	private int portSrc = -1;
	private int portDest = -1;
	
	public HeaderTCP(List<String> listOctet) {
		this.sizeTCP = 0;
		this.listData = listOctet;
		this.listTCP = new ArrayList<>();
		
		/** ajout du port source */
		List<String> list= new ArrayList<>(); 
		list.add(listData.get(0));
		listData.remove(0);
		list.add(listData.get(0));
		listData.remove(0);
		this.sizeTCP += list.size();
		listTCP.add(new Port(list,true));
		
		/** ajout du port destination */
		list= new ArrayList<>(); 
		list.add(listData.get(0));
		listData.remove(0);
		list.add(listData.get(0));
		listData.remove(0);
		this.sizeTCP += list.size();
		listTCP.add(new Port(list,false));
		
		/** ajout du Sequence Number */
		list= new ArrayList<>(); 
		for(int i = 0; i<4;i++) {
			list.add(listData.get(0));
			listData.remove(0);
		}
		this.sizeTCP += list.size();
		listTCP.add(new AckSeqNumber(list,false));
		
		/** ajout du Acknowlegement Number */
		list= new ArrayList<>(); 
		for(int i = 0; i<4;i++) {
			list.add(listData.get(0));
			listData.remove(0);
		}
		this.sizeTCP += list.size();
		listTCP.add(new AckSeqNumber(list,true));
		
		/** ajout de la taille de l'entete TCP */
		list= new ArrayList<>(); 
		list.add(listData.get(0));
		listData.remove(0);	
		listTCP.add(new LengthQuartet(list,"TCP"));
		
		/** ajout des flags */
		list.add(listData.get(0));
		listData.remove(0);
		this.sizeTCP += list.size();
		listTCP.add(new Flags(list, "TCP"));
		
		/** ajout de Windows */
		list= new ArrayList<>(); 
		list.add(listData.get(0));
		listData.remove(0);
		list.add(listData.get(0));
		listData.remove(0);
		this.sizeTCP += list.size();
		listTCP.add(new Windows(list));
		
		/** ajout de Checksum */
		list= new ArrayList<>(); 
		list.add(listData.get(0));
		listData.remove(0);
		list.add(listData.get(0));
		listData.remove(0);
		this.sizeTCP += list.size();
		listTCP.add(new Checksum(list));
		
		/** ajout de Checksum */
		list= new ArrayList<>(); 
		list.add(listData.get(0));
		listData.remove(0);
		list.add(listData.get(0));
		listData.remove(0);
		this.sizeTCP += list.size();
		listTCP.add(new UrgPointeur(list));
		
		/** calcul de la taille des options et recupere les numero des ports*/
		this.sizeOptions = ((LengthQuartet)listTCP.get(4)).getTailleIP() - 20;
		this.portSrc = ((Port)listTCP.get(0)).getPortNumber();
		this.portDest = ((Port)listTCP.get(1)).getPortNumber();
		
	}


	@Override
	public boolean checkSize() {
		if(sizeTCP == 20 && 
				(listTCP.size() == 4)) return true;
		return false;
	}

	@Override
	public List<String> getData() {
		return listData;
	}
	
	@Override
	public String toString() {
		return "Entête TCP: "+sizeTCP+" octets";
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
		for(int i = 0; i<listTCP.size(); i++) {
			s +="\n"+listTCP.get(i).formatDisplay(tab+1);
		}
		return s;
	}
	
	/**
	 * donne la taille des options
	 * @return la taille en octets
	 */
	public int getTailleOptions() {
		return sizeOptions;
	}
	
	@Override
	public int getSize() {
		return sizeTCP;
	}

	/**
	 * donne le protocole
	 * @return le protocole
	 */
	public String getPort() {
		return this.PortNumToString();
	}
	
	private String PortNumToString() {
		if(portDest == 80 || portSrc == 80) return "HTTP";
		return "Ports non listé";
	}

}
