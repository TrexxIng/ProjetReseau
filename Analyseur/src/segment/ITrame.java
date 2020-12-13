package segment;

import java.util.List;

import offset.IOffset;

public interface ITrame {
	boolean checkSize();
	List<String> getData();
	String formatDisplay(int tab);
	int getTailleOptions();
}
