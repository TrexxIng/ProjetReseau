package segment;

import java.util.ArrayList;
import java.util.List;

import champs.Data;
import champs.IChamps;
import exceptions.ExceptionIncoherence;
import exceptions.ExceptionSupport;

public class DataDump implements ITrame {
	private List<IChamps> data;
	private int sizeContenu;
	private boolean erreur;
	
	public DataDump(List<String> listOctet, boolean erreur) {
		this.sizeContenu = listOctet.size();
		this.data = new ArrayList<>();
		this.data.add(new Data(listOctet));
		this.erreur = erreur;
	}

	@Override
	public List<String> getData() {
		return new ArrayList<>();
	}
	
	@Override
	public String toString() {
		if(!erreur)
			return "Données: "+sizeContenu+" octets";
		return "Données (non analysées): "+sizeContenu+" octets";
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
	
	@Override
	public String nextSegment() {
		return "Rien";
	}

	@Override
	public void errorDetect() throws ExceptionSupport, ExceptionIncoherence {
		// pas d'erreur d'incoherence sévère ou de support detectable
	}

	@Override
	public String messageVerification() {
		// aucune erreur non importante ne peut etre determinée ici
		return "";
	}

}
