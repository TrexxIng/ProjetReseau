package protocole;

import java.util.List;

import offset.IOffset;

public interface ITrame {
	List<IOffset> getListOffset();
	boolean checkSize();
}
