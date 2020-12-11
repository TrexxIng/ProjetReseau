package protocole;

import java.util.List;

import offset.IOffset;

public class DatagramIP implements ITrame {
	private List<IOffset> listEther;
	private List<String> listOctet;
	private int sizeOption;
	
	public DatagramIP(List<String> listOctet) {
		
	}

	@Override
	public List<IOffset> getListOffset() {
		// TODO Auto-generated method stub
		return listEther;
	}

}
