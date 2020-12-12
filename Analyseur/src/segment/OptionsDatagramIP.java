package segment;

import java.util.List;

import offset.IOffset;

public class OptionsDatagramIP implements ITrame {
	private List<IOffset> listOptIP;
	private List<String> listOctet;
	private int size;

	public OptionsDatagramIP(List<String> listOctet, int size) {
		this.listOctet = listOctet;
		this.size = size;
	}

	@Override
	public List<IOffset> getListOffset() {
		return listOptIP;
	}

	@Override
	public boolean checkSize() {
		if(listOctet.size() == size) return true;
		return false;
	}

}
