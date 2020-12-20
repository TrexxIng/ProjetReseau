package champs;

import java.util.ArrayList;
import java.util.List;

import flags.*;
import flags.flagsIP.*;
import flags.flagsTCP.*;
import flags.flagsDNS.*;

public class Flags implements IChamps {
	private String hex;
	private int valDec;
	private List<IFlags> flags;
	private String type;
	private String typeFlags = "";
	private int reserved;
	
	
	public Flags(List<String> valHex, String type) {
		this.type = type;
		List<String> valbits;
		flags = new ArrayList<>();
		if(this.type == "IP") {
			
			/** 16 bits */
			hex= valHex.get(0)+valHex.get(1);
			valDec = Integer.parseInt(hex, 16);
			valbits = decToBinary(valDec,16);
			
			/** ajout d'une liste de flags  */
			flags.add(new Reserved(valbits,this.type));
			flags.add(new DF(valbits));
			flags.add(new MF(valbits));
			flags.add(new FragOffset(valbits));
			
			/** pour verifier si les bits reservés sont à 0 */
			this.reserved = flags.get(0).getValue();
			
		}
		else if(this.type == "TCP"){
			
			/**12 bits */
			hex = valHex.get(0).charAt(1)+valHex.get(1);
			valDec = Integer.parseInt(hex, 16);
			valbits = decToBinary(valDec,16);
			
			/** ajout d'une liste de flags  */	
			flags.add(new Reserved(valbits,this.type));
			flags.add(new URG(valbits));
			flags.add(new ACK(valbits));
			flags.add(new PSH(valbits));
			flags.add(new RST(valbits));
			flags.add(new SYN(valbits));
			flags.add(new FIN(valbits));
			
			/** type pour affichage */
			for(int i = 1; i<flags.size(); i++) {
				if(flags.get(i).getValue() == 1) {
					if(!typeFlags.equals(""))
						this.typeFlags += ",";
					this.typeFlags += flags.get(i).getType();
				}
			}
			
			/** pour verifier si les bits reservés sont à 0 */
			this.reserved = flags.get(0).getValue();
			
		}
		else if(this.type == "DNS") {
			
			/** 16 bits */
			hex= valHex.get(0)+valHex.get(1);
			valDec = Integer.parseInt(hex, 16);
			valbits = decToBinary(valDec,16);
			
			/** ajout d'une liste de flags  */	
			flags.add(new QR(valbits));
			flags.add(new Opcode(valbits));
			flags.add(new AA(valbits));
			flags.add(new TC(valbits));
			flags.add(new RD(valbits));		
			flags.add(new RA(valbits));	
			flags.add(new Reserved(valbits,"DNS"));
			flags.add(new ReponseAuthent(valbits));
			flags.add(new Check(valbits));
			flags.add(new RCode(valbits));
			
			/** type pour affichage */
			this.typeFlags += flags.get(1).getType();
			this.typeFlags += ", "+flags.get(9).getType();
			
			/** pour verifier si les bits reservés sont à 0 */
			this.reserved = flags.get(6).getValue();
			
		}
		

	}

	
	/**
	 * transforme un decimal en une liste de bits
	 * @param num le chiffre decimal
	 * @param nbBits le nombre de bits dans la liste
	 * @return la liste de bits
	 */
	private List<String> decToBinary(int num, int nbBits){
		List<String> decToBits = new ArrayList<>();
		String bits = Integer.toBinaryString(num);
		int len = bits.length();
		if((nbBits - len) != 0) {
			for(int i = 0; i<(nbBits - len); i++) {
				decToBits.add("0");
			}
		}
		for(int i = 0; i<len; i++) {
			decToBits.add(""+bits.charAt(i));
		}
		return decToBits;
	}


	
	@Override 
	public String toString() {
		if(this.type == "IP")
			return "Flags: 0x"+hex+" ("+valDec+")";
		String s = "Flags: 0x"+hex;
		if(typeFlags != "")
			s += " ("+typeFlags+")";
		return s;
		
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
		for(int i = 0; i<flags.size(); i++) {
			s +="\n"+flags.get(i).formatDisplay(tab+1);
		}
		return s;
	}
	
	/** renvoie la valeur du/des bits reservés */
	public int reserved() {
		return reserved;
	}

}
