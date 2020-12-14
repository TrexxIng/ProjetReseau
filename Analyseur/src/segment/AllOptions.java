package segment;

import java.util.ArrayList;
import java.util.List;

import offset.IOffset;

public class AllOptions implements ITrame {
	
	private List<Options> listOption;
	private List<String> listData;
	private int sizeOptions;
	private String protocol;
	
	public AllOptions(List<String> listOctet, int nbOptions, String protocol) {
		this.protocol = protocol;
		this.listData = listOctet;
		this.sizeOptions = nbOptions;
		this.listOption = new ArrayList<>();
		
		/** s'il y a des options */
		if(nbOptions > 0) {
			List<String> listOctetsOptions = new ArrayList<>();
			for(int i = 0; i < nbOptions; i++) {
				listOctetsOptions.add(listData.get(0));
				listData.remove(0);
			}
			int cpt = 0;
			while(listOctetsOptions.size() > 0) {
				listOption.add(new Options(listOctetsOptions, protocol));
				listOctetsOptions = listOption.get(cpt).getData();
				cpt++;
			}
		}
	
	}

	@Override
	public boolean checkSize() {
		boolean check = true;
		for(int i=0; i<listOption.size(); i++) {
			check = check && listOption.get(i).checkSize();
		}
		return check;
	}

	@Override
	public List<String> getData() {
		return listData;
	}
	
	@Override
	public String toString() {
		return "Options: "+sizeOptions+" octets";
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
		for(int i = 0; i<listOption.size(); i++) {
			s +="\n"+listOption.get(i).formatDisplay(tab+1);
		}
		return s;
	}

	@Override
	public int getTailleOptions() {
		return 0;
	}

	@Override
	public int getSize() {
		return sizeOptions;
	}
	

}
