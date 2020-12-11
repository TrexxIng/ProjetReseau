package protocole;

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
		List<String> listmacD= new ArrayList<>(); 
		for(int i = 0; i < 6; i++) {
			listmacD.add(listOctet.get(i));
		}
		
		listEther.add(new AdresseMAC(listmacD,false));
		List<String> listmacS= new ArrayList<>(); 
		for(int i = 6; i < 12; i++) {
			listmacS.add(listOctet.get(i));
		}
		listEther.add(new AdresseMAC(listmacS,true));
		List<String> listT= new ArrayList<>(); 
		listT.add(listOctet.get(12));
		listT.add(listOctet.get(13));
		listEther.add(new TypeEther(listT));
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
	

}
