package segment;

import java.util.ArrayList;
import java.util.List;

import offset.AdresseMAC;
import offset.IOffset;
import offset.TypeEther;

public class Ethernet implements ITrame {
	private List<IOffset> listEther;
	private List<String> listOctet;
	
	
	public Ethernet(List<String> listOctet) {
		this.listOctet = listOctet;
		this.listEther = new ArrayList<>();
		List<String> list= new ArrayList<>(); 
		
		/** ajout adresseMac destination */
		for(int i = 0; i < 6; i++) {
			list.add(listOctet.get(i));
		}		
		listEther.add(new AdresseMAC(list,false));
		
		/** ajout adresseMac source */
		list = new ArrayList<>(); 
		for(int i = 6; i < 12; i++) {
			list.add(listOctet.get(i));
		}
		listEther.add(new AdresseMAC(list,true));
		
		/** ajout Type Ethernet */
		list= new ArrayList<>(); 
		list.add(listOctet.get(12));
		list.add(listOctet.get(13));
		listEther.add(new TypeEther(list));
	}

	@Override
	public List<IOffset> getListOffset() {
		return listEther;
	}
	
	public String dataType() {
		return ((TypeEther)listEther.get(2)).getType();
		
	}
	
	@Override
	public String toString() {
		String s = "Trame Ethernet: "+listOctet.size()+" octets";
		for(int i = 0; i<listEther.size(); i++) {
			s +="\n\t"+listEther.get(i).toString();
		}
		return s;
	}

	@Override
	public boolean checkSize() {
		if(listOctet.size() == 14) return true;
		return false;
	}
	

}
