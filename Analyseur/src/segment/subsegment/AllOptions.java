package segment.subsegment;

import java.util.ArrayList;
import java.util.List;

import packet.ExceptionTaille;
import segment.ITrame;

public class AllOptions implements ITrame {
	
	private List<Options> listOption;
	private List<String> listData;
	private int sizeOptions;
	private int padding;
	
	public AllOptions(List<String> listOctet, int nbOptions, String protocol) throws ExceptionTaille {
		this.listData = listOctet;
		this.listOption = new ArrayList<>();
		
		if(listData.size() < nbOptions) 
			throw new ExceptionTaille("pas assez d'octets pour les options "+protocol);
		
		/** s'il y a des options */
		if(nbOptions > 0) {
			List<String> listOctetsOptions = new ArrayList<>();
			for(int i = 0; i < nbOptions; i++) {
				listOctetsOptions.add(listData.get(0));
				listData.remove(0);
			}
			int cpt = 0;
			int size = 0;
			boolean stop = false;
			while((listOctetsOptions.size() > 0) && (!stop)) {
				listOption.add(new Options(listOctetsOptions, protocol));
				listOctetsOptions = listOption.get(cpt).getData();
				size += listOption.get(cpt).getSize();
				stop = listOption.get(cpt).stop();
				cpt++;
			}
			
			this.sizeOptions = size;
			
			this.padding = nbOptions - size;

		}
	
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
	
	public int getSizePadding() {
		return padding;
	}

}
