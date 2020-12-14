package offset;

import java.util.List;

public class AckSeqNumber implements IOffset {
	private List<String> valHex;
	private boolean ackseq;
	
	/**
	 * Acknowlegment Number ou Sequence Number
	 * @param valHex liste des octets
	 * @param ackseq Acknowlegment Number = true ;  Sequence Number = false
	 */
	public AckSeqNumber(List<String> valHex, boolean ackseq) {
		this.valHex = valHex;
		this.ackseq = ackseq;
		
	}

	@Override
	public boolean checkSize() {
		if(valHex.size()%2 == 0) return true;
		return false;
	}
	
	@Override 
	public String toString() {
		String s = "Sequence";
		if(ackseq)
			s = "Acknowlegment";
		
		s = s+" Number: 0x";
				
		for(int i = 0; i<valHex.size(); i++) {
			s += valHex.get(i);
		}
		return  s;
	}

	@Override
	public String formatDisplay(int tab) {
		String s ="";
		if(tab > 0) {
			for (int i = 0; i<tab; i++) {
				s += "\t";
			}
		}
		return s+this.toString();
	}

}
