package segment;

import java.util.ArrayList;
import java.util.List;

import offset.Checksum;
import offset.CodeICMP;
import offset.IOffset;
import offset.TypeICMP;

public class ICMP implements ITrame {
	private List<IOffset> listICMP;
	private List<String> listData;
	private int sizeICMP;
	
	public ICMP(List<String> listOctet) {
		this.sizeICMP = listOctet.size();
		this.listICMP = new ArrayList<>();
		this.listData = listOctet;
		
		/** ajout du type */
		List<String> list= new ArrayList<>(); 
		list.add(listData.get(0));
		listData.remove(0);
		listICMP.add(new TypeICMP(list));
		
		/** ajout du Code */
		list= new ArrayList<>(); 
		list.add(listData.get(0));
		listData.remove(0);
		listICMP.add(new CodeICMP(list));
		
		/** ajout du Checksum */
		list= new ArrayList<>(); 
		list.add(listData.get(0));
		listData.remove(0);
		list.add(listData.get(0));
		listData.remove(0);
		listICMP.add(new Checksum(list));
	}


	@Override
	public boolean checkSize() {
		if((sizeICMP - listData.size() == 4) 
				&& (listICMP.size() == 3)) return true;
		return false;
	}

	@Override
	public List<String> getData() {
		return listData;
	}

	@Override
	public String toString() {
		return "Internet Control Message Protocol, taille: "+sizeICMP+" octets";
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
		for(int i = 0; i<listICMP.size(); i++) {
			s +="\n"+listICMP.get(i).formatDisplay(tab+1);
		}
		return s;
	}

	@Override
	public int getTailleOptions() {
		return 0;
	}

}
