package DataBase;

import java.util.*;

public interface Isqltool {
	public boolean add(int prenodeid,Object item);
	public boolean update(Object item);
	public boolean delete(int prenodeid,int nownodeid);
	public ArrayList<Object> show(int id);
}
