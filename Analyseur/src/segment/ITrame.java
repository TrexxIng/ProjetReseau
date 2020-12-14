package segment;

import java.util.List;

import champs.IChamps;

public interface ITrame {
	boolean checkSize();
	List<String> getData();
	String formatDisplay(int tab);
	int getTailleOptions();
	int getSize();
}
