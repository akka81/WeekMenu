package hware.weekmenu.BusinessLogic.Entities;

import java.util.HashMap;
import java.util.Map;

public enum Measures {
	
	KG(0),
	G(1),
	ML(2);
	
	private final int value;
	private static Map<Integer, Measures> map = new HashMap<Integer, Measures>();

    static {
        for (Measures MesEnum : Measures.values()) {
            map.put(MesEnum.value, MesEnum);
        }
    }

	private Measures(int Val)
	{
		this.value = Val;
	}
	
	public int getValue(){return value;}
	
	  public static Measures valueOf(int value) {
	        return map.get(value);
	    }
}
