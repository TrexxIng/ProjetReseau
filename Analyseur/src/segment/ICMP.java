package segment;

import java.util.ArrayList;
import java.util.List;

import offset.AckSeqNumber;
import offset.AdresseIP;
import offset.AdresseMask;
import offset.Bourrage;
import offset.Checksum;
import offset.CodeICMP;
import offset.Data;
import offset.Horodatage;
import offset.IOffset;
import offset.Identification;
import offset.NextHopMTU;
import offset.TypeICMP;

public class ICMP implements ITrame {
	private List<IOffset> listICMP;
	private List<String> listData;
	private int sizeICMP;
	private String type;
	
	public ICMP(List<String> listOctet) {
		this.sizeICMP = 0;
		this.listICMP = new ArrayList<>();
		this.listData = listOctet;
		
		/** ajout du type */
		List<String> list= new ArrayList<>(); 
		list.add(listData.get(0));
		listData.remove(0);
		this.sizeICMP += list.size();
		listICMP.add(new TypeICMP(list));
		
		/** ajout du Code */
		list= new ArrayList<>(); 
		list.add(listData.get(0));
		listData.remove(0);
		this.sizeICMP += list.size();
		listICMP.add(new CodeICMP(list));
		
		/** ajout du Checksum */
		list= new ArrayList<>(); 
		list.add(listData.get(0));
		listData.remove(0);
		list.add(listData.get(0));
		listData.remove(0);
		this.sizeICMP += list.size();
		listICMP.add(new Checksum(list));
		
		/** recuperer le type pour la suite */
		this.type = ((TypeICMP)listICMP.get(0)).getType();
		
		addTrameType();
		
		if(listData.size() > 0) {
			this.sizeICMP += listData.size();
			listICMP.add(new Data(listData));
		}
		
		
	}


	@Override
	public boolean checkSize() {
		if((sizeICMP == 4) 
				&& (listICMP.size() == 3)) return true;
		return false;
	}

	@Override
	public List<String> getData() {
		return new ArrayList<>() ;
	}

	@Override
	public String toString() {
		return "Internet Control Message Protocol: "+sizeICMP+" octets";
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
	
	@Override
	public int getSize() {
		return sizeICMP;
	}
	
	public void addTrameType() {
		List<String> list;
		
		if(this.type == "Echo reply" || this.type == "Echo request" 
				|| this.type == "Timestamp" || this.type == "Timestamp reply") {
			list= new ArrayList<>(); 
			list.add(listData.get(0));
			listData.remove(0);
			list.add(listData.get(0));
			listData.remove(0);
			this.sizeICMP += list.size();
			listICMP.add(new Identification(list));
			
			list= new ArrayList<>(); 
			list.add(listData.get(0));
			listData.remove(0);
			list.add(listData.get(0));
			listData.remove(0);
			this.sizeICMP += list.size();
			listICMP.add(new AckSeqNumber(list,false));	
			
			if(this.type == "Timestamp" || this.type == "Timestamp reply") {
				list= new ArrayList<>(); 
				for(int i=0; i<4; i++) {
					list.add(listData.get(0));
					listData.remove(0);	
				}
				this.sizeICMP += list.size();
				listICMP.add(new Horodatage(list," originale"));
				
				list= new ArrayList<>(); 
				for(int i=0; i<4; i++) {
					list.add(listData.get(0));
					listData.remove(0);	
				}
				this.sizeICMP += list.size();
				listICMP.add(new Horodatage(list," reçue"));
				
				list= new ArrayList<>(); 
				for(int i=0; i<4; i++) {
					list.add(listData.get(0));
					listData.remove(0);	
				}
				this.sizeICMP += list.size();
				listICMP.add(new Horodatage(list," transmise"));
			}
			
			return;
		}
		
		if(this.type == "Destination Unreachable") {
			list= new ArrayList<>(); 
			
			list.add(listData.get(0));
			listData.remove(0);
			list.add(listData.get(0));
			listData.remove(0);
			this.sizeICMP += list.size();
			listICMP.add(new Bourrage(list));
			
			list= new ArrayList<>(); 
			list.add(listData.get(0));
			listData.remove(0);
			list.add(listData.get(0));
			listData.remove(0);
			this.sizeICMP += list.size();
			listICMP.add(new NextHopMTU(list));	
			
			return;			
		}		
		if(this.type == "Source Quench" || this.type == "Time Exceeded") {
			list= new ArrayList<>(); 
			for(int i=0; i<4; i++) {
				list.add(listData.get(0));
				listData.remove(0);	
			}
			this.sizeICMP += list.size();
			listICMP.add(new Bourrage(list));
			return;
		}		
		if(this.type == "Redirect") {
			list= new ArrayList<>(); 
			for(int i=0; i<4; i++) {
				list.add(listData.get(0));
				listData.remove(0);	
			}
			this.sizeICMP += list.size();
			listICMP.add(new AdresseIP(list, "à rediriger vers"));
			return;
		}		
		if(this.type == "Address Mask Request") {
			list= new ArrayList<>(); 
			for(int i=0; i<4; i++) {
				list.add(listData.get(0));
				listData.remove(0);	
			}
			this.sizeICMP += list.size();
			listICMP.add(new AdresseMask(list, "demandé"));
			return;
		}		
		if(this.type == "Address Mask Reply") {
			list= new ArrayList<>(); 
			for(int i=0; i<4; i++) {
				list.add(listData.get(0));
				listData.remove(0);	
			}
			this.sizeICMP += list.size();
			listICMP.add(new AdresseMask(list, "donné"));
			return;
		}
		
	}

}
