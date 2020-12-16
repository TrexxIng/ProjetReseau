package segment.subsegment;

import java.util.ArrayList;
import java.util.List;

import champs.IChamps;
import champs.options.Bourrage;
import segment.ITrame;

public class Padding implements ITrame {

	private List<IChamps> listBourrage;
	private List<String> listData;
	private int sizeBourrage = 0;
	
	public Padding(List<String> listOctets, int size) {
		this.listData = listOctets;
		listBourrage = new ArrayList<>();
		
		List<String> list = new ArrayList<>();
		for(int i = 0; i<size; i++) {
			list.add(listData.get(0));
			listData.remove(0);
		}
		sizeBourrage += list.size();
		listBourrage.add(new Bourrage(list));	
	}

	@Override
	public List<String> getData() {
		return listData;
	}
	
	@Override
	public String toString() {
		return "Padding: "+sizeBourrage+" octets";
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
		for(int i = 0; i<listBourrage.size(); i++) {
			s +="\n"+listBourrage.get(i).formatDisplay(tab+1);
		}
		return s;
	}

	@Override
	public int getTailleOptions() {
		return 0;
	}

	@Override
	public int getSize() {
		return sizeBourrage;
	}

}
