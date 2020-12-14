package segment;

import java.util.ArrayList;
import java.util.List;

import champs.Data;
import champs.IChamps;

public class Contenu implements ITrame {
	private List<IChamps> data;
	private int sizeContenu;
	
	public Contenu(List<String> listOctet) {
		this.sizeContenu = listOctet.size();
		this.data = new ArrayList<>();
		this.data.add(new Data(listOctet));
	}

	@Override
	public boolean checkSize() {
		return true;
	}

	@Override
	public List<String> getData() {
		return new ArrayList<>();
	}
	
	@Override
	public String toString() {
		return "Données: "+sizeContenu+" octets";
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
		for(int i = 0; i<data.size(); i++) {
			s +="\n"+data.get(i).formatDisplay(tab+1);
		}
		return s;
	}

	@Override
	public int getTailleOptions() {
		return 0;
	}
	
	@Override
	public int getSize() {
		return sizeContenu;
	}

}
