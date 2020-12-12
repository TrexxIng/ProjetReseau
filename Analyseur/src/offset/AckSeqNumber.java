package offset;

import java.util.List;

public class AckSeqNumber implements IOffset {
	private List<String> valHex;
	private int number;
	private boolean ackseq;
	
	/**
	 * Acknowlegment Number ou Sequence Number
	 * @param valHex liste des octets
	 * @param ackseq Acknowlegment Number = true ;  Sequence Number = false
	 */
	public AckSeqNumber(List<String> valHex, boolean ackseq) {
		this.valHex = valHex;
		this.ackseq = ackseq;
		this.number = Integer.parseInt(
				(valHex.get(0)+valHex.get(1)+valHex.get(2)+valHex.get(3)),16);
	}

	@Override
	public boolean checkSize() {
		if(valHex.size() == 4) return true;
		return false;
	}
	
	@Override 
	public String toString() {
		String s = "Sequence";
		if(ackseq)
			s = "Acknowlegment";
		return  s+" Number: "+number+" (0x"+
					valHex.get(0)+valHex.get(1)+valHex.get(2)+valHex.get(3)+")";
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
