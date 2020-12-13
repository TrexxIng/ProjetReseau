package segment;

import java.util.ArrayList;
import java.util.List;

import offset.AdresseIP;
import offset.AdresseMAC;
import offset.Hardware;
import offset.IOffset;
import offset.Length1Bytes;
import offset.Operation;
import offset.Protocol;


public class ARP implements ITrame {
	
	private List<IOffset> listARP;
	private List<String> listData;
	private int sizeARP;
	
	public ARP(List<String> listOctet) {
		this.sizeARP = listOctet.size();
		this.listARP = new ArrayList<>();
		this.listData = listOctet;
		
		/** ajout du hardware */
		List<String> list= new ArrayList<>(); 
		list.add(listData.get(0));
		listData.remove(0);
		list.add(listData.get(0));
		listData.remove(0);
		listARP.add(new Hardware(list));
		
		/** ajout du protocol */
		list= new ArrayList<>(); 
		list.add(listData.get(0));
		listData.remove(0);
		list.add(listData.get(0));
		listData.remove(0);
		listARP.add(new Protocol(list,"ARP"));
		
		/** ajout taille hardware */
		list= new ArrayList<>(); 
		list.add(listData.get(0));
		listData.remove(0);
		listARP.add(new Length1Bytes(list, "Hardware"));

		/** ajout taille protocole */
		list= new ArrayList<>(); 
		list.add(listData.get(0));
		listData.remove(0);
		listARP.add(new Length1Bytes(list,"Protocole"));
		
		/** ajout de l'operation */
		list= new ArrayList<>(); 
		list.add(listData.get(0));
		listData.remove(0);
		list.add(listData.get(0));
		listData.remove(0);
		listARP.add(new Operation(list));
		
		/** ajout adresseMac emetteur */
		list = new ArrayList<>(); 
		for(int i = 0; i < 6; i++) {
			list.add(listData.get(0));
			listData.remove(0);
		}
		listARP.add(new AdresseMAC(list,"Emetteur"));
		
		/** ajout de l'adresse IP emetteur */
		list= new ArrayList<>(); 
		for(int i = 0; i<4;i++) {
			list.add(listData.get(0));
			listData.remove(0);
		}
		listARP.add(new AdresseIP(list,"Emetteur"));
		
		
		/** ajout adresseMac destinataire */
		list = new ArrayList<>(); 
		for(int i = 0; i < 6; i++) {
			list.add(listData.get(0));
			listData.remove(0);
		}
		listARP.add(new AdresseMAC(list,"Destinataire"));
		
		/** ajout de l'adresse IP destinaire */
		list= new ArrayList<>(); 
		for(int i = 0; i<4;i++) {
			list.add(listData.get(0));
			listData.remove(0);
		}
		listARP.add(new AdresseIP(list,"Destinataire"));
		
	}


	@Override
	public boolean checkSize() {
		if((listARP.size() == 9) && (sizeARP - listData.size() == 28)) return true;
		return false;
	}

	@Override
	public List<String> getData() {
		return listData;
	}
	
	@Override
	public String toString() {
		return "(Reverse) Adress Resolution Protocole, taille: "+sizeARP+" octets";
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

}
