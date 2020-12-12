package segment;

import java.util.List;

import offset.IOffset;

public class ARP implements ITrame {
	
	private List<IOffset> listARP;
	private List<String> listData;
	private int sizeARP;
	
	public ARP() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<IOffset> getListOffset() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkSize() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<String> getData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String formatDisplay(int tab) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTailleOptions() {
		// TODO Auto-generated method stub
		return 0;
	}

}
