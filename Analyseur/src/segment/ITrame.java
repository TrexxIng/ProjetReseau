package segment;

import java.util.List;

import offset.IOffset;

public interface ITrame {
	List<IOffset> getListOffset();
	boolean checkSize();
	List<String> getData();
	String formatDisplay(int tab);
}
