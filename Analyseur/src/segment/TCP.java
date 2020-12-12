package segment;

import java.util.ArrayList;
import java.util.List;

import offset.IOffset;

public class TCP implements ITrame {
	private List<IOffset> listIP;
	private List<String> listOctet;
	
	public TCP(List<String> listOctet) {
		this.listOctet = listOctet;
		this.listIP = new ArrayList<>();
		
		
		
	}

	@Override
	public List<IOffset> getListOffset() {
		return listIP;
	}

	@Override
	public boolean checkSize() {
	//	if(listIP.size() == ??) return true;
		return false;
	}

}
