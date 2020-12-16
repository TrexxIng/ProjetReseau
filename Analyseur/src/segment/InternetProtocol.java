package segment;

import java.util.ArrayList;
import java.util.List;

import packet.ExceptionTaille;
import segment.subsegment.AllOptions;
import segment.subsegment.HeaderDatagramIP;
import segment.subsegment.Padding;

public class InternetProtocol implements ITrame {
	private List<ITrame> listIP;
	private List<String> listData;
	private int sizeIP;
	private String protocol;
	
	public InternetProtocol(List<String> listOctet) throws ExceptionTaille {
		this.sizeIP = 0;
		this.listData = listOctet;
		this.listIP = new ArrayList<>();
		
		/** header */
		HeaderDatagramIP hip = new HeaderDatagramIP(listData);
		listIP.add(hip);
		sizeIP += hip.getSize();
		listData = hip.getData();
		int sizeOption = hip.getTailleOptions();
		this.protocol = hip.getProtocol();
		
		/** options */
		AllOptions opt = new AllOptions(listData,sizeOption,"IP");
		listIP.add(opt);
		sizeIP += opt.getSize();
		listData = opt.getData();
		int padding = opt.getSizePadding();
		
		/** Padding */
		if(padding > 0) {
			Padding pad = new Padding(listData, padding);
			listIP.add(pad);
			sizeIP += pad.getSize();
			listData = pad.getData();
		}
			
	}


	@Override
	public List<String> getData() {
		return listData;
	}
	
	@Override
	public String toString() {
		return "Datagramme Internet Protocol: "+sizeIP+" octets";
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
		for(int i = 0; i<listIP.size(); i++) {
			s +="\n"+listIP.get(i).formatDisplay(tab+1);
		}
		return s;
	}

	@Override
	public int getTailleOptions() {
		return 0;
	}

	@Override
	public int getSize() {
		return sizeIP;
	}
	
	public String getProtocol() {
		return protocol;
	}
	

}
