package segment;

import java.util.ArrayList;
import java.util.List;

import champs.Flags;
import champs.IChamps;
import champs.informations.Hardware;
import champs.informations.Identification;
import champs.nbQuestRep.NbAdditional;
import champs.nbQuestRep.NbAuthority;
import champs.nbQuestRep.NbQuestions;
import champs.nbQuestRep.NbReponsesRR;
import champs.sectionDNS.AllRequeteDNS;
import champs.sectionDNS.OtherDNS;
import exceptions.ExceptionIncoherence;
import exceptions.ExceptionSupport;
import exceptions.ExceptionTaille;

public class DNS implements ITrame {
	
	private List<IChamps> listDNS;
	private List<String> listData;
	private int sizeDNS;
	private int requetes;
	private int reponses;
	private int additional;
	private int authority;
	
	public DNS(List<String> listOctet) throws ExceptionTaille {
		this.sizeDNS = 0;
		this.listDNS = new ArrayList<>();
		this.listData = listOctet;
		
		if(listData.size() < 12) 
			throw new ExceptionTaille("pas assez d'octets pour DNS ");
		
		List<String> list= new ArrayList<>();
		
		/** identification */
		list.add(listData.get(0));
		listData.remove(0);
		list.add(listData.get(0));
		listData.remove(0);
		this.sizeDNS += list.size();
		listDNS.add(new Identification(list));
		
		/** flags */
		list= new ArrayList<>();
		list.add(listData.get(0));
		listData.remove(0);
		list.add(listData.get(0));
		listData.remove(0);
		this.sizeDNS += list.size();
		listDNS.add(new Flags(list, "DNS"));
		
		/** nombre de requetes */
		list= new ArrayList<>();
		list.add(listData.get(0));
		listData.remove(0);
		list.add(listData.get(0));
		listData.remove(0);
		this.sizeDNS += list.size();
		listDNS.add(new NbQuestions(list));
		this.requetes = ((NbQuestions)listDNS.get(2)).getNb();
		
		/** nombre de reponses */
		list= new ArrayList<>();
		list.add(listData.get(0));
		listData.remove(0);
		list.add(listData.get(0));
		listData.remove(0);
		this.sizeDNS += list.size();
		listDNS.add(new NbReponsesRR(list));
		this.reponses = ((NbReponsesRR)listDNS.get(3)).getNb();
		
		/** nombre de authority */
		list= new ArrayList<>();
		list.add(listData.get(0));
		listData.remove(0);
		list.add(listData.get(0));
		listData.remove(0);
		this.sizeDNS += list.size();
		listDNS.add(new NbAuthority(list));
		this.authority = ((NbAuthority)listDNS.get(4)).getNb();
		
		/** nombre de additional */
		list= new ArrayList<>();
		list.add(listData.get(0));
		listData.remove(0);
		list.add(listData.get(0));
		listData.remove(0);
		this.sizeDNS += list.size();
		listDNS.add(new NbAdditional(list));
		this.additional = ((NbAdditional)listDNS.get(5)).getNb();
		
		/** requetes */
		if(requetes > 0) {
			AllRequeteDNS req = new AllRequeteDNS(listData,requetes);
			listData = req.getData();
			this.sizeDNS += req.getSize();
			listDNS.add(req);
		}
		
		/** reponses */
		if(reponses > 0) {
			OtherDNS resp = new OtherDNS(listData,reponses,"Reponse");
			listData = resp.getData();
			listDNS.add(resp);
			this.sizeDNS += resp.getSize();
		}
		
		/** authorité et additional */
		if(authority > 0 || additional > 0) {
			OtherDNS aa = new OtherDNS(listData,additional,"other");
			listData = aa.getData();
			listDNS.add(aa);
			this.sizeDNS += aa.getSize();
		}	

		
		
	}

	@Override
	public List<String> getData() {
		return new ArrayList<>();
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
		for(int i = 0; i<listDNS.size(); i++) {
			s +="\n"+listDNS.get(i).formatDisplay(tab+1);
		}
		return s;
	}

	@Override
	public int getTailleOptions() {
		return 0;
	}

	@Override
	public int getSize() {
		return sizeDNS;
	}
	
	@Override
	public String toString() {
		String s = "Domain Name System";
		return s+": "+sizeDNS+" octets";
	}

	@Override
	public String nextSegment() {
		return "Data";
	}

	@Override
	public void errorDetect() throws ExceptionSupport, ExceptionIncoherence {
		// TODO Auto-generated method stub
	}

	@Override
	public String messageVerification() {
		
		return "";
	}

}
