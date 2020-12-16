package segment;

import java.util.ArrayList;
import java.util.List;

import champs.*;
import champs.adresseEtPort.Port;
import champs.longueur.LengthUDP;
import packet.ExceptionTaille;

public class UDP implements ITrame {
	private List<IChamps> listUDP;
	private List<String> listData;
	private int sizeUDP;
	
	
	public UDP(List<String> listOctet) throws ExceptionTaille {
		this.sizeUDP = 0;
		this.listUDP = new ArrayList<>();
		this.listData = listOctet;
		
		if(listData.size() < 8) 
			throw new ExceptionTaille("pas assez d'octets pour UDP");
		
		/** ajout du port source */
		List<String> list= new ArrayList<>(); 
		list.add(listData.get(0));
		listData.remove(0);
		list.add(listData.get(0));
		listData.remove(0);
		this.sizeUDP += list.size();
		listUDP.add(new Port(list,true));
		
		/** ajout du port destination */
		list= new ArrayList<>(); 
		list.add(listData.get(0));
		listData.remove(0);
		list.add(listData.get(0));
		listData.remove(0);
		this.sizeUDP += list.size();
		listUDP.add(new Port(list,false));
		
		
		/** ajout de la longueur UDP */
		list= new ArrayList<>(); 
		list.add(listData.get(0));
		listData.remove(0);
		list.add(listData.get(0));
		listData.remove(0);
		this.sizeUDP += list.size();
		listUDP.add(new LengthUDP(list));
		
		/** ajout du checksum */
		list= new ArrayList<>(); 
		list.add(listData.get(0));
		listData.remove(0);
		list.add(listData.get(0));
		listData.remove(0);
		this.sizeUDP += list.size();
		listUDP.add(new Checksum(list));	
		
	}



	@Override
	public List<String> getData() {
		return listData;
	}
	
	@Override
	public String toString() {
		return "User Datagram Protocol: "+sizeUDP+" octets";
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
		for(int i = 0; i<listUDP.size(); i++) {
			s +="\n"+listUDP.get(i).formatDisplay(tab+1);
		}
		return s;
	}

	@Override
	public int getTailleOptions() {
		return 0;
	}
	
	@Override
	public int getSize() {
		return sizeUDP;
	}


}
