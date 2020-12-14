package segment;

import java.util.ArrayList;
import java.util.List;

import champs.AdresseIP;
import champs.AdresseMAC;
import champs.Hardware;
import champs.IChamps;
import champs.Length1Bytes;
import champs.Operation;
import champs.Protocol;


public class ARP implements ITrame {
	
	private List<IChamps> listARP;
	private List<String> listData;
	private int sizeARP;
	private String type;
	
	public ARP(List<String> listOctet, String type) {
		this.type = type;
		this.sizeARP = 0;
		this.listARP = new ArrayList<>();
		this.listData = listOctet;
		
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

		/** ajout taille protocole */
		list= new ArrayList<>(); 
		list.add(listData.get(0));
		listData.remove(0);
		this.sizeARP += list.size();
		listARP.add(new Length1Bytes(list,"Protocole"));
		
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
	public boolean checkSize() {
		if((listARP.size() == 9) && (sizeARP == 28)) return true;
		return false;
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

}
