package segment;

import java.util.ArrayList;
import java.util.List;

import offset.*;

public class UDP implements ITrame {
	private List<IOffset> listUDP;
	private List<String> listData;
	private int sizeUDP;
	
	
	public UDP(List<String> listOctet) {
		this.sizeUDP = listOctet.size();
		this.listUDP = new ArrayList<>();
		this.listData = listOctet;
		
		/** ajout du port source */
		List<String> list= new ArrayList<>(); 
		list.add(listData.get(0));
		listData.remove(0);
		list.add(listData.get(0));
		listData.remove(0);
		listUDP.add(new Port(list,true));
		
		/** ajout du port destination */
		list= new ArrayList<>(); 
		list.add(listData.get(0));
		listData.remove(0);
		list.add(listData.get(0));
		listData.remove(0);
		listUDP.add(new Port(list,false));
		
		
		/** ajout de la longueur UDP */
		list= new ArrayList<>(); 
		list.add(listData.get(0));
		listData.remove(0);
		list.add(listData.get(0));
		listData.remove(0);
		listUDP.add(new LengthUDP(list));
		
		/** ajout du checksum */
		list= new ArrayList<>(); 
		list.add(listData.get(0));
		listData.remove(0);
		list.add(listData.get(0));
		listData.remove(0);
		listUDP.add(new Checksum(list));	
		
	}


	@Override
	public boolean checkSize() {
		if((sizeUDP - listData.size()) == 20 && (listUDP.size() == 4)) return true;
		return false;
	}

	@Override
	public List<String> getData() {
		return listData;
	}
	
	@Override
	public String toString() {
		return "User Datagram Protocol, taille (avec données): "+sizeUDP+" octets";
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

}
