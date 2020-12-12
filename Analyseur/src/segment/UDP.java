package segment;

import java.util.ArrayList;
import java.util.List;

import offset.AdresseIP;
import offset.IOffset;

public class UDP implements ITrame {
	private List<IOffset> listUDP;
	private List<String> listOctet;
	private List<IOffset> listHeaderUDP;
	
	public UDP(List<String> listOctet) {
		this.listOctet = listOctet;
		this.listUDP = new ArrayList<>();
		this.listHeaderUDP = new ArrayList<>();
		
	}

	@Override
	public List<IOffset> getListOffset() {
		return listUDP;
	}

	@Override
	public boolean checkSize() {
		if(listOctet.size() == 20) return true;
		return false;
	}

}
