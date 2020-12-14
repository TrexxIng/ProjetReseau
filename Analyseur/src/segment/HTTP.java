package segment;

import java.util.List;

public class HTTP implements ITrame {
	private List<String> listData;
	
	public HTTP(List<String> listOctets) {
		this.listData = listOctets;
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

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

}
