package segment;

import java.util.ArrayList;
import java.util.List;

import exceptions.ExceptionIncoherence;
import exceptions.ExceptionSupport;
import exceptions.ExceptionTaille;
import segment.subsegment.AllOptions;
import segment.subsegment.HeaderTCP;
import segment.subsegment.Padding;

public class TCP implements ITrame {
	private List<ITrame> listTCP;
	private List<String> listData;
	private int sizeTCP;
	private String port;
	
	public TCP(List<String> listOctet) throws ExceptionTaille, ExceptionIncoherence {
		this.sizeTCP = 0;
		this.listData = listOctet;
		this.listTCP = new ArrayList<>();
		
		/** header */
		HeaderTCP hip = new HeaderTCP(listData);
		listTCP.add(hip);
		sizeTCP += hip.getSize();
		listData = hip.getData();
		int sizeOption = hip.getTailleOptions();
		this.port = hip.getPort();
		
		/** options */
		AllOptions opt = new AllOptions(listData,sizeOption,"TCP");
		listTCP.add(opt);
		sizeTCP += opt.getSize();
		listData = opt.getData();
		int padding = opt.getSizePadding();
		
		/** Padding */
		if(padding > 0) {
			Padding pad = new Padding(listData, padding);
			listTCP.add(pad);
			sizeTCP += pad.getSize();
			listData = pad.getData();
		}
			
	}



	@Override
	public List<String> getData() {
		return listData;
	}
	
	@Override
	public String toString() {
		return "Transmission Control Protocol: "+sizeTCP+" octets";
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
		for(int i = 0; i<listTCP.size(); i++) {
			s +="\n"+listTCP.get(i).formatDisplay(tab+1);
		}
		return s;
	}

	@Override
	public int getTailleOptions() {
		return 0;
	}

	@Override
	public int getSize() {
		return sizeTCP;
	}
	
	@Override
	public String nextSegment() {
		return port;
	}

	@Override
	public void errorDetect() throws ExceptionSupport, ExceptionIncoherence {
		for(int i =0; i<listTCP.size(); i++)
			listTCP.get(i).errorDetect();	
	}
	
	@Override
	public String messageVerification() {
		return listTCP.get(0).messageVerification();
	}

}
